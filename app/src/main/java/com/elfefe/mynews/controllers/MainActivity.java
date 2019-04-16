package com.elfefe.mynews.controllers;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elfefe.mynews.R;
import com.elfefe.mynews.adapters.PageAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.main_viewpager);

        ArrayList<String> pageTitles = new ArrayList<>();
        pageTitles.add("TopStories");
        pageTitles.add("Most Popular");
        pageTitles.add("Favorites");

        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), pageTitles));

        TabLayout tabs = (TabLayout) findViewById(R.id.main_tablayout);

        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
