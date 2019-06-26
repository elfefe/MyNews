package com.elfefe.mynews;

import com.elfefe.mynews.controllers.MainActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    public void getSupportFragmentManger_not_null_after_initiate() throws Exception{
        mainActivity.start().create();

        Assert.assertNotNull(mainActivity.get().getSupportFragmentManager().findFragmentById(R.id.framelayout_main));

    }
}
