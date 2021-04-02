package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.FriendsPresenter;

import Request.Abstract.FriendRequest;
import Request.FriendsRequest;
import Response.FriendsResponse;

public class FriendsTask extends AsyncTask<FriendsRequest, Void, FriendsResponse> {
    private FriendsObserver observer;
    private FriendsPresenter presenter;

    public interface FriendsObserver {
        void onGetFriendsSuccess(FriendsResponse response);
        void onGetFriendsFailure(FriendsResponse response);
    }

    public FriendsTask(FriendsObserver observer, FriendsPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    @Override
    protected void onPostExecute(FriendsResponse response) {
        if(response.isSuccess()) {
            observer.onGetFriendsSuccess(response);
        } else {
            observer.onGetFriendsFailure(response);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected FriendsResponse doInBackground(FriendsRequest... friendsRequests) {
        return presenter.getFriends(friendsRequests[0]);
    }
}
