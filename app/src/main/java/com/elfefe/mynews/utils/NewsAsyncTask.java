package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class NewsAsyncTask extends AsyncTask<String,Void,String> {

    private final WeakReference<Listeners> callback;

    public NewsAsyncTask(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    public interface Listeners{
        void onPreExecute();
        void onPostExecute(String success);
        void doInBackground();
    }

    @Override
    protected String doInBackground(String... url) {
        this.callback.get().doInBackground();
        return NewsHttpURLConnection.startHttprequest(url[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
    }

    @Override
    protected void onPostExecute(String success) {
        super.onPostExecute(success);
        this.callback.get().onPostExecute(success);
    }

}

