package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.SearchItemsPresenter;
import com.example.libraryofpeers.presenters.SearchUsersPresenter;

import Request.SearchItemsRequest;
import Request.SearchUsersRequest;
import Response.SearchUsersResponse;

public class SearchUsersTask extends AsyncTask<SearchUsersRequest, Void, SearchUsersResponse> {
    private SearchUsersPresenter presenter;
    private SearchUsersObserver observer;

    public interface SearchUsersObserver {
        void onSearchSuccess(SearchUsersResponse response);
        void onSearchFail(SearchUsersResponse response);
    }

    public SearchUsersTask(SearchUsersObserver observer, SearchUsersPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    @Override
    protected void onPostExecute(SearchUsersResponse response) {
        if(response.isSuccess()) {
            observer.onSearchSuccess(response);
        } else {
            observer.onSearchFail(response);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected SearchUsersResponse doInBackground(SearchUsersRequest... requests) {
        return presenter.searchUsers(requests[0]);
    }
}
