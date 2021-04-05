package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.EditProfilePresenter;

import java.util.PriorityQueue;
import java.util.Queue;

import Entities.User;
import Request.EditUserRequest;
import Response.EditUserResponse;

public class EditProfileTask extends AsyncTask<EditUserRequest, Void, EditUserResponse> {
    private final EditProfileTask.EditProfileRequestObserver observer;
    private final EditProfilePresenter presenter;
    private Queue<User> editedUsersCache = new PriorityQueue<>();

    public EditProfileTask(EditProfileTask.EditProfileRequestObserver observer, EditProfilePresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    public interface EditProfileRequestObserver {
        void onEditSuccess(EditUserResponse response, User user);
        void onEditFailure(EditUserResponse response);
    }

    @Override
    protected EditUserResponse doInBackground(EditUserRequest... requests) {
        editedUsersCache.add(requests[0].getUser());
        return presenter.editProfile(requests[0]);
    }

    @Override
    protected void onPostExecute(EditUserResponse response) {
        if (response.isSuccess()) {
            observer.onEditSuccess(response, editedUsersCache.remove());
        } else {
            observer.onEditFailure(response);
        }
    }
}
