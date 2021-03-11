package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.AddItemServiceProxy;

import Request.AddItemRequest;
import Response.AddItemResponse;
import Service.IAddItemService;

public class AddItemPresenter {
    public AddItemResponse addItem(AddItemRequest request) {
        IAddItemService service = new AddItemServiceProxy();
        return service.addItem(request);
    }
}
