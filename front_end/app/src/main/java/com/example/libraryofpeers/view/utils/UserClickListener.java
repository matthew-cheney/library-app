package com.example.libraryofpeers.view.utils;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import com.example.libraryofpeers.view.FriendProfileActivity;

import Entities.User;

public class UserClickListener implements View.OnClickListener {

    private User user;

    public UserClickListener(User user) {
        this.user = user;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), FriendProfileActivity.class);
        intent.putExtra("user", user);
        SearchCache.setFriendCatalogQuery("");
        SearchCache.setFriendCategoryFilter(null);
        view.getContext().startActivity(intent);
    }
}
