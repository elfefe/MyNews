package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTService {
    @GET("{type}/v2/{subject}.json")
    Call<News> getFollowing(@Path("type") String type, @Path("subject") String subject, @Query("api-key") String key);
}
