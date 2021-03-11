package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import Request.AddItemRequest;
import Response.AddItemResponse;

public class AddItemTask extends AsyncTask<AddItemRequest, Void, AddItemResponse> {
    public interface AddItemObserver {
        public void onSuccess()
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
