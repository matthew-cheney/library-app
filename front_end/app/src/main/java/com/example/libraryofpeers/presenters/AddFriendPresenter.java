package com.example.libraryofpeers.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.service_proxy.AddFriendServiceProxy;

import Request.AddFriendRequest;
import Response.AddFriendResponse;

public class AddFriendPresenter {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public AddFriendResponse addFriend(AddFriendRequest request) {
        return (new AddFriendServiceProxy()).addFriend(request);
    }
}
