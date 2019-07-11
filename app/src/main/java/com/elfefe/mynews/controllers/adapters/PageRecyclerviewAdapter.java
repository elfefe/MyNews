package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.Activity.ArticleActivity;
import com.elfefe.mynews.models.Article;

import java.util.List;

public class PageRecyclerviewAdapter extends RecyclerView.Adapter<PageRecyclerviewAdapter.PageViewHolder> {

    private Context context;
    private List<Article> articles;

    public PageRecyclerviewAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PageViewHolder(LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.recyclerview_adapter_item, viewGroup,false),
                context);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int i) {
        TextView date = holder.view.findViewById(R.id.news_item_date);
        TextView title = holder.view.findViewById(R.id.news_item_title);
        TextView section = holder.view.findViewById(R.id.news_item_section);
        TextView news = holder.view.findViewById(R.id.news_item_news);
        ImageView img = holder.view.findViewById(R.id.news_item_img);

        Article article = articles.get(i);

        date.setText(article.getDate());
        title.setText(article.getTitle());
        section.setText(article.getSection());
        news.setText(article.getArticle());
        if (article.getMultimediaUrl() != null) {
            Glide.with(holder.view).load(article.getMultimediaUrl()).into(img);
        }

        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("url", article.getUrl());

        holder.view.setOnClickListener(view -> context.startActivity(intent));
    }



    @Override
    public int getItemCount() {
        return articles.size();
    }


    static class PageViewHolder extends RecyclerView.ViewHolder {
        View view;

        PageViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            view = itemView;
        }
    }
}
