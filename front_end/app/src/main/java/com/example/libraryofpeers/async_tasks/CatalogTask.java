package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.CatalogPresenter;

import java.io.IOException;

import Request.CatalogRequest;
import Response.CatalogResponse;

public class CatalogTask extends AsyncTask<CatalogRequest, Void, CatalogResponse> {

    private final CatalogPresenter presenter;
    private final Observer observer;
    private Exception exception;

    public interface Observer {
        void catalogRetrieved(CatalogResponse CatalogResponse);
        void handleException(Exception exception);
    }

    public CatalogTask(CatalogPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected CatalogResponse doInBackground(CatalogRequest... CatalogRequests) {

        CatalogResponse response = null;

        try {
            response = presenter.getCatalog(CatalogRequests[0]);
        } catch (IOException ex) {
            exception = ex;
        }

        return response;
    }

    @Override
    protected void onPostExecute(CatalogResponse catalogResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.catalogRetrieved(catalogResponse);
        }
    }
}
