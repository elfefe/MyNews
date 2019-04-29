package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.News;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NYTCalls {

    private NYTService nytService;
    private String key;

    NYTCalls() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nytService = retrofit.create(NYTService.class);

        key = "7beqz304Fmqzmbi3GxAQxanKShTgNCRb";
    }

    News fetchTopStoriesFollowing(String subject) {
        Call<News> call = nytService.getTopStories(subject, key);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    News fetchMostPopularFollowing() {
        Call<News> call = nytService.getMostPopular(key);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    News fetchSearchArticleFollowing(Map<String, String> search) {
        Call<News> call = nytService.getSearchArticle(key, search);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}