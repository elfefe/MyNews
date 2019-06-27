package com.elfefe.mynews.controllers;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.impl.model.WorkTypeConverters;

import android.widget.Button;
import android.widget.EditText;

import com.elfefe.mynews.R;
import com.elfefe.mynews.models.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotificationActivity extends AppCompatActivity{

    EditText text;
    AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;
    Button search;
    private Service mService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_query);

        Toolbar toolbar = (Toolbar) findViewById(R.id.query_toolbar);
        setSupportActionBar(toolbar);

        text = (EditText) findViewById(R.id.query_query);
        arts = (AppCompatCheckBox) findViewById(R.id.query_cb_arts);
        buisness = (AppCompatCheckBox) findViewById(R.id.query_cb_buisness);
        entrepreneurs = (AppCompatCheckBox) findViewById(R.id.query_cb_entrepreneurs);
        politics = (AppCompatCheckBox) findViewById(R.id.query_cb_politics);
        sports = (AppCompatCheckBox) findViewById(R.id.querye_cb_sports);
        travel = (AppCompatCheckBox) findViewById(R.id.query_cb_travel);
        search = (Button) findViewById(R.id.query_button_query);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        search.setOnClickListener(v -> {
            List<String> sectionsList = new ArrayList<String>(){{
                if(arts.isChecked()){add("arts");}
                if (buisness.isChecked()){add("business");}
                if (entrepreneurs.isChecked()){add("entrepreneurs");}
                if (politics.isChecked()){add("politics");}
                if (sports.isChecked()){add("sports");}
                if (travel.isChecked()){add("search");}
            }};

            String[] sections = new String[sectionsList.size()];
            sectionsList.toArray(sections);

            Data data = new Data.Builder()
                    .putStringArray(NotificationWorker.KEY_SECTION,  sections)
                    .putString(NotificationWorker.KEY_SEARCH,text.getText().toString())
                    .build();

            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();

            PeriodicWorkRequest notificationWorker = new PeriodicWorkRequest.Builder(NotificationWorker.class, 1, TimeUnit.DAYS)
                    .setConstraints(constraints)
                    .setInputData(data)
                    .build();

            WorkManager.getInstance()
                    .enqueue(notificationWorker);
        });
    }
}
