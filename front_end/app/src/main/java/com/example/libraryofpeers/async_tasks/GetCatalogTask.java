package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.GetCatalogPresenter;

import Request.CatalogRequest;
import Response.CatalogResponse;

public class GetCatalogTask extends AsyncTask<CatalogRequest, Void, CatalogResponse> {
    GetCatalogObserver observer;
    GetCatalogPresenter presenter;

    public GetCatalogTask(GetCatalogObserver observer, GetCatalogPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    public interface GetCatalogObserver {
        void onGetSuccess(CatalogResponse response);
        void onGetFailure(CatalogResponse response);
    }

    @Override
    protected CatalogResponse doInBackground(CatalogRequest... CatalogRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(CatalogResponse CatalogResponse) {
        super.onPostExecute(CatalogResponse);
    }
}
