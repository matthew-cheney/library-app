package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.RegisterPresenter;

import Request.RegisterRequest;
import Response.RegisterResponse;

public class RegisterTask extends AsyncTask<RegisterRequest, Void, RegisterResponse> {
    private final RegisterRequestObserver observer;
    private final RegisterPresenter presenter;

    public RegisterTask(RegisterRequestObserver observer, RegisterPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }


    public interface  RegisterRequestObserver {
        void onRegisterSuccess(RegisterResponse registerResponse);
        void onRegisterFailure(RegisterResponse registerResponse);
    }

    @Override
    protected RegisterResponse doInBackground(RegisterRequest... registerRequests) {
        return presenter.register(registerRequests[0]);
    }

    @Override
    protected void onPostExecute(RegisterResponse registerResponse) {
        if (registerResponse.isSuccess()) {
            observer.onRegisterSuccess(registerResponse);
        } else {
            observer.onRegisterFailure(registerResponse);
        }
    }
}
