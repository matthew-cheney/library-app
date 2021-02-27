package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.LoginServiceProxy;

import Request.LoginRequest;
import Response.LoginResponse;

public class LoginPresenter {
    public LoginResponse login(LoginRequest loginRequest) {
        return LoginServiceProxy.getInstance().login(loginRequest);
    }
}
