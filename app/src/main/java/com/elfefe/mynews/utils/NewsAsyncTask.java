package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.News;

import java.lang.ref.WeakReference;
import java.util.List;

public class NewsAsyncTask extends AsyncTask<String,Void, List<News>> {

    private final WeakReference<Listeners> callback;

    public NewsAsyncTask(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    public interface Listeners{
        void onResult(List<News> news);
    }

    @Override
    protected List<News> doInBackground(String... url) {
        NYTCalls nytCalls = new NYTCalls();
        return nytCalls.fetchTitleFollowing(url[0]);
    }

    @Override
    protected void onPostExecute(List<News> news) {
        callback.get().onResult(news);
    }
}

