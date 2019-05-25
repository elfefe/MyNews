package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.elfefe.mynews.controllers.fragments.PageFragment;
import com.elfefe.mynews.models.Pages;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {


    private final Pages[] pages;
    private Context context;
    private List<Fragment> listPage = new ArrayList<>();

    public PageAdapter(FragmentManager fm, Pages[] pages, Context context) {
        super(fm);

        this.pages = pages;
        this.context = context;

        for (Pages page : pages) {
            listPage.add(PageFragment.newInstance(page));
        }
    }

    @Override
    public Fragment getItem(int i) {
        return listPage.get(i);
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(pages[position].getTitle());
    }
}