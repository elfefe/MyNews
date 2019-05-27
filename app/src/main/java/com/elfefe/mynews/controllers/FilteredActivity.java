package com.elfefe.mynews.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageRecyclerviewAdapter;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Pages;
import com.elfefe.mynews.utils.NewsAsyncTask;

import java.util.List;

public class FilteredActivity extends AppCompatActivity implements NewsAsyncTask.Listeners{
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_filtered);

        Toolbar toolbar = (Toolbar) findViewById(R.id.filtered_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.filtered_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();

        Log.d( "********|:", "-------   -;'");
        if (bundle != null)
            Log.d( "onCreate: ", bundle.getString(SearchActivity.KEY_SEARCHED));

        new NewsAsyncTask(this).execute(Pages.FILTERED);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResult(List<Article> articles) {
        PageRecyclerviewAdapter adapter = new PageRecyclerviewAdapter(this, articles);
        recyclerView.setAdapter(adapter);
    }
}
