package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.News;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

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
        switch (url[0]){
            case "Top Stories":
                return nytCalls.fetchTopStoriesFollowing();
            case "Most Popular":
                return nytCalls.fetchMostPopularFollowing();
            case "Sports":
                return nytCalls.fetchFavoriteFollowing("sports");
            case "Search Article":
                Map<String, String> map = new HashMap<>();
                map.put("q", url[1]);
                return  nytCalls.fetchSearchArticleFollowing(map);
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(News news) {
        callback.get().onResult(news);
    }
}

