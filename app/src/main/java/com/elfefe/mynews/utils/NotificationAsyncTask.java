package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.models.notification.Multimedium;
import com.elfefe.mynews.models.notification.NotificationQuery;
import com.elfefe.mynews.models.notification.Result;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationAsyncTask extends AsyncTask<Search,Void, Article> {

    private final WeakReference<NotificationAsyncTask.Listeners> callback;

    public NotificationAsyncTask(NotificationAsyncTask.Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    public interface Listeners{
        void onResult(Article articles);
    }

    @Override
    protected Article doInBackground(Search... url) {
        NYTCalls nytCalls = new NYTCalls();
        Map<String, String> map = new HashMap<>();

        Search search = url[0];

        Article notificationArticle = new Article();
        NotificationQuery notificationResponse = nytCalls.fetchNotificationFollowing(search.getSearch(),search.getSections().get(0));

        for (Result result : notificationResponse.getResults()){
            notificationArticle = loadArticle(result);
        }
        return notificationArticle;
    }

    @Override
    protected void onPostExecute(Article articles) {
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
                if (multimedia.getFormat().equals("Normal")){
                    article.setMultimediaUrl(multimedia.getUrl());
                    break;
                }
            }
        }
        return article;
    }
}
