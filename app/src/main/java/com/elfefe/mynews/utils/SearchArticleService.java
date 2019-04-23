package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.News;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchArticleService {
    @GET("search/v2/{period}.json")
    Call<News> getFollowing( @Path("period") String period, @Query("api-key") String key, @FieldMap Map<String, String> search);
}
