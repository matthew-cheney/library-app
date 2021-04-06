package com.example.libraryofpeers.view.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import Config.Constants;
import Entities.Item;
import Entities.User;
import Enums.ObjectTypeEnum;

public class ItemBindingUtils {

    public static String bookUrl = "https://cheneycreations.com/hairballs/cat1.png";
    public static String movieUrl = "https://cheneycreations.com/hairballs/cat2.png";
    public static String boardGameUrl = "https://cheneycreations.com/hairballs/cat3.png";

    public static String bookUri = "@drawable/book";
    public static String movieUri = "@drawable/movie";
    public static String boardGameUri = "@drawable/board_game";

    public static String userUri = "@drawable/person";

    public static void bindItemToViews(Item item, TextView itemTitle, TextView itemSubtitle, ImageView itemImage, Context context) {
        itemTitle.setText(item.getTitle());

        if (item.getCategory() == null) {
            return;
        }

        switch (item.getCategory()) {
            case Constants.BOOK_CATEGORY:
                itemImage.setImageDrawable(ImageUtils.drawableFromUrl(item.getImageUrl(), ObjectTypeEnum.book, context));
                if (item.getAuthor() != null) {
                    itemSubtitle.setText(item.getAuthor());
                    itemSubtitle.setVisibility(View.VISIBLE);
                } else {
                    itemSubtitle.setVisibility(View.GONE);
                }
                break;
            case Constants.MOVIE_CATEGORY:
                itemImage.setImageDrawable(ImageUtils.drawableFromUrl(item.getImageUrl(), ObjectTypeEnum.movie, context));
                itemSubtitle.setVisibility(View.GONE);
                break;
            case Constants.BOARD_GAME_CATEGORY:
                itemImage.setImageDrawable(ImageUtils.drawableFromUrl(item.getImageUrl(), ObjectTypeEnum.boardGame, context));
                itemSubtitle.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public static void bindUserToViews(User user, TextView itemTitle, TextView itemSubtitle, ImageView itemImage, Context context) {
        String userFullName = user.getFirstName() + " " + user.getLastName();
        itemTitle.setText(userFullName);
        itemSubtitle.setText(user.getEmail());
        itemImage.setImageDrawable(ImageUtils.drawableFromUrl(user.getImageUrl(), ObjectTypeEnum.user, context));
        itemTitle.setVisibility(View.VISIBLE);
        itemSubtitle.setVisibility(View.VISIBLE);
        itemImage.setVisibility(View.VISIBLE);
    }

}
