package com.example.libraryofpeers.view.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import Entities.Item;
import Entities.User;

import static com.example.libraryofpeers.view.utils.FetchImage.setImageToDrawable;
import static com.example.libraryofpeers.view.utils.FetchImage.setImageViewToUrl;

public class ItemBindingUtils {

    public static String bookUrl = "https://cheneycreations.com/hairballs/cat1.png";
    public static String movieUrl = "https://cheneycreations.com/hairballs/cat2.png";
    public static String boardGameUrl = "https://cheneycreations.com/hairballs/cat3.png";

    public static String bookUri = "@drawable/book";
    public static String movieUri = "@drawable/movie";
    public static String boardGameUri = "@drawable/board_game";

    public static String userUri = "@drawable/book";

    public static void bindItemToViews(Item item, TextView itemTitle, TextView itemSubtitle, ImageView itemImage, Context context) {
        itemTitle.setText(item.getTitle());

        if (item.getCategory() == null) {
            return;
        }

        switch (item.getCategory()) {
            case "BOOK":
                setImageToDrawable(itemImage, bookUri, context);
                if (item.getAuthor() != null) {
                    itemSubtitle.setText(item.getAuthor());
                    itemSubtitle.setVisibility(View.VISIBLE);
                } else {
                    itemSubtitle.setVisibility(View.GONE);
                }
                break;
            case "MOVIE":
                setImageToDrawable(itemImage, movieUri, context);
                itemSubtitle.setVisibility(View.GONE);
                break;
            case "BOARD_GAME":
                setImageToDrawable(itemImage, boardGameUri, context);
                itemSubtitle.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public static void bindUserToViews(User item, TextView itemTitle, TextView itemSubtitle, ImageView itemImage, Context context) {
        itemTitle.setText(item.getFirstName());

        setImageToDrawable(itemImage, userUri, context);
        itemSubtitle.setVisibility(View.GONE);
    }

}
