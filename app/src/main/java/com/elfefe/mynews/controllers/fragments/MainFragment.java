package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageAdapter;
import com.elfefe.mynews.models.NYTTitle;
import com.elfefe.mynews.utils.NYTCalls;
import com.elfefe.mynews.utils.NewsAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements NewsAsyncTask.Listeners, NYTCalls.CallBacks {

    ViewPager pager;
    ArrayList<String> titles = new ArrayList<>();

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_main, container,false);

        pager = (ViewPager) result.findViewById(R.id.main_viewpager);

        new NewsAsyncTask(this).execute("https://api.github.com/users/JakeWharton/following");

        ArrayList<String> pageTitles = new ArrayList<>();

        pageTitles.add("TopStories");
        pageTitles.add("Most Popular");
        pageTitles.add("Favorites");

        if(titles.isEmpty())
            titles.add("echec");
        else
            Log.d("ERREUREUREEEUURER", Integer.toString(titles.size()));

        if(getActivity().getSupportFragmentManager() != null)
            pager.setAdapter(new PageAdapter(getActivity().getSupportFragmentManager(), pageTitles, titles));

        TabLayout tabs = (TabLayout) result.findViewById(R.id.main_tablayout);

        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);

        NYTCalls.fetchTitleFollowing(this, "JakeWharton");

        return result;
    }

    @Override
    public void onPreExecute() {
        Log.d("*****PreExecute:", "Loading data.");
    }

    @Override
    public void onPostExecute(String success) {
        Log.d("|||||||||| PostExecute:", success);
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onResponse(@Nullable List<NYTTitle> titles) {
        if (titles != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (NYTTitle title : titles) {
                stringBuilder.append(" - ").append(title.getTitle()).append("\n");
            }
            result(titles.get(0).getTitle());
        }
    }

    @Override
    public void onFailure() {
        result("failed");
    }

    private void result(String result){
        Log.d("******** RESULT :", result);
    }
}
