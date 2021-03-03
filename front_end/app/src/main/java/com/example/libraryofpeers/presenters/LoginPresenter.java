package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.LoginServiceProxy;

import Request.LoginRequest;
import Response.LoginResponse;

public class LoginPresenter {
    private final LoginPresenter.View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, Specify methods here that will be called on the view in response to model updates
    }

    public LoginPresenter(LoginPresenter.View view) {
        this.view = view;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        return LoginServiceProxy.getInstance().login(loginRequest);
    }
}
