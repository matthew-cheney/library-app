package com.example.libraryofpeers.view.utils;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

//import com.example.libraryofpeers.view.ViewUserActivity;

import Entities.User;


public class UserClickListener implements View.OnClickListener {

    private User user;

    public UserClickListener(User user) {
        this.user = user;
    }

    @Override
    public void onClick(View view) {
//        Intent intent = new Intent(view.getContext(), ViewUserActivity.class);
//        intent.putExtra("user", user);
//        view.getContext().startActivity(intent);
        Toast.makeText(view.getContext(), user.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}
