package com.elfefe.mynews;

import android.webkit.WebView;

import com.elfefe.mynews.controllers.ArticleActivity;
import com.elfefe.mynews.controllers.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

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

    @After
    public void tearDown(){}

    @Test
    public void webView_load_view(){
        WebView webView = articleActivity.findViewById(R.id.article_webview);


    }
}
