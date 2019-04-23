package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.News;

import java.lang.ref.WeakReference;

public class NewsAsyncTask extends AsyncTask<String,Void, News> {

    private final WeakReference<Listeners> callback;
    public NewsAsyncTask(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    public interface Listeners{
        void onResult(News news);
    }

    @Override
    protected News doInBackground(String... url) {
        NYTCalls nytCalls = new NYTCalls();
        return nytCalls.fetchTopStoriesFollowing(url[0],url[1], url[2]);
    }

    @Override
    protected void onPostExecute(News news) {
        callback.get().onResult(news);
    }
}

