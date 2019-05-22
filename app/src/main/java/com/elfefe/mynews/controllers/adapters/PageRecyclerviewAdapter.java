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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Url;

import static com.elfefe.mynews.R.string.*;

public class PageRecyclerviewAdapter extends RecyclerView.Adapter<PageRecyclerviewAdapter.PageViewHolder> {

    private final int articleMaxLength = 80;
    private final int dateLength = 10;
    private final int zero = 0;

    private Context context;
    private Article article;

    public PageRecyclerviewAdapter(Context context, Article article) {
        this.context = context;
        this.article = article;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PageViewHolder(LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.recyclerview_item, viewGroup,false),
                context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int i) {
        TextView date = holder.view.findViewById(R.id.news_item_date);
        TextView title = holder.view.findViewById(R.id.news_item_title);
        TextView section = holder.view.findViewById(R.id.news_item_news);
        ImageView img = holder.view.findViewById(R.id.news_item_img);

        if(article.getMultimedia() == null) {
            String dateQuery = article.getDate();
            String titleQuery = article.getTitle();
            String sectionQuery = article.getSection();
            if (article.getSection() != null)
                titleQuery = article.getSection() + context.getString(item_title_separator) + article.getSection();
            else
                titleQuery = null;
            if (article.getArticle().length() > articleMaxLength)
                sectionQuery = article.getArticle().substring(zero, articleMaxLength) + context.getString(item_article_following);
            else
                sectionQuery = article.getArticle();

            if (article.getMultimedia() != null) {
                LinkedTreeMap<String, String> imgQuery = (LinkedTreeMap<String, String>) article.getMultimedia()[0];
                Picasso.get().load(imgQuery.get("url")).into(img);
            } else if (article.getMedia() != null) {
                LinkedTreeMap<String, String> imgQuery = (LinkedTreeMap<String, String>) article.getMedia()[0];
                Picasso.get().load(imgQuery.get("url")).into(img);
            }


            date.setText(dateQuery);
            title.setText(titleQuery);
            section.setText(sectionQuery);

        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("url", article.getUrl());

        holder.view.setOnClickListener(view -> context.startActivity(intent));
        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }


    static class PageViewHolder extends RecyclerView.ViewHolder {
        View view;

        PageViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            view = itemView;
        }
    }
}
