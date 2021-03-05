package com.example.libraryofpeers.service_proxy;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.EditUserRequest;
import Response.EditUserResponse;
import Service.IEditUserService;

public class EditUserServiceProxy implements IEditUserService {
    private ServerFacade serverFacade;
    private static final String URL_PATH = "/edituser";

    public EditUserServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @Override
    public EditUserResponse editUser(EditUserRequest request) {
        try{
            return serverFacade.editUser(request, URL_PATH);
        } catch (IOException e) {
            return new EditUserResponse(false, "Internal server error: " + e.getMessage());
        }
    }
}
