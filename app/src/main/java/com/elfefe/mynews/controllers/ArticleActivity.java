package com.elfefe.mynews.controllers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.elfefe.mynews.R;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Toolbar toolbar = findViewById(R.id.article_toolbar);
        WebView webView = findViewById(R.id.article_webview);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
