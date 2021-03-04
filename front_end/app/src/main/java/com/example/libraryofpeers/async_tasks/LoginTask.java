package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.LoginPresenter;
import com.example.libraryofpeers.presenters.RegisterPresenter;

import Request.LoginRequest;
import Request.RegisterRequest;
import Response.LoginResponse;
import Response.RegisterResponse;

public class LoginTask extends AsyncTask<LoginRequest, Void, LoginResponse> {
    private final LoginTask.LoginRequestObserver observer;
    private final LoginPresenter presenter;

    public LoginTask(LoginTask.LoginRequestObserver observer, LoginPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }


    public interface LoginRequestObserver {
        void onLoginSuccess(LoginResponse response);
        void onLoginFailure(LoginResponse response);
    }

    @Override
    protected LoginResponse doInBackground(LoginRequest... requests) {
        return presenter.login(requests[0]);
    }

    @Override
    protected void onPostExecute(LoginResponse response) {
        if (response.isSuccess()) {
            observer.onLoginSuccess(response);
        } else {
            observer.onLoginFailure(response);
        }
    }
}
