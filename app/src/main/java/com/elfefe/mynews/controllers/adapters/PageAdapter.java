package com.elfefe.mynews.controllers.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.elfefe.mynews.controllers.fragments.PageFragment;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.News;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {


    private final ArrayList<String> pageTitle;

    public PageAdapter(FragmentManager fm, ArrayList<String> pageTitle) {
        super(fm);

        this.pageTitle = pageTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return PageFragment.newInstance();
    }

    @Override
    public int getCount() {
        return pageTitle.size();
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
