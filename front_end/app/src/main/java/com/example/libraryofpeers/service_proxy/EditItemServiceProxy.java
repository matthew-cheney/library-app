package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.AddItemRequest;
import Request.EditItemRequest;
import Response.AddItemResponse;
import Response.EditItemResponse;
import Service.IEditItemService;

public class EditItemServiceProxy implements IEditItemService {
    private ServerFacade serverFacade;
    private static String urlPath = "/edititem";

    public EditItemServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public EditItemResponse editItem(EditItemRequest request) {
        try {
            return serverFacade.editItem(request, urlPath);
        } catch (IOException e) {
            return new EditItemResponse(false, e.getMessage());
        }
    }
}
