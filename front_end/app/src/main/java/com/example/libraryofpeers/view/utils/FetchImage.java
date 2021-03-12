package com.example.libraryofpeers.view.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class FetchImage {

    public static void setImageViewToUrl(ImageView imageView, String url) {
        new DownloadImageTask(imageView).execute(url);
    }

    public static void setImageToDrawable(ImageView imageView, String uri, Context context) {
        Drawable res = context.getResources().getDrawable(context.getResources().getIdentifier(uri, null, context.getPackageName()));
        imageView.setImageDrawable(res);
    }

}
