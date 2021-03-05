package com.example.libraryofpeers.view.utils;

import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import Entities.Item;

public class ItemBindingUtils {

    public static void bindItemToViews(Item item, TextView itemTitle) {
//        itemImage.setImageDrawable(ImageUtils.drawableFromByteArray(item.getAuthor().getImageBytes()));
//        itemAlias.setText(item.getAuthor().getAlias());
        itemTitle.setText(item.getTitle());
//        itemContent.setText(parseBody(item.getBody()));
//        itemContent.setMovementMethod(LinkMovementMethod.getInstance());
//        itemTimestamp.setText(item.getDateTimePosted().toString());
    }

    private static SpannableString parseBody(String body) {
        SpannableString spannableText = new SpannableString(body);
        boolean justSawSpace = true;
        for (int i = 0; i < spannableText.length(); i++) {
            if (justSawSpace && spannableText.charAt(i) == '@') {
                int start = i;
                i++;
                StringBuilder nextTag = new StringBuilder();
                while (i < spannableText.length()) {
                    if (spannableText.charAt(i) != ' ') {
                        nextTag.append(spannableText.charAt(i));
                        i++;
                    } else {
                        break;
                    }
                }
                if (spannableText.length() > 1) {
                    spannableText.setSpan(new ClickSpan(nextTag.toString()), start, i, spannableText.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } else {
                justSawSpace = spannableText.charAt(i) == ' ';
            }
        }
        return spannableText;
    }

}
