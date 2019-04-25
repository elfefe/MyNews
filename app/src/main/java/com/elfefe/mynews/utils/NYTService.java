package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.News;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTService {
    @GET("{type}/v2/{subject}.json")
    Call<News> getTopStories(@Path("type") String type, @Path("subject") String subject, @Query("api-key") String key);

    @GET("search/v2/{period}.json")
    Call<News> getSearchArticle( @Path("period") String period, @Query("api-key") String key, @FieldMap Map<String, String> search);

    @GET("v2/{type}/{period}.json")
    Call<News> getMostPopular(@Path("type") String type,@Path("period") String period, @Query("api-key") String key);
}
