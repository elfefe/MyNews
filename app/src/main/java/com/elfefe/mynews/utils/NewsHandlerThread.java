package com.elfefe.mynews.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class NewsHandlerThread extends HandlerThread {

    private WeakReference<ProgressBar> progressBarWeakReference;

    public NewsHandlerThread(String name, ProgressBar progressBar) {
        super(name);
        progressBarWeakReference = new WeakReference<>(progressBar);
    }

    public void startHandler(){
        if (progressBarWeakReference.get() != null)
            progressBarWeakReference.get().setVisibility(View.VISIBLE);

        if(!this.isAlive()) this.start();

        Handler handler = new Handler(this.getLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                if(progressBarWeakReference.get() != null)
                    progressBarWeakReference.get().setVisibility(View.GONE);
            }
        });
    }
}
