package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.NYTTitle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTService {
    @GET("users/{username}/following")
    Call<List<NYTTitle>> getFollowing(@Path("username") String title);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}
