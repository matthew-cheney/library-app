package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.GetFriendsPresenter;

import Request.FriendsRequest;
import Response.FriendsResponse;

public class GetFriendsTask extends AsyncTask<FriendsRequest, Void, FriendsResponse> {
    private GetFriendsObserver observer;
    private GetFriendsPresenter presenter;

    public interface GetFriendsObserver {
        void onGetFriendsSuccess(FriendsResponse response);
        void onGetFriendsFail(FriendsResponse response);
    }

    public GetFriendsTask(GetFriendsObserver observer, GetFriendsPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    @Override
    protected void onPostExecute(FriendsResponse friendsResponse) {
        if(friendsResponse.isSuccess()) {
            observer.onGetFriendsSuccess(friendsResponse);
        } else {
            observer.onGetFriendsFail(friendsResponse);
        }
    }

    @Override
    protected FriendsResponse doInBackground(FriendsRequest... friendsRequests) {
        return presenter.getFriends(friendsRequests[0]);
    }
}
