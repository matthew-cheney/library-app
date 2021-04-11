package com.example.libraryofpeers.view.utils;

import android.content.Intent;
import android.view.View;

import com.example.libraryofpeers.view.ViewItemActivity;

import Entities.Item;


public class ItemClickListener implements View.OnClickListener {

    private Item item;
    private boolean isCurrentUser;

    public ItemClickListener(Item item, boolean isCurrentUser) {
        this.item = item;
        this.isCurrentUser = isCurrentUser;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ViewItemActivity.class);
        intent.putExtra("item", item);
        intent.putExtra("isCurrentUser", isCurrentUser);
        view.getContext().startActivity(intent);
    }

}
