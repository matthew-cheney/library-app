package com.example.libraryofpeers.service_proxy;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.LoginRequest;
import Request.RegisterRequest;
import Response.LoginResponse;
import Response.RegisterResponse;
import Service.IRegisterService;

public class RegisterServiceProxy implements IRegisterService {
    private final ServerFacade serverFacade = new ServerFacade();
    private static final String URL_PATH = "/register";

    @Override
    public RegisterResponse register(RegisterRequest request) {
        try {
            RegisterResponse response = serverFacade.register(request, URL_PATH);
            if (response.isSuccess()) {
                LoginResponse loginResponse = LoginServiceProxy.getInstance().login(new LoginRequest(
                        request.getUsername(), request.getPassword()
                ));
                if (loginResponse.isSuccess()) {
                    return response;
                } else {
                    return new RegisterResponse(false);
                }
            }
            return response;
        } catch (IOException e) {
            return new RegisterResponse(false,
                    "Internal server error: " + e.getMessage());
        }
    }
}
