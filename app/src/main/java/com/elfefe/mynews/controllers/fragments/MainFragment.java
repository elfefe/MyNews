package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageAdapter;
import com.elfefe.mynews.models.News;
import com.elfefe.mynews.utils.NewsAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment implements NewsAsyncTask.Listeners{

    ViewPager pager;
    List<News> news = new ArrayList<>();
    ArrayList<String> pageTitles = new ArrayList<>();

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_main, container, false);

        pager = (ViewPager) result.findViewById(R.id.main_viewpager);

        new NewsAsyncTask(this).execute("JakeWharton");

        pageTitles.add(getString(R.string.page_title_topstories));
        pageTitles.add(getString(R.string.page_title_mostpopular));
        pageTitles.add(getString(R.string.page_title_favorites));

        News test = new News();
        test.setTitle("TEST");

        news.add(test);

        Log.d("[********]",news.get(0).getTitle());

        pager.setAdapter(new PageAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                pageTitles,
                news));

        TabLayout tabs = (TabLayout) result.findViewById(R.id.main_tablayout);

        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);

        return result;
    }

    @Override
    public void onResult(List<News> news) {
        this.news = news;
        pager.getAdapter().notifyDataSetChanged();
    }
}
