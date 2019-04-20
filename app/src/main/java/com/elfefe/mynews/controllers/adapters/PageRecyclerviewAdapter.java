package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elfefe.mynews.R;
import com.elfefe.mynews.models.News;

import java.util.List;

public class PageRecyclerviewAdapter extends RecyclerView.Adapter<PageRecyclerviewAdapter.PageViewHolder> {

    private List<News> news;

    public PageRecyclerviewAdapter(List<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        PageViewHolder holder = new PageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup,false),news.get(i).getTitle());
        TextView title = holder.view.findViewById(R.id.news_item_title);
        title.setText(news.get(i).getTitle());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder pageViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    static class PageViewHolder extends RecyclerView.ViewHolder {
        View view;
        private String text;

        PageViewHolder(@NonNull View itemView, String text) {
            super(itemView);

            view = itemView;
            this.text = text;
        }
    }
}