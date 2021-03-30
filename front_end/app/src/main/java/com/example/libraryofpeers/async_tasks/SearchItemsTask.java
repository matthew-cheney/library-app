package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.SearchItemsPresenter;

import Request.SearchItemsRequest;
import Response.SearchItemsResponse;


public class SearchItemsTask extends AsyncTask<SearchItemsRequest, Void, SearchItemsResponse> {
    private SearchItemsPresenter presenter;
    private SearchItemsObserver observer;

    public interface SearchItemsObserver {
        void onSearchSuccess(SearchItemsResponse response);
        void onSearchFail(SearchItemsResponse response);
    }

    public SearchItemsTask(SearchItemsObserver observer, SearchItemsPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected SearchItemsResponse doInBackground(SearchItemsRequest... searchItemsRequests) {
        return this.presenter.searchItems(searchItemsRequests[0]);
    }

    @Override
    protected void onPostExecute(SearchItemsResponse searchItemsResponse) {
        if(searchItemsResponse.isSuccess()) {
            observer.onSearchSuccess(searchItemsResponse);
        } else {
            observer.onSearchFail(searchItemsResponse);
        }
    }
}
