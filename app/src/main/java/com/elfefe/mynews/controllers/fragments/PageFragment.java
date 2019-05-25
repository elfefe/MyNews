package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageRecyclerviewAdapter;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Pages;
import com.elfefe.mynews.utils.NewsAsyncTask;

import java.util.List;

public class PageFragment extends Fragment implements NewsAsyncTask.Listeners {

    private RecyclerView recyclerView;
    private Pages page;

    public PageFragment() {
    }

    public static PageFragment newInstance(Pages page) {
        PageFragment frag = new PageFragment();
        frag.page = page;

        Bundle args = new Bundle();

        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);

        recyclerView = (RecyclerView) result.findViewById(R.id.page_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        new NewsAsyncTask(this).execute(page);

        return result;
    }

    @Override
    public void onResult(List<Article> articles) {
        PageRecyclerviewAdapter adapter = new PageRecyclerviewAdapter(this.getContext(), articles);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
