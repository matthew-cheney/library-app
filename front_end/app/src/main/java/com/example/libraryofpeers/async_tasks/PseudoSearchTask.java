package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.presenters.CatalogPresenter;

import java.io.IOException;
import java.util.List;

import Entities.Item;
import Request.CatalogRequest;
import Response.CatalogResponse;

public class PseudoSearchTask extends AsyncTask<CatalogRequest, Void, CatalogResponse> {

    private final CatalogPresenter presenter;
    private final CatalogTask.Observer observer;
    private Exception exception;
    private String query;

    public interface Observer {
        void catalogRetrieved(CatalogResponse CatalogResponse);
        void handleException(Exception exception);
    }

    public PseudoSearchTask(CatalogPresenter presenter, CatalogTask.Observer observer, String query) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
        this.query = query;
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

        List<Item> newItems = response.getItems();

        for (Item item : newItems) {
            item.setTitle(query + ":" + item.getTitle());
        }

        response.setItems(newItems);

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
