package com.elfefe.mynews.utils;

import android.support.annotation.Nullable;

import com.elfefe.mynews.models.NYTTitle;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYTCalls {
    public interface  CallBacks {
        void onResponse(@Nullable List<NYTTitle> titles);
        void onFailure();
    }

    public static void fetchTitleFollowing(CallBacks callBacks, String title) {
        final WeakReference<CallBacks> callBacksWeakReference = new WeakReference<CallBacks>(callBacks);

        NYTService nytService = NYTService.retrofit.create(NYTService.class);

        Call<List<NYTTitle>> call = nytService.getFollowing(title);

        call.enqueue(new Callback<List<NYTTitle>>() {
            @Override
            public void onResponse(Call<List<NYTTitle>> call, Response<List<NYTTitle>> response) {
                if(callBacksWeakReference.get() != null)
                    callBacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<NYTTitle>> call, Throwable t) {

                if(callBacksWeakReference.get() != null)

                    callBacksWeakReference.get().onFailure();
            }
        });
    }
}
