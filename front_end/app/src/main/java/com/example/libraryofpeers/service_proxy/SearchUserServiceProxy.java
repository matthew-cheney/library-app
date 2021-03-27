package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.SearchUsersRequest;
import Response.SearchUsersResponse;
import Service.ISearchUsersService;

public class SearchUserServiceProxy implements ISearchUsersService {
    private ServerFacade serverFacade;
    private static final String urlPath = "/searchusers";

    public SearchUserServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public SearchUsersResponse searchUsers(SearchUsersRequest request) {
        try {
            return serverFacade.searchUsers(request, urlPath);
        } catch (IOException e) {
            return new SearchUsersResponse(false, e.getMessage());
        }
    }
}
