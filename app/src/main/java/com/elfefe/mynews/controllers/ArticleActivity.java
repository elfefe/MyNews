package com.elfefe.mynews.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.fragments.MainFragment;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Toolbar toolbar = (Toolbar) findViewById(R.id.article_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.article_framelayout);

        if(mainFragment == null){
            mainFragment = new MainFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.article_framelayout, mainFragment)
                    .commit();
        }
    }
}
