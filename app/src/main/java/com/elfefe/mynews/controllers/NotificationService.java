package com.elfefe.mynews.controllers;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;

import com.bumptech.glide.Glide;
import com.elfefe.mynews.R;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.utils.NotificationAsyncTask;

import java.util.List;
import java.util.Objects;

public class NotificationService extends Service implements NotificationAsyncTask.Listeners{
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "notif_channel_id";

    private String title, section,contenu;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setContentTitle(section + " " + title)
                .setContentText(contenu)
                .setContentIntent(pendingIntent)
                .build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Search search = intent.getParcelableExtra(SearchActivity.KEY_SEARCH);

        new NotificationAsyncTask(this ).execute(search);

        startForeground();

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onResult(Article article) {
        title = article.getTitle();
        section = article.getSection();
        contenu = article.getArticle();
    }
}
