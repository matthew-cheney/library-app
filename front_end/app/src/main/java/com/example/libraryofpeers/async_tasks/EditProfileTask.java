package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.EditProfilePresenter;

import Request.EditUserRequest;
import Response.EditUserResponse;

public class EditProfileTask extends AsyncTask<EditUserRequest, Void, EditUserResponse> {
    private final EditProfileTask.EditProfileRequestObserver observer;
    private final EditProfilePresenter presenter;

    public EditProfileTask(EditProfileTask.EditProfileRequestObserver observer, EditProfilePresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }


    public interface EditProfileRequestObserver {
        void onEditSuccess(EditUserResponse response);
        void onEditFailure(EditUserResponse response);
    }

    @Override
    protected EditUserResponse doInBackground(EditUserRequest... requests) {
        return presenter.editProfile(requests[0]);
    }

    @Override
    protected void onPostExecute(EditUserResponse response) {
        if (response.isSuccess()) {
            observer.onEditSuccess(response);
        } else {
            observer.onEditFailure(response);
        }
    }
}
