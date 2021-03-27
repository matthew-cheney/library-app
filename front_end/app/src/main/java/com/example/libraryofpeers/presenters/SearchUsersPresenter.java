package com.example.libraryofpeers.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.service_proxy.SearchUserServiceProxy;

import Request.SearchUsersRequest;
import Response.SearchUsersResponse;

public class SearchUsersPresenter {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public SearchUsersResponse searchUsers(SearchUsersRequest request) {
        return (new SearchUserServiceProxy()).searchUsers(request);
    }
}
