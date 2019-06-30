package com.elfefe.mynews.controllers;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_main);

        if(mainFragment == null){
            mainFragment = new MainFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.framelayout_main, mainFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_notif:
                startActivity(new Intent(this,NotificationActivity.class));
                return true;
            case R.id.main_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
