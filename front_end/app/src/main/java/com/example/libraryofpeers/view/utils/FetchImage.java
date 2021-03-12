package com.example.libraryofpeers.view.utils;

import android.widget.ImageView;

public class FetchImage {

    public static void setImageViewToUrl(ImageView imageView, String url) {
        new DownloadImageTask(imageView).execute(url);
    }

}
