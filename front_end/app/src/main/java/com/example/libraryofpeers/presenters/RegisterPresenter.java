package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.RegisterServiceProxy;

import Request.RegisterRequest;
import Response.RegisterResponse;
import Service.IRegisterService;

public class RegisterPresenter {
    public RegisterResponse register(RegisterRequest registerRequest) {
        IRegisterService registerService = new RegisterServiceProxy();
        return registerService.register(registerRequest);
    }
}
