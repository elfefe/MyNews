package com.elfefe.mynews;

import com.elfefe.mynews.controllers.activity.ArticleActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ArticleActivityTest {
    private ArticleActivity articleActivity;

    @Before
    public void setup(){
        articleActivity = Robolectric
                .buildActivity(ArticleActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void supportActionBarIsNotNull(){
        Assert.assertNotNull(articleActivity.getSupportActionBar());
    }
}
