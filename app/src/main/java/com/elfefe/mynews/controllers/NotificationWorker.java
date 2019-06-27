package com.elfefe.mynews.controllers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Notification;
import com.elfefe.mynews.models.notification.Multimedium;
import com.elfefe.mynews.models.notification.NotificationQuery;
import com.elfefe.mynews.models.notification.Result;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.SearchQuery;
import com.elfefe.mynews.utils.NYTCalls;

import java.util.HashMap;
import java.util.Map;

public class NotificationWorker extends Worker {
    public static final String KEY_SEARCH = "key_search";
    public static final String KEY_SECTION = "key_section";
    private static final String NOTIF_CHANNEL_ID = "Channel_id";
    private Context context;
    String searchQuery;
    String[] sections;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        searchQuery = workerParams.getInputData().getString(KEY_SEARCH);
        sections = workerParams.getInputData().getStringArray(KEY_SECTION);
    }

    @NonNull
    @Override
    public Result doWork() {

        Map<String, String> query = new HashMap<>();

        query.put("q", searchQuery);

        for (String section:sections) {
            query.put("fq", section);
        }

        NYTCalls nytCalls = new NYTCalls();
        SearchQuery response = nytCalls.fetchSearchArticleFollowing(query);

        if(response==null){
            return Result.failure();
        }

        if(response.getResponse().getDocs().size() > 0){
            NotificationCompat.Builder notification = new NotificationCompat.Builder(context,
                    NOTIF_CHANNEL_ID);

            Docs result = response.getResponse().getDocs().get(0);

            String title = result.getSnippet();
            String contenu = result.getAbstract();
            String section = result.getSectionName();

            notification
                    .setOngoing(true)
                    .setContentTitle(section + " " + title)
                    .setContentText(contenu)
                    .build();
        }

        return Result.success();
    }


    private Article loadArticle(com.elfefe.mynews.models.notification.Result result){
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
