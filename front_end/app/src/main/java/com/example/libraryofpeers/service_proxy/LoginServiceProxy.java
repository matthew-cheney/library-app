package com.example.libraryofpeers.service_proxy;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Entities.User;
import Request.LoginRequest;
import Response.LoginResponse;
import Response.RegisterResponse;
import Service.ILoginService;

public class LoginServiceProxy implements ILoginService {

    public static LoginServiceProxy instance;
    public final ServerFacade serverFacade;
    public static final String URL_PATH = "/login";
    public User currentUser;
    public String curAuthToken;

    public static LoginServiceProxy getInstance() {
        if(instance == null) {
            instance = new LoginServiceProxy();
        }
        return instance;
    }

    private LoginServiceProxy() {
        serverFacade = new ServerFacade();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurAuthToken() {
        return curAuthToken;
    }

    public void setCurAuthToken(String curAuthToken) {
        this.curAuthToken = curAuthToken;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            LoginResponse response = serverFacade.login(request, URL_PATH);
            if (response.isSuccess()) {
                setCurrentUser(response.getUser());
            }
            return response;
        } catch (IOException e) {
            return new LoginResponse(false,
                    "Internal server error: " + e.getMessage());
        }
    }
}
