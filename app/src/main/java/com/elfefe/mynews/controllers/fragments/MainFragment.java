package com.elfefe.mynews.controllers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageAdapter;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.News;
import com.elfefe.mynews.utils.NewsAsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment implements NewsAsyncTask.Listeners{

    ViewPager pager;
    PageAdapter adapter;
    List<Article> news;
    ArrayList<String> pageTitles;
    TabLayout tabs;



    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_main, container, false);

        pager = (ViewPager) result.findViewById(R.id.main_viewpager);
        tabs = (TabLayout) result.findViewById(R.id.main_tablayout);

        news = new ArrayList<>();
        pageTitles = new ArrayList<>();

        new NewsAsyncTask(this).execute("topstories","arts","7beqz304Fmqzmbi3GxAQxanKShTgNCRb");

        pageTitles.add(getString(R.string.page_title_topstories));
        pageTitles.add(getString(R.string.page_title_mostpopular));
        pageTitles.add(getString(R.string.page_title_favorites));

        adapter = new PageAdapter(getActivity().getSupportFragmentManager(),
                pageTitles,
                news);

        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        return result;
    }

    @Override
    public void onResult(News news) {
        Type typeArticle = new TypeToken<List<Article>>(){}.getType();
        JsonElement jsonArticle = new GsonBuilder().create().toJsonTree(news.getResults());

        List<Article> article = new GsonBuilder().create().fromJson(jsonArticle, typeArticle);

        this.news.addAll(article);
        adapter.notifyDataSetChanged();
    }


}
