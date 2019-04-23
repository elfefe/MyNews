package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MostPopularService {
    @GET("v2/{type}/{period}.json")
    Call<News> getFollowing(@Path("type") String type,@Path("period") String period, @Query("api-key") String key);
}
