package com.elfefe.mynews;

import com.elfefe.mynews.controllers.activity.MainActivity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Before
    public void setup(){
        ActivityController<MainActivity> mainActivity = Robolectric.buildActivity(MainActivity.class).setup();
    }

}
