package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.RemoveFriendPresenter;


import Request.RemoveFriendRequest;
import Response.RemoveFriendResponse;

public class RemoveFriendTask extends AsyncTask<RemoveFriendRequest, Void, RemoveFriendResponse> {
    private RemoveFriendTask.RemoveFriendObserver observer;
    private RemoveFriendPresenter presenter;

    public interface RemoveFriendObserver {
        void onRemoveFriendSuccess(RemoveFriendResponse response);
        void onRemoveFriendFailure(RemoveFriendResponse response);
    }

    public RemoveFriendTask(RemoveFriendTask.RemoveFriendObserver observer, RemoveFriendPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    @Override
    protected void onPostExecute(RemoveFriendResponse RemoveFriendResponse) {
        if(RemoveFriendResponse.isSuccess()) {
            observer.onRemoveFriendSuccess(RemoveFriendResponse);
        } else {
            observer.onRemoveFriendFailure(RemoveFriendResponse);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected RemoveFriendResponse doInBackground(RemoveFriendRequest... RemoveFriendRequests) {
        return presenter.removeFriend(RemoveFriendRequests[0]);
    }
}
