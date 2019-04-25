package com.elfefe.mynews.controllers;

import android.content.Intent;
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
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        EditText text = (EditText) findViewById(R.id.search_search);
        AppCompatCheckBox arts = (AppCompatCheckBox) findViewById(R.id.search_cb_arts);
        AppCompatCheckBox buisness = (AppCompatCheckBox) findViewById(R.id.search_cb_buisness);
        AppCompatCheckBox entrepreneurs = (AppCompatCheckBox) findViewById(R.id.search_cb_entrepreneurs);
        AppCompatCheckBox politics = (AppCompatCheckBox) findViewById(R.id.search_cb_politics);
        AppCompatCheckBox sports = (AppCompatCheckBox) findViewById(R.id.searche_cb_sports);
        AppCompatCheckBox travel = (AppCompatCheckBox) findViewById(R.id.search_cb_travel);
        Button search = (Button) findViewById(R.id.search_button_search);

        Bundle bundle = new Bundle();

        String searched = text.getText().toString();

        boolean[] options = {
                arts.isChecked(),
                buisness.isChecked(),
                entrepreneurs.isChecked(),
                politics.isChecked(),
                sports.isChecked(),
                travel.isChecked()
        };

        bundle.putString("searched", searched);
        bundle.putBooleanArray("options", options);

        search.setOnClickListener(v -> {
            
            Log.d("BUNNNNDLED3", searched);

            getIntent().putExtra("bundle",bundle);
            setResult(RESULT_OK, getIntent());
            finish();
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
