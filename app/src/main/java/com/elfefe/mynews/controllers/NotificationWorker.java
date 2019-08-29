package com.elfefe.mynews.controllers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.elfefe.mynews.R;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.SearchQuery;
import com.elfefe.mynews.utils.NYTCalls;

import java.util.HashMap;
import java.util.Map;

public class NotificationWorker extends Worker {
    public static final String KEY_SEARCH = "key_search";
    public static final String KEY_SECTION = "key_section";
    public static final String PREF_NAME= "notification_preferences";
    private static final String NOTIF_CHANNEL_ID = "Channel_id";
    private static final int NOTIF_ID = 1;
    private final Context context;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        Map<String, String> query = new HashMap<>();

        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);

        String sections = preferences.getString(KEY_SECTION, null);
        String prefSearch = preferences.getString(KEY_SEARCH, "");

        if (prefSearch != null) {
            query.put("q", prefSearch);
        }

        if (sections != null) {
            query.put("fq", sections);
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

            String title = result.getSnippet().substring(0,15);
            String contenu = result.getAbstract();
            String section = result.getSectionName();
            String date = result.getPubDate().substring(0,10);

            notification
                    .setSmallIcon(R.drawable.news_icon)
                    .setContentTitle(section + "    " + title)
                    .setContentText(contenu)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .setBigContentTitle(section + "    " + title + "    " + date)
                            .bigText(contenu));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "MyNews";
                String description = "MyNews notification";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(NOTIF_CHANNEL_ID, name, importance);
                channel.setDescription(description);

                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                notificationManager
                        .createNotificationChannel(channel);

                notificationManager.notify(NOTIF_ID, notification.build());
            } else {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(NOTIF_ID,notification.build());
            }
        }
        return Result.success();
    }
}
