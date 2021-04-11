package com.example.libraryofpeers.net;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.json.Serializer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


class ClientCommunicator {

    private static final int TIMEOUT_MILLIS = 10000;

    private final String baseURL;

    ClientCommunicator(String baseURL) {
        this.baseURL = baseURL;
    }

    private interface RequestStrategy {
        void setRequestMethod(HttpURLConnection connection) throws IOException;
        void sendRequest(HttpURLConnection connection) throws IOException;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    <T> T doPost(final String urlPath, final Object requestInfo, Map<String, String> headers, Class<T> returnType) throws IOException {
        RequestStrategy requestStrategy = new RequestStrategy() {
            @Override
            public void setRequestMethod(HttpURLConnection connection) throws IOException {
                connection.setRequestMethod("POST");
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void sendRequest(HttpURLConnection connection) throws IOException {
                connection.setDoOutput(true);

                String entityBody = Serializer.serialize(requestInfo);
                System.out.println("........................" + urlPath + "=" + entityBody + ".........................");
                try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                    os.writeBytes(entityBody);
                    os.flush();
                }
            }
        };

        return doRequest(urlPath, headers, returnType, requestStrategy);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    <T> T doGet(String urlPath, Map<String, String> headers, Class<T> returnType) throws IOException {
        RequestStrategy requestStrategy = new RequestStrategy() {
            @Override
            public void setRequestMethod(HttpURLConnection connection) throws IOException {
                connection.setRequestMethod("GET");
            }

            @Override
            public void sendRequest(HttpURLConnection connection) {
                // Nothing to send. For a get, the request is sent when the connection is opened.
            }
        };

        return doRequest(urlPath, headers, returnType, requestStrategy);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private <T> T doRequest(String urlPath, Map<String, String> headers, Class<T> returnType, RequestStrategy requestStrategy) throws IOException {

        HttpURLConnection connection = null;

        try {
            URL url = getUrl(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(TIMEOUT_MILLIS);
            requestStrategy.setRequestMethod(connection);

            if(headers != null) {
                for (String headerKey : headers.keySet()) {
                    connection.setRequestProperty(headerKey, headers.get(headerKey));
                }
            }

            requestStrategy.sendRequest(connection);

            String response = getResponse(connection);
            return Serializer.deserialize(response, returnType);
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    private URL getUrl(String urlPath) throws MalformedURLException {
        String urlString = baseURL + (urlPath.startsWith("/") ? "" : "/") + urlPath;
        return new URL(urlString);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getResponse(HttpURLConnection connection) throws IOException {
        if (connection.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
            throw new IOException(connection.getResponseCode() + " " + connection.getResponseMessage());
        } else if (connection.getResponseCode() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
            throw new IOException(connection.getResponseCode() + " " + connection.getResponseMessage());
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        }
    }
}
