package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.presenters.EditItemPresenter;

import Request.EditItemRequest;
import Response.EditItemResponse;

public class EditItemTask extends AsyncTask<EditItemRequest, Void, EditItemResponse> {
    EditItemObserver observer;
    EditItemPresenter presenter;

    public EditItemTask(EditItemObserver observer, EditItemPresenter presenter) {
        this.observer = observer;
        this.presenter = presenter;
    }

    public interface EditItemObserver {
        void onEditSuccess(EditItemResponse response);
        void onEditFailure(EditItemResponse response);
    }

    @Override
    protected EditItemResponse doInBackground(EditItemRequest... EditItemRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(EditItemResponse EditItemResponse) {
        super.onPostExecute(EditItemResponse);
    }
}
