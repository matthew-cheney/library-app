package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.SearchItemsRequest;
import Response.SearchItemsResponse;
import Service.ISearchItemsService;

public class SearchItemsServiceProxy implements ISearchItemsService {
    private ServerFacade serverFacade;
    private static final String urlPath = "/searchitems";

    public SearchItemsServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public SearchItemsResponse searchItems(SearchItemsRequest request) {
        try {
            return serverFacade.searchItems(request, urlPath);
        } catch (IOException e) {
            return new SearchItemsResponse(false, e.getMessage());
        }
    }
}
