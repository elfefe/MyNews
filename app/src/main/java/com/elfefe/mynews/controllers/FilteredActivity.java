package com.elfefe.mynews.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageRecyclerviewAdapter;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.utils.PagesAsyncTask;
import com.elfefe.mynews.utils.SearchAsyncTask;

import java.util.List;

public class FilteredActivity extends AppCompatActivity implements PagesAsyncTask.Listeners{
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered);

        Toolbar toolbar = (Toolbar) findViewById(R.id.filtered_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.filtered_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Search search = getIntent().getParcelableExtra(SearchActivity.KEY_SEARCH);

        if(search != null)
        new SearchAsyncTask(this).execute(search);
    }

    @Override
    public void onResult(List<Article> articles) {
        PageRecyclerviewAdapter adapter = new PageRecyclerviewAdapter(this, articles);
        recyclerView.setAdapter(adapter);
    }
}
