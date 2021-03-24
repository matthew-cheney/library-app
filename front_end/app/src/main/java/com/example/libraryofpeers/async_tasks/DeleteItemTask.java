package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.DeleteItemPresenter;

import Request.DeleteItemRequest;
import Response.DeleteItemResponse;

public class DeleteItemTask extends AsyncTask<DeleteItemRequest, Void, DeleteItemResponse> {

    private DeleteItemObserver observer;
    private DeleteItemPresenter presenter;

    public DeleteItemTask(DeleteItemObserver observer, DeleteItemPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    public interface DeleteItemObserver {
        void onDeleteSuccess(DeleteItemResponse response);
        void onDeleteFail(DeleteItemResponse response);
    }

    @Override
    protected void onPostExecute(DeleteItemResponse response) {
        if(response.isSuccess()) {
            observer.onDeleteSuccess(response);
        } else {
            observer.onDeleteFail(response);
        }
    }

    @Override
    protected DeleteItemResponse doInBackground(DeleteItemRequest... requests) {
        return presenter.delete(requests[0]);
    }
}
