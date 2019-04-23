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

    News fetchTopStoriesFollowing(String type, String subject, String key) {

        TopStoriesService topStoriesService = retrofit.create(TopStoriesService.class);

        Call<News> call = topStoriesService.getFollowing(type, subject, key);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    News fetchMostPopularFollowing(String type, String period, String key) {

        MostPopularService mostPopularService = retrofit.create(MostPopularService.class);

        Call<News> call = mostPopularService.getFollowing(type, period, key);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    News fetchTSearchArticleFollowing(String period, String key, Map<String, String> search) {

        SearchArticleService searchArticleService = retrofit.create(SearchArticleService.class);

        Call<News> call = searchArticleService.getFollowing(period, key, search);

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
