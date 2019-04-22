package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.ArticleActivity;
import com.elfefe.mynews.models.News;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageRecyclerviewAdapter extends RecyclerView.Adapter<PageRecyclerviewAdapter.PageViewHolder> {

    private List<News> news;
    private Context context;

    public PageRecyclerviewAdapter(Context context,List<News> news) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        PageViewHolder holder = new PageViewHolder(LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.recyclerview_item, viewGroup,false),
                news.get(i).getTitle(),
                context);

        TextView date = holder.view.findViewById(R.id.news_item_date);
        TextView title = holder.view.findViewById(R.id.news_item_title);

        LinkedTreeMap<String, String> results = (LinkedTreeMap<String, String>) news.get(i).getResults()[0];

        String string = String.format("%s" ,results.get("title"));

        date.setText(news.get(i).getDate());
        title.setText(results.get(string));
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

        PageViewHolder(@NonNull View itemView, String text, Context context) {
            super(itemView);

            view = itemView;

            view.setOnClickListener(view -> context.startActivity(new Intent(context, ArticleActivity.class)));
        }
    }
}
