package com.elfefe.mynews;

import com.elfefe.mynews.controllers.MainActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private ActivityController<MainActivity> mainActivity;

    @Before
    public void setup(){
        mainActivity = Robolectric.buildActivity(MainActivity.class).setup();
    }

    @After
    public void tearDown(){}

    public void item_selection_work(){

    }
}
