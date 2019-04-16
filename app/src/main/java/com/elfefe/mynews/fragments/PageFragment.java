package com.elfefe.mynews.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.adapters.PageRecyclerviewAdapter;

public class PageFragment extends Fragment {

    public PageFragment() { }

    public static PageFragment newInstance(){
        PageFragment frag = new PageFragment();

        Bundle args = new Bundle();

        frag.setArguments(args);

        return  frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);

        ConstraintLayout layoutView = (ConstraintLayout) result.findViewById(R.id.page_layout);
        RecyclerView recyclerView = (RecyclerView) result.findViewById(R.id.page_recyclerview);

        recyclerView.setAdapter(new PageRecyclerviewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return result;
    }
}
