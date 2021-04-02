package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.AddFriendRequest;
import Response.AddFriendResponse;
import Service.IAddFriendService;

public class AddFriendServiceProxy implements IAddFriendService {
    private ServerFacade serverFacade;
    private static final String urlPath = "/addfriend";

    public AddFriendServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public AddFriendResponse addFriend(AddFriendRequest request) {
        try {
            return serverFacade.addFriend(request, urlPath);
        } catch(IOException e) {
            return new AddFriendResponse(false, e.getMessage());
        }
    }
}
