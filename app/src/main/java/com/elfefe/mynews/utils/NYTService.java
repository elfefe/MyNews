package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTService {
    @GET("users/{username}/following")
    Call<List<News>> getFollowing(@Path("username") String title);
}
