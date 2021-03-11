package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.EditProfilePresenter;
import com.example.libraryofpeers.presenters.LoginPresenter;
import com.example.libraryofpeers.presenters.RegisterPresenter;

import Request.EditUserRequest;
import Request.LoginRequest;
import Response.EditUserResponse;
import Response.LoginResponse;

public class EditProfileTask extends AsyncTask<EditUserRequest, Void, EditUserResponse> {
    private final EditProfileTask.EditProfileRequestObserver observer;
    private final EditProfilePresenter presenter;

    public EditProfileTask(EditProfileTask.EditProfileRequestObserver observer, EditProfilePresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }


    public interface EditProfileRequestObserver {
        void onLoginSuccess(EditUserResponse response);
        void onLoginFailure(EditUserResponse response);
    }

    @Override
    protected EditUserResponse doInBackground(EditUserRequest... requests) {
        return presenter.editProfile(requests[0]);
    }

    @Override
    protected void onPostExecute(EditUserResponse response) {
        if (response.isSuccess()) {
            observer.onLoginSuccess(response);
        } else {
            observer.onLoginFailure(response);
        }
    }
}
