package com.elfefe.mynews.controllers.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class PageFragmentTest {

    private FragmentActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.buildActivity( FragmentActivity.class )
                .create()
                .start()
                .resume()
                .get();
    }

    @After
    public void  tearDown(){

    }

    @Test
    public void pageFragmentAndArgumentIsNotNull(){
        Fragment pageFragment = PageFragment.newInstance(Pages.TOP_STORIES);

        startFragment(pageFragment);
        Assert.assertNotNull(pageFragment);
        Assert.assertNotNull(pageFragment.getArguments());
    }

    private void startFragment(Fragment fragment) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add( fragment, null );
        fragmentTransaction.commit();
    }

}