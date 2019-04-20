package com.elfefe.mynews.controllers.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment {

    private List<News> news;

    public PageFragment() { }

    public static PageFragment newInstance(List<News> news){
        PageFragment frag = new PageFragment();

        frag.news = news;


        Bundle args = new Bundle();

        frag.setArguments(args);

        return  frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);

        RecyclerView recyclerView = (RecyclerView) result.findViewById(R.id.page_recyclerview);

        recyclerView.setAdapter(new PageRecyclerviewAdapter(this.getContext(),news));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return result;
    }
}
