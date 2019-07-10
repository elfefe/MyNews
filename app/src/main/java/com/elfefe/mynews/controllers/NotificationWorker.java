package com.elfefe.mynews.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.elfefe.mynews.R;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.SearchQuery;
import com.elfefe.mynews.utils.NYTCalls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NotificationWorker extends Worker {
    public static final String KEY_SEARCH = "key_search";
    public static final String KEY_SECTION = "key_section";
    public static final String PREF_NAME= "notification_preferences";
    private static final String NOTIF_CHANNEL_ID = "Channel_id";
    private static final int NOTIF_ID = 1;
    private Context context;
   /* String searchQuery;
    String[] sections;*/

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        //searchQuery = workerParams.getInputData().getString(KEY_SEARCH);
        //sections = workerParams.getInputData().getStringArray(KEY_SECTION);
    }

    @NonNull
    @Override
    public Result doWork() {

        Map<String, String> query = new HashMap<>();

        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);

        Set<String> sectionSet = preferences.getStringSet(KEY_SECTION, null);
        String prefSearch = preferences.getString(KEY_SEARCH, "");

        ArrayList<String> sectionList = new ArrayList<>();

        if (sectionSet != null) {
            sectionList = new ArrayList<>(sectionSet);
        }

        if (prefSearch != null) {
            query.put("q", prefSearch);
        }

        String sections = NotificationUrl.createUrl(sectionList).getUrl();

        query.put("fq", sections);

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

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(NOTIF_ID, notification
                    .setOngoing(true)
                    .setSmallIcon(R.drawable.baseline_arrow_back_24)
                    .setContentTitle(section + " " + title)
                    .setContentText(contenu)
                    .build());
        }

        return Result.success();
    }
}
