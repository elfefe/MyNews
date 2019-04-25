package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.ArticleActivity;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.News;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Url;

public class PageRecyclerviewAdapter extends RecyclerView.Adapter<PageRecyclerviewAdapter.PageViewHolder> {

    private List<Article> news;
    private Context context;

    public PageRecyclerviewAdapter(Context context, List<Article> news) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PageViewHolder(LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.recyclerview_item, viewGroup,false),
                context);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int i) {
        TextView date = holder.view.findViewById(R.id.news_item_date);
        TextView title = holder.view.findViewById(R.id.news_item_title);
        TextView article = holder.view.findViewById(R.id.news_item_news);
        ImageView img = holder.view.findViewById(R.id.news_item_img);

        String dateQuery = news.get(i).getDate().substring(0, 10);
        String titleQuery = news.get(i).getTitle().substring(0, 20) + "...";
        String articleQuery = news.get(i).getArticle();

        LinkedTreeMap<String, String> imgQuery =(LinkedTreeMap<String, String>) news.get(i).getMultimedia()[0];

        date.setText(dateQuery);
        title.setText(titleQuery);
        article.setText(articleQuery);
        Picasso.get().load(imgQuery.get("url")).into(img);


        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("url", news.get(i).getUrl());

        holder.view.setOnClickListener(view -> context.startActivity(intent));

    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    static class PageViewHolder extends RecyclerView.ViewHolder {
        View view;

        PageViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            view = itemView;
        }
    }
}
