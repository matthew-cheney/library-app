package com.example.libraryofpeers.utilities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.core.content.res.ResourcesCompat;

import com.example.libraryofpeers.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Enums.ObjectTypeEnum;

public class ImageUtils implements GetBytesTask.GetBytesObserver {

    byte [] imageBytes = new byte [] {};

    public Drawable drawableFromUrl(String url, ObjectTypeEnum objectType) {
        try {
            GetBytesTask task = new GetBytesTask(this);
            task.execute(url);
            imageBytes = task.get(10, TimeUnit.SECONDS);
            if (imageBytes == null) throw new IOException("Error getting URL.");
            return drawableFromByteArray(imageBytes);
        }
        catch (ExecutionException | InterruptedException | IOException | TimeoutException ex) {
            switch (objectType) {
                case user:
                    return ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.ic_profile, null);
                case book:
                    return ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.book, null);
                case movie:
                    return ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.movie, null);
                case boardGame:
                    return ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.board_game, null);
                default:
                    return null;
            }
        }
    }

    @Override
    public void onTaskComplete(byte[] bytes) {
        imageBytes = bytes;
    }

    private static Drawable drawableFromByteArray(byte [] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return new BitmapDrawable(Resources.getSystem(), bitmap);
    }
}

class GetBytesTask extends AsyncTask<String, Void, byte []> {

    GetBytesObserver observer;

    public GetBytesTask(GetBytesObserver observer) {
        this.observer = observer;
    }

    public interface GetBytesObserver {
        void onTaskComplete(byte [] bytes);
    }

    protected byte [] doInBackground(String... urls) {
        HttpURLConnection connection = null;

        try {
            String urlString = urls[0];
            urlString.replace(' ', '+');
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return bytesFromInputStream(inputStream);
            }
        }
        catch (IOException ex) {
            System.out.println("Unable to read from url.");
        }
        finally {
            if(connection != null) {
                connection.disconnect();
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        return null;
    }

    protected void onPostExecute(byte [] bytes) {
        observer.onTaskComplete(bytes);
    }

    private static byte [] bytesFromInputStream(InputStream inputStream) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int numbRead;
        byte[] data = new byte[1024];
        while ((numbRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, numbRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }
}
