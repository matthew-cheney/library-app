package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;


import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.AddFriendPresenter;

import Request.AddFriendRequest;
import Response.AddFriendResponse;

public class AddFriendTask extends AsyncTask<AddFriendRequest, Void, AddFriendResponse> {
    private AddFriendObserver observer;
    private AddFriendPresenter presenter;

    public interface AddFriendObserver {
        void onAddFriendSuccess(AddFriendResponse response);
        void onAddFriendFailure(AddFriendResponse response);
    }

    public AddFriendTask(AddFriendObserver observer, AddFriendPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    @Override
    protected void onPostExecute(AddFriendResponse addFriendResponse) {
        if(addFriendResponse.isSuccess()) {
            observer.onAddFriendSuccess(addFriendResponse);
        } else {
            observer.onAddFriendFailure(addFriendResponse);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected AddFriendResponse doInBackground(AddFriendRequest... addFriendRequests) {
        return presenter.addFriend(addFriendRequests[0]);
    }
}
