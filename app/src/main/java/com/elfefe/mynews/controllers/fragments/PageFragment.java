package com.elfefe.mynews.controllers.fragments;

import android.app.AlertDialog;
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
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.News;
import com.elfefe.mynews.utils.NewsAsyncTask;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment implements NewsAsyncTask.Listeners{

    private ArrayList<Article> article;
    PageRecyclerviewAdapter adapter;
    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) result.findViewById(R.id.page_recyclerview);

        new NewsAsyncTask(this).execute("topstories","arts","7beqz304Fmqzmbi3GxAQxanKShTgNCRb");

        article = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return result;
    }

    @Override
    public void onResult(News news) {
        JsonElement jsonTree = new GsonBuilder().create().toJsonTree(news.getResults());

        Type articleType = new TypeToken<List<Article>>() {}.getType();

        article = new GsonBuilder().create().fromJson(jsonTree, articleType);

        Log.d("SIIIIIIIZE", Integer.toString(article.size()));

        Log.d("CONTEEEENT", article.get(3).getTitle());

        adapter = new PageRecyclerviewAdapter(this.getContext(), article);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
