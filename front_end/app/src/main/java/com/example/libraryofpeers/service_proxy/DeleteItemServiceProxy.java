package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.DeleteItemRequest;
import Response.DeleteItemResponse;
import Service.IDeleteItemService;

public class DeleteItemServiceProxy implements IDeleteItemService {
    private ServerFacade serverFacade;
    private static String urlPath = "/deleteitem";

    public DeleteItemServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public DeleteItemResponse deleteItem(DeleteItemRequest request) {
        try {
            return serverFacade.deleteItem(request, urlPath);
        } catch (IOException e) {
            return new DeleteItemResponse(false, e.getMessage());
        }
    }
}
