package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.AddFriendRequest;
import Request.RemoveFriendRequest;
import Response.AddFriendResponse;
import Response.RemoveFriendResponse;
import Service.IRemoveFriendService;

public class RemoveFriendServiceProxy implements IRemoveFriendService {
    private ServerFacade serverFacade;
    private static final String urlPath = "/removefriend";

    public RemoveFriendServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public RemoveFriendResponse removeFriend(RemoveFriendRequest request) {
        try {
            return serverFacade.removeFriend(request, urlPath);
        } catch (IOException e) {
            return new RemoveFriendResponse(false, e.getMessage());
        }
    }
}
