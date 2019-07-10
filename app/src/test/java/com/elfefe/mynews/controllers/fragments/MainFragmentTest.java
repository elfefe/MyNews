package com.elfefe.mynews.controllers.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.elfefe.mynews.controllers.adapters.PageAdapter;
import com.elfefe.mynews.models.Pages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class MainFragmentTest {
    private FragmentActivity activity;
    private List<Pages> pages;

    @Before
    public void setup(){
        activity = Robolectric.buildActivity( FragmentActivity.class )
                .create()
                .start()
                .resume()
                .get();

        pages = new ArrayList<Pages>(){{
            add(Pages.TOP_STORIES);
            add(Pages.MOST_POPULAR);
            add(Pages.FAVORITE);
        }};
    }

    @After
    public void  tearDown(){

    }

    @Test
    public void mainFragmentIsNotNull(){
        Fragment mainFragment = MainFragment.newInstance();

        startFragment(mainFragment);
        Assert.assertNotNull(mainFragment);
    }

    @Test
    public void getActivityIsNotNull(){
        Assert.assertNotNull(activity);
    }

    @Test
    public void adapterLoadCorrectly(){
        PageAdapter adapter = new PageAdapter(activity.getSupportFragmentManager(), pages, activity.getApplicationContext());

        Assert.assertNotNull(adapter);
    }

    private void startFragment(Fragment fragment) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add( fragment, null );
        fragmentTransaction.commit();
    }

}