package com.elfefe.mynews.controllers.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.NotificationWorker;
import com.elfefe.mynews.utils.SectionLuceneFormator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotificationActivity extends AppCompatActivity{

    private EditText text;
    private AppCompatCheckBox arts;
    private AppCompatCheckBox buisness;
    private AppCompatCheckBox technology;
    private AppCompatCheckBox health;
    private AppCompatCheckBox sports;
    private AppCompatCheckBox science;
    private Button search;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_query);

        Toolbar toolbar = findViewById(R.id.query_toolbar);
        setSupportActionBar(toolbar);

        text = findViewById(R.id.query_query);
        arts = findViewById(R.id.query_cb_arts);
        buisness = findViewById(R.id.query_cb_buisness);
        technology = findViewById(R.id.query_cb_technology);
        health = findViewById(R.id.query_cb_health);
        sports = findViewById(R.id.querye_cb_sports);
        science = findViewById(R.id.query_cb_science);
        search = findViewById(R.id.query_button_search);

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
                if (technology.isChecked()){add("technology");}
                if (health.isChecked()){add("health");}
                if (sports.isChecked()){add("sports");}
                if (science.isChecked()){add("science");}
            }};

            SectionLuceneFormator sections = new SectionLuceneFormator(sectionsList);

            SharedPreferences preferences = getSharedPreferences(NotificationWorker.PREF_NAME, MODE_PRIVATE);

            String searched = (!text.getText().toString().isEmpty())?text.getText().toString():"";

            preferences
                    .edit()
                    .putString(NotificationWorker.KEY_SECTION,sections.createLucene())
                    .putString(NotificationWorker.KEY_SEARCH,searched)
                    .apply();


            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();

            PeriodicWorkRequest notificationWorker = new PeriodicWorkRequest.Builder(NotificationWorker.class, 1, TimeUnit.DAYS)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(getApplicationContext())
                    .enqueue(notificationWorker);

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
    }
}
