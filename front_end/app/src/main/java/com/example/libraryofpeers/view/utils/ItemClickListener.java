package com.example.libraryofpeers.view.utils;

import android.content.Intent;
import android.view.View;

import com.example.libraryofpeers.view.ViewItemActivity;

import Entities.Item;


public class ItemClickListener implements View.OnClickListener {

    private Item item;

    public ItemClickListener(Item item) {
        this.item = item;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ViewItemActivity.class);
        intent.putExtra("item", item);
        view.getContext().startActivity(intent);
    }
}
