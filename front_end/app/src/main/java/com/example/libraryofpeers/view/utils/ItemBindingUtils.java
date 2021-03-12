package com.example.libraryofpeers.view.utils;

import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import Entities.Item;

import static com.example.libraryofpeers.view.utils.FetchImage.setImageToDrawable;
import static com.example.libraryofpeers.view.utils.FetchImage.setImageViewToUrl;

public class ItemBindingUtils {

    public static String bookUrl = "https://cheneycreations.com/hairballs/cat1.png";
    public static String movieUrl = "https://cheneycreations.com/hairballs/cat2.png";
    public static String boardGameUrl = "https://cheneycreations.com/hairballs/cat3.png";

    public static void bindItemToViews(Item item, TextView itemTitle, TextView itemSubtitle, ImageView itemImage) {
        itemTitle.setText(item.getTitle());

        if (item.getCategory() == null) {
            return;
        }

        switch (item.getCategory()) {
            case "BOOK":
                setImageViewToUrl(itemImage, bookUrl);
                itemSubtitle.setText(item.getCategory());
                break;
            case "MOVIE":
                setImageViewToUrl(itemImage, movieUrl);
                itemSubtitle.setText(item.getCategory());
                break;
            case "BOARD_GAME":
                setImageViewToUrl(itemImage, boardGameUrl);
                itemSubtitle.setText(item.getCategory());
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
