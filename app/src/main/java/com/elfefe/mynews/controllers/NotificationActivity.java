package com.elfefe.mynews.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.elfefe.mynews.R;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.utils.NotificationAsyncTask;
import com.elfefe.mynews.utils.PagesAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationActivity extends AppCompatActivity{

    private static final String KEY_SEARCH = "key_search";

    EditText text;
    AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;
    Button search;

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
            Bundle bundle = new Bundle();

            List<String> sections = new ArrayList<String>(){{
                add("arts");
                add("business");
                add("entrepreneurs");
                add("politics");
                add("sports");
                add("search");
            }};

            boolean[] checked = {
                    arts.isChecked(),
                    buisness.isChecked(),
                    entrepreneurs.isChecked(),
                    politics.isChecked(),
                    sports.isChecked(),
                    travel.isChecked(),
            };

            Search searched = new Search(text.getText().toString(),"","",sections,checked);

            bundle.putParcelable(KEY_SEARCH, searched);

            Intent intent = new Intent(this, NotificationService.class);

            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
