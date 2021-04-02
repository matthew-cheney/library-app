package com.example.libraryofpeers.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.service_proxy.RemoveFriendServiceProxy;

import Request.RemoveFriendRequest;
import Response.RemoveFriendResponse;

public class RemoveFriendPresenter {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public RemoveFriendResponse removeFriend(RemoveFriendRequest request) {
        return (new RemoveFriendServiceProxy()).removeFriend(request);
    }
}
