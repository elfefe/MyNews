package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageAdapter;
import com.elfefe.mynews.models.News;
import com.elfefe.mynews.utils.NewsAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements NewsAsyncTask.Listeners{

    ViewPager pager;
    List<News> titles = new ArrayList<>();
    ArrayList<String> pageTitles = new ArrayList<>();

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_main, container,false);

        pager = (ViewPager) result.findViewById(R.id.main_viewpager);

        pager.setAdapter(new PageAdapter(getActivity().getSupportFragmentManager(), pageTitles, titles));

        new NewsAsyncTask(this).execute("JakeWharton");

        pageTitles.add("TopStories");
        pageTitles.add("Most Popular");
        pageTitles.add("Favorites");


        TabLayout tabs = (TabLayout) result.findViewById(R.id.main_tablayout);

        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);

        return result;
    }

    @Override
    public void onResult(List<News> nytTitles) {
        titles = nytTitles;
        pager.getAdapter().notifyDataSetChanged();
    }
}
