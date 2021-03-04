package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.RegisterServiceProxy;

import Request.RegisterRequest;
import Response.RegisterResponse;
import Service.IRegisterService;

public class RegisterPresenter {
    private final RegisterPresenter.View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public RegisterPresenter(RegisterPresenter.View view) {
        this.view = view;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        IRegisterService registerService = new RegisterServiceProxy();
        return registerService.register(registerRequest);
    }
}
