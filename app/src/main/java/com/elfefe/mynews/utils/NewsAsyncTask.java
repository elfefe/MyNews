package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Pages;
import com.elfefe.mynews.models.mostpopular.MostPopularResults;
import com.elfefe.mynews.models.topstory.Result;
import com.elfefe.mynews.models.topstory.TopStoryResults;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class NewsAsyncTask extends AsyncTask<Pages,Void, List<Article>> {

    private final WeakReference<Listeners> callback;

    public NewsAsyncTask(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }
    public interface Listeners{
        void onResult(List<Article> articles);
    }

    @Override
    protected List<Article> doInBackground(Pages... url) {
        NYTCalls nytCalls = new NYTCalls();
        switch (url[0]){
            case TOP_STORIES:
                List<Article> topStoryArticle = new ArrayList<>();
                TopStoryResults topStoryResults = nytCalls.fetchTopStoriesFollowing();

                for (Result result : topStoryResults.getResults()) {
                    topStoryArticle.add(loadArticle(result));
                }
                return  topStoryArticle;
            case MOST_POPULAR:
                List<Article> mostPopularArticle = new ArrayList<>();
                MostPopularResults mostPopularResults = nytCalls.fetchMostPopularFollowing();

                for (com.elfefe.mynews.models.mostpopular.Result result : mostPopularResults.getResults()) {
                    Article article = new Article();
                    article.setTitle(result.getTitle());
                    article.setArticle(result.getAbstract());
                    article.setDate(result.getPublishedDate().substring(0, 10));
                    article.setSection(result.getSection());

                    mostPopularArticle.add(article);

                }

                return mostPopularArticle;
            case SEARCH_ARTICLE:
                List<Article> favoritesArticle = new ArrayList<>();
                TopStoryResults favoriteResults = nytCalls.fetchFavoriteFollowing();

                for (Result result : favoriteResults.getResults()) {
                    Article article = new Article();
                }
                return  favoritesArticle;
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        callback.get().onResult(articles);
    }

    private Article loadArticle(Result result){
        Article article = new Article();

        article.setTitle(result.getTitle());
        article.setArticle(result.getAbstract());
        article.setDate(result.getPublishedDate().substring(0, 10));
        article.setMultimedia(result.getMultimedia().get(1));
        article.setSection(result.getSection());

        return article;
    }
}

