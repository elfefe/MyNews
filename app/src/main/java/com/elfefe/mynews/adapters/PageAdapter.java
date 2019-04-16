package com.elfefe.mynews.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.elfefe.mynews.fragments.PageFragment;

import java.util.ArrayList;

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
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle.get(position);
    }
}
