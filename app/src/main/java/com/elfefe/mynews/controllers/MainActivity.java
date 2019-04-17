package com.elfefe.mynews.controllers;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;

import com.elfefe.mynews.R;
import com.elfefe.mynews.adapters.PageAdapter;
import com.elfefe.mynews.utils.NewsHandlerThread;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NewsHandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.main_viewpager);

        ArrayList<String> pageTitles = new ArrayList<>();
        pageTitles.add("TopStories");
        pageTitles.add("Most Popular");
        pageTitles.add("Favorites");

        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), pageTitles));

        TabLayout tabs = (TabLayout) findViewById(R.id.main_tablayout);

        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);

        handlerThread = new NewsHandlerThread("Loading ...", new ProgressBar(this));
        handlerThread.startHandler();
    }

    @Override
    protected void onDestroy() {
        handlerThread.quit();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
}
