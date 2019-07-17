package com.elfefe.mynews.utils;

import com.elfefe.mynews.models.mostpopular.MostPopularQuery;
import com.elfefe.mynews.models.notification.NotificationQuery;
import com.elfefe.mynews.models.search.SearchQuery;
import com.elfefe.mynews.models.topstory.TopStoryQuery;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

interface NYTService {
    @GET("topstories/v2/home.json")
    Call<TopStoryQuery> getTopStories(@Query("api-key") String key);

    @GET("mostpopular/v2/viewed/30.json")
    Call<MostPopularQuery> getMostPopular(@Query("api-key") String key);

    @GET("topstories/v2/{subject}.json")
    Call<TopStoryQuery> getFavorite(@Path("subject") String subject, @Query("api-key") String key);

    @GET("search/v2/articlesearch.json")
    Call<SearchQuery> getSearchArticle(@QueryMap Map<String, String> search, @Query("api-key") String key);

    @GET("news/v3/content/{source}/{section}.json")
    Call<NotificationQuery> getNotification(@Path("source") String source,
                                            @Path("section") String section,
                                            @Query("limit") int limit,
                                            @Query("api-key") String key);
}
