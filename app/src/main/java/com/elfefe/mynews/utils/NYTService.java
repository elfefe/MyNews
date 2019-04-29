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
    @GET("topstories/v2/{subject}.json")
    Call<News> getTopStories(@Path("subject") String subject, @Query("api-key") String key);

    @GET("v2/mostpopular/30.json")
    Call<News> getMostPopular(@Query("api-key") String key);

    @GET("search/v2/30.json")
    Call<News> getSearchArticle(@Query("api-key") String key, @FieldMap Map<String, String> search);
}
