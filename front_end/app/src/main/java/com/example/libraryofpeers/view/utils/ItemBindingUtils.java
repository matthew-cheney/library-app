package com.example.libraryofpeers.view.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import Entities.Item;

import static com.example.libraryofpeers.view.utils.FetchImage.setImageToDrawable;
import static com.example.libraryofpeers.view.utils.FetchImage.setImageViewToUrl;

public class ItemBindingUtils {

    public static String bookUrl = "https://cheneycreations.com/hairballs/cat1.png";
    public static String movieUrl = "https://cheneycreations.com/hairballs/cat2.png";
    public static String boardGameUrl = "https://cheneycreations.com/hairballs/cat3.png";

    public static String bookUri = "@drawable/book";
    public static String movieUri = "@drawable/movie";
    public static String boardGameUri = "@drawable/board_game";

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
//
//    private static SpannableString parseBody(String body) {
//        SpannableString spannableText = new SpannableString(body);
//        boolean justSawSpace = true;
//        for (int i = 0; i < spannableText.length(); i++) {
//            if (justSawSpace && spannableText.charAt(i) == '@') {
//                int start = i;
//                i++;
//                StringBuilder nextTag = new StringBuilder();
//                while (i < spannableText.length()) {
//                    if (spannableText.charAt(i) != ' ') {
//                        nextTag.append(spannableText.charAt(i));
//                        i++;
//                    } else {
//                        break;
//                    }
//                }
//                if (spannableText.length() > 1) {
//                    spannableText.setSpan(new ClickSpan(nextTag.toString()), start, i, spannableText.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            } else {
//                justSawSpace = spannableText.charAt(i) == ' ';
//            }
//        }
//        return spannableText;
//    }

}
