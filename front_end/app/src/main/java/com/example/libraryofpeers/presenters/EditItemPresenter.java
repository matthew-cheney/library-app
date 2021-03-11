package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.EditItemServiceProxy;

import Request.EditItemRequest;
import Response.EditItemResponse;
import Service.IEditItemService;

public class EditItemPresenter {
    public EditItemResponse EditItem(EditItemRequest request) {
        IEditItemService service = new EditItemServiceProxy();
        return service.editItem(request);
    }
}
