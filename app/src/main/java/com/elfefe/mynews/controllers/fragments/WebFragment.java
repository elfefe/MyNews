package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.elfefe.mynews.R;

import java.util.ArrayList;

public class WebFragment extends Fragment {

    public WebFragment() { }

    public static WebFragment newInstance(ArrayList<String> titles){
        WebFragment frag = new WebFragment();

        Bundle args = new Bundle();

        frag.setArguments(args);

        return  frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_web, container, false);

        WebView webView = result.findViewById(R.id.web_layout);

        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return result;
    }
}
