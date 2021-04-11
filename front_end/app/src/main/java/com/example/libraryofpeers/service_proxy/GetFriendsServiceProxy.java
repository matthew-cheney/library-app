package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.FriendsRequest;
import Response.CatalogResponse;
import Response.FriendsResponse;
import Service.IGetFriendsService;

public class GetFriendsServiceProxy implements IGetFriendsService {
    private ServerFacade serverFacade;
    private static String urlpath = "/friends";

    public GetFriendsServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public FriendsResponse getFriends(FriendsRequest request) {
        try {
            return serverFacade.getFriends(request, urlpath);
        } catch(IOException e) {
            return new FriendsResponse(false, e.getMessage());
        }
    }
}
