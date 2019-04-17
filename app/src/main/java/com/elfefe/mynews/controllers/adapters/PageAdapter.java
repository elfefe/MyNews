package com.elfefe.mynews.controllers.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.elfefe.mynews.controllers.fragments.PageFragment;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {


    private final ArrayList<String> pageTitle;
    private ArrayList<String> titles;

    public PageAdapter(FragmentManager fm, ArrayList<String> pageTitle, ArrayList<String> titles) {
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle.get(position);
    }
}
