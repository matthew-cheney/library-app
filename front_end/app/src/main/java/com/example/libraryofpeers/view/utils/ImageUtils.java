package com.example.libraryofpeers.view.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.MutableBoolean;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
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

import Config.Constants;
import Enums.ObjectTypeEnum;

public class ImageUtils {

    public static class ImageReporter {
        public boolean usingDefault;
        public ImageReporter(boolean usingDefault) {
            this.usingDefault = usingDefault;
        }
    }

    public static Drawable drawableFromUrl(String url, ObjectTypeEnum objectType, Context context, ImageReporter reporter) {
        try {
            if (url == null) throw new IOException("URL was null.");
            GetBytesTask task = new GetBytesTask();
            task.execute(url);
            byte[] imageBytes = task.get(10, TimeUnit.SECONDS); // This is the Java way to await for 10 seconds.
            if (imageBytes == null) throw new IOException("Error getting URL.");
            Drawable resDrawable = drawableFromByteArray(imageBytes);
            reporter.usingDefault = false;
            return resDrawable;
        }
        catch (ExecutionException | InterruptedException | IOException | TimeoutException ex) {
            reporter.usingDefault = true;
            switch (objectType) {
                case user:
                    return drawableFromContext(context, Constants.USER_URI);
                case book:
                    return drawableFromContext(context, Constants.BOOK_URI);
                case movie:
                    return drawableFromContext(context, Constants.MOVIE_URI);
                case boardGame:
                    return drawableFromContext(context, Constants.BOARD_GAME_URI);
                default:
                    return null;
            }
        }
    }

    public static Drawable drawableFromUrl(String url, ObjectTypeEnum objectType, Context context) {
        ImageReporter reporter = new ImageReporter(false);
        return drawableFromUrl(url, objectType, context, reporter);
    }


        private static Drawable drawableFromByteArray(byte[] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return new BitmapDrawable(Resources.getSystem(), bitmap);
    }

    private static Drawable drawableFromContext(Context context, String uri) {
        return ResourcesCompat.getDrawable(
                context.getResources(),
                context.getResources().getIdentifier(uri, null, context.getPackageName()),
                null);
    }

    private static class GetBytesTask extends AsyncTask<String, Void, byte[]> {

        protected byte[] doInBackground(String... urls) {
            HttpURLConnection connection = null;

            try {
                String urlString = urls[0];
                urlString = urlString.replace(' ', '+');
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
                }
            }

            return null;
        }

        private static byte[] bytesFromInputStream(InputStream inputStream) throws IOException {

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
}
