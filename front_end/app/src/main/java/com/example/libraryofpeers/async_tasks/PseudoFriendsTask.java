package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.FriendsPresenter;

import java.io.IOException;
import java.util.List;

import Entities.Item;
import Request.FriendsRequest;
import Response.FriendsResponse;

public class PseudoFriendsTask extends AsyncTask<FriendsRequest, Void, FriendsResponse> {

    private final FriendsPresenter presenter;
    private final PseudoFriendsTask.Observer observer;
    private Exception exception;
    private String query;

    public interface Observer {
        void friendsRetrieved(FriendsResponse FriendsResponse);
        void handleException(Exception exception);
    }

    public PseudoFriendsTask(FriendsPresenter presenter, PseudoFriendsTask.Observer observer, String query) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
        this.query = query;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected FriendsResponse doInBackground(FriendsRequest... FriendsRequests) {

        FriendsResponse response = null;

        try {
            response = presenter.getFriends(FriendsRequests[0]);
        } catch (IOException ex) {
            exception = ex;
        }

        return response;
    }

    @Override
    protected void onPostExecute(FriendsResponse friendsResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.friendsRetrieved(friendsResponse);
        }
    }
}
