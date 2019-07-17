package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.elfefe.mynews.controllers.fragments.PageFragment;
import com.elfefe.mynews.models.Pages;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {


    private final List<Pages> pages;
    private final Context context;
    private final List<Fragment> listPage = new ArrayList<>();

    public PageAdapter(FragmentManager fm, List<Pages> pages, Context context) {
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
        return pages.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(pages.get(position).getTitle());
    }
}