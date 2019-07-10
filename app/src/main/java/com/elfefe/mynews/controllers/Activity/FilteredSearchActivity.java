package com.elfefe.mynews.controllers.Activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageRecyclerviewAdapter;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.utils.SearchAsyncTask;

import java.util.List;

public class FilteredSearchActivity extends AppCompatActivity implements SearchAsyncTask.Listeners{
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered);

        Toolbar toolbar = findViewById(R.id.filtered_toolbar);
        recyclerView = findViewById(R.id.filtered_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Search search = getIntent().getParcelableExtra(SearchActivity.KEY_SEARCH);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        if(search != null) {
            new SearchAsyncTask( this).execute(search);
        }
    }

    @Override
    public void onResult(List<Article> articles) {
        PageRecyclerviewAdapter adapter = new PageRecyclerviewAdapter(this, articles);
        recyclerView.setAdapter(adapter);
    }
}
