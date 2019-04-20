package com.elfefe.mynews.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.elfefe.mynews.R;

public class SearchActivity extends AppCompatActivity {

    EditText editText;
    AppCompatCheckBox arts, buisness, entrepreneurs, politics, sports, travel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.search_search);
        arts = (AppCompatCheckBox) findViewById(R.id.search_cb_arts);
        buisness = (AppCompatCheckBox) findViewById(R.id.search_cb_buisness);
        entrepreneurs = (AppCompatCheckBox) findViewById(R.id.search_cb_entrepreneurs);
        politics = (AppCompatCheckBox) findViewById(R.id.search_cb_politics);
        sports = (AppCompatCheckBox) findViewById(R.id.searche_cb_sports);
        travel = (AppCompatCheckBox) findViewById(R.id.search_cb_travel);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_settings:

                return true;
            case R.id.main_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
