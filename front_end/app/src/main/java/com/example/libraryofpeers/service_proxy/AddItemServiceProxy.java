package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.AddItemRequest;
import Response.AddItemResponse;
import Service.IAddItemService;

public class AddItemServiceProxy implements IAddItemService {
    private ServerFacade serverFacade;
    private static String urlPath = "/additem";

    public AddItemServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public AddItemResponse addItem(AddItemRequest request) {
        try {
            return serverFacade.addItem(request, urlPath);
        } catch (IOException e) {
            return new AddItemResponse(false, e.getMessage());
        }
    }
}
