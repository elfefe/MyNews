package com.elfefe.mynews.controllers;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.elfefe.mynews.R;

public class SearchActivity extends AppCompatActivity {

    public static String KEY_SEARCHED = "SEARCHED";
    public static String KEY_OPTIONS = "OPTIONS";

    EditText text;
    AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;
    Button search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        text = (EditText) findViewById(R.id.search_search);
        arts = (AppCompatCheckBox) findViewById(R.id.search_cb_arts);
        buisness = (AppCompatCheckBox) findViewById(R.id.search_cb_buisness);
        entrepreneurs = (AppCompatCheckBox) findViewById(R.id.search_cb_entrepreneurs);
        politics = (AppCompatCheckBox) findViewById(R.id.search_cb_politics);
        sports = (AppCompatCheckBox) findViewById(R.id.searche_cb_sports);
        travel = (AppCompatCheckBox) findViewById(R.id.search_cb_travel);
        search = (Button) findViewById(R.id.search_button_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        text.setWidth(0);

        Bundle bundle = new Bundle();

        search.setOnClickListener(v -> {

            String searched = text.getText().toString();

            boolean[] options = {
                    arts.isChecked(),
                    buisness.isChecked(),
                    entrepreneurs.isChecked(),
                    politics.isChecked(),
                    sports.isChecked(),
                    travel.isChecked()
            };

            bundle.putString(KEY_SEARCHED, searched);
            bundle.putBooleanArray(KEY_OPTIONS, options);

            Intent intent = new Intent(this, FilteredActivity.class);

            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_back) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
