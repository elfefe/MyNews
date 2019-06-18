package com.elfefe.mynews.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.elfefe.mynews.controllers.MainActivity;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.models.notification.Multimedium;
import com.elfefe.mynews.models.notification.NotificationQuery;
import com.elfefe.mynews.models.notification.Result;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.SearchQuery;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationAsyncTask extends AsyncTask<Search,Void, List<Article>> {

    private final WeakReference<PagesAsyncTask.Listeners> callback;
    private int pixelDimension;

    public NotificationAsyncTask(PagesAsyncTask.Listeners callback, int pixelDimension) {
        this.callback = new WeakReference<>(callback);
        this.pixelDimension = pixelDimension;
    }

    public interface Listeners{
        void onResult(List<Article> articles);
    }

    @Override
    protected List<Article> doInBackground(Search... url) {
        NYTCalls nytCalls = new NYTCalls();
        Map<String, String> map = new HashMap<>();

        Search search = url[0];

        List<Article> notificationArticle = new ArrayList<>();
        NotificationQuery notificationResponse = nytCalls.fetchNotificationFollowing(search.getSearch(),search.getSections().get(0),0);

        for (Result result : notificationResponse.getResults()){
            notificationArticle.add(loadArticle(result));
        }
        return notificationArticle;
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        callback.get().onResult(articles);
    }

    private Article loadArticle(Result result){
        Article article = new Article();

        article.setTitle(result.getTitle().substring(0,15));
        article.setArticle(result.getAbstract());
        article.setSection(result.getSection());
        article.setUrl(result.getUrl());

        if(result.getMultimedia() != null && result.getMultimedia().size() > 0) {
            for(Multimedium multimedia : result.getMultimedia()){
                if (multimedia.getHeight() >= pixelDimension -5){
                    article.setMultimediaUrl(multimedia.getUrl());
                    break;
                }
            }
        }
        return article;
    }
}
