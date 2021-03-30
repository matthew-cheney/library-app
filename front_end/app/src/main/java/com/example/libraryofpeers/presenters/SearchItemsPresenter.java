package com.example.libraryofpeers.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.service_proxy.SearchItemsServiceProxy;

import Request.SearchItemsRequest;
import Response.SearchItemsResponse;

public class SearchItemsPresenter {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public SearchItemsResponse searchItems(SearchItemsRequest request) {
        return (new SearchItemsServiceProxy()).searchItems(request);
    }
}
