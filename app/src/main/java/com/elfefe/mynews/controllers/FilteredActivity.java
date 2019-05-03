package com.elfefe.mynews.controllers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageRecyclerviewAdapter;
import com.elfefe.mynews.models.News;
import com.elfefe.mynews.utils.NewsAsyncTask;

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

        new NewsAsyncTask(this).execute("Sports");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResult(News news) {
        PageRecyclerviewAdapter adapter = new PageRecyclerviewAdapter(this, news);
        recyclerView.setAdapter(adapter);
    }
}
