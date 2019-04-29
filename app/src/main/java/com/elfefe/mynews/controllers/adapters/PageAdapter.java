package com.elfefe.mynews.controllers.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.elfefe.mynews.controllers.fragments.PageFragment;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.News;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {


    private final ArrayList<String> pageTitle;
    private List<Fragment> listPage = new ArrayList<>();

    public PageAdapter(FragmentManager fm, ArrayList<String> pageTitle) {
        super(fm);

        this.pageTitle = pageTitle;

        for (String title : pageTitle) {
            Log.d("TIIIITLE_PageAdapter", title);
            listPage.add(PageFragment.newInstance(title));
        }
    }

    @Override
    public Fragment getItem(int i) {
        return listPage.get(i);
    }

    @Override
    public int getCount() {
        return pageTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle.get(position);
    }
}