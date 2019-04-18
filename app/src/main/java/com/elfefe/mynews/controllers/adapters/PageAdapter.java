package com.elfefe.mynews.controllers.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.elfefe.mynews.controllers.fragments.PageFragment;
import com.elfefe.mynews.models.News;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {


    private final ArrayList<String> pageTitle;
    private List<News> titles;

    public PageAdapter(FragmentManager fm, ArrayList<String> pageTitle, List<News> titles) {
        super(fm);

        this.pageTitle = pageTitle;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return PageFragment.newInstance(titles);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle.get(position);
    }
}
