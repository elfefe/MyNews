package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageRecyclerviewAdapter;
import com.elfefe.mynews.models.News;
import com.elfefe.mynews.utils.NewsAsyncTask;

public class PageFragment extends Fragment implements NewsAsyncTask.Listeners {

    private String title;
    private RecyclerView recyclerView;

    public PageFragment() {
    }

    public static PageFragment newInstance(String title) {
        PageFragment frag = new PageFragment();
        frag.title = title;
        if(title != null)
            Log.d("TIIIITLE_newInstance", title);

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

        if(title != null) {
            Log.d("TIIIITLE_onCreate", title);

            new NewsAsyncTask(this).execute(title);
        }

        return result;
    }

    @Override
    public void onResult(News news) {


        PageRecyclerviewAdapter adapter = new PageRecyclerviewAdapter(this.getContext(), news);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
