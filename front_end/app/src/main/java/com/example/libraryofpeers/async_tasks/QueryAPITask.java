package com.example.libraryofpeers.async_tasks;

import android.os.AsyncTask;

import com.example.libraryofpeers.view.utils.APICommunicator;

public class QueryAPITask extends AsyncTask<String, Void, APICommunicator.BookResult> {
    private final QueryAPITask.QueryAPIObserver observer;

    public QueryAPITask(QueryAPITask.QueryAPIObserver observer) {
        this.observer = observer;
    }


    public interface QueryAPIObserver {
        void onQuerySuccess(APICommunicator.BookResult response);
        void onQueryFailure(APICommunicator.BookResult response);
    }

    @Override
    protected APICommunicator.BookResult doInBackground(String... requests) {
        return new APICommunicator().getBookFromISBN(requests[0]);
    }

    @Override
    protected void onPostExecute(APICommunicator.BookResult response) {
        if (response != null) {
            observer.onQuerySuccess(response);
        } else {
            observer.onQueryFailure(response);
        }
    }
}
