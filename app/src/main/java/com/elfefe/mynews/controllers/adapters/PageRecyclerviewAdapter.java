package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elfefe.mynews.R;

import java.util.ArrayList;

public class PageRecyclerviewAdapter extends RecyclerView.Adapter<PageRecyclerviewAdapter.PageViewHolder> {

    private ArrayList<String> titles;

    public PageRecyclerviewAdapter(ArrayList<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        PageViewHolder holder = new PageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup,false));
        TextView title = holder.view.findViewById(R.id.news_item_title);
        title.setText(titles.get(i));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder pageViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    static class PageViewHolder extends RecyclerView.ViewHolder {
        View view;

        PageViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            view.setOnClickListener(v -> Log.d("[********]",view.getContext().getClass().getName()));
        }
    }
}
