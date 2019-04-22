package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.News;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NYTCalls {

    private Retrofit retrofit;

    NYTCalls() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    News fetchTitleFollowing(String type, String subject, String key) {

        NYTService nytService = retrofit.create(NYTService.class);

        Call<News> call = nytService.getFollowing(type, subject, key);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
