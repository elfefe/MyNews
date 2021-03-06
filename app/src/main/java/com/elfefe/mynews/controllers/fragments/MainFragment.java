package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageAdapter;
import com.elfefe.mynews.models.Pages;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private PageAdapter adapter;
    private int page;


    public static MainFragment newInstance(int page) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.page = page;
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_main, container, false);
        ViewPager pager = result.findViewById(R.id.main_viewpager);
        TabLayout tabs = result.findViewById(R.id.main_tablayout);

        List<Pages> pages = new ArrayList<Pages>(){{
            add(Pages.TOP_STORIES);
            add(Pages.MOST_POPULAR);
            add(Pages.FAVORITE);
        }};

        if(getActivity() != null) {
            adapter = new PageAdapter(getActivity().getSupportFragmentManager(),
                    pages, getContext());
        }

        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        pager.setCurrentItem(page);

        return result;
    }

}
