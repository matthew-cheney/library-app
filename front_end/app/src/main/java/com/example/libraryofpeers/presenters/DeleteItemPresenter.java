package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.DeleteItemServiceProxy;

import Request.DeleteItemRequest;
import Response.DeleteItemResponse;
import Service.IDeleteItemService;

public class DeleteItemPresenter {
    public DeleteItemResponse delete(DeleteItemRequest request) {
        IDeleteItemService service = new DeleteItemServiceProxy();
        return service.deleteItem(request);
    }
}
