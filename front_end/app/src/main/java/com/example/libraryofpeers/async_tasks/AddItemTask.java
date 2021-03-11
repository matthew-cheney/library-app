package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.AddItemPresenter;

import Request.AddItemRequest;
import Response.AddItemResponse;

public class AddItemTask extends AsyncTask<AddItemRequest, Void, AddItemResponse> {
    AddItemObserver observer;
    AddItemPresenter presenter;

    public AddItemTask(AddItemObserver observer, AddItemPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    public interface AddItemObserver {
        void onAddSuccess(AddItemResponse response);
        void onAddFailure(AddItemResponse response);
    }

    @Override
    protected AddItemResponse doInBackground(AddItemRequest... addItemRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(AddItemResponse addItemResponse) {
        super.onPostExecute(addItemResponse);
    }
}
