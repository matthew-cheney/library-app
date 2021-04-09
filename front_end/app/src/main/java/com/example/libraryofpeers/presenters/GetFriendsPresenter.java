package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.GetCatalogServiceProxy;
import com.example.libraryofpeers.service_proxy.GetFriendsServiceProxy;

import Request.FriendsRequest;
import Response.FriendsResponse;
import Service.IGetCatalogService;
import Service.IGetFriendsService;

public class GetFriendsPresenter {
    public FriendsResponse getFriends(FriendsRequest request) {
        IGetFriendsService service = new GetFriendsServiceProxy();
        return service.getFriends(request);
    }
}
