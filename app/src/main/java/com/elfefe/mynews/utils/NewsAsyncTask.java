package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Pages;
import com.elfefe.mynews.models.mostpopular.MostPopularQuery;
import com.elfefe.mynews.models.mostpopular.MostPopularResult;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.SearchQuery;
import com.elfefe.mynews.models.topstory.TopStoryQuery;
import com.elfefe.mynews.models.topstory.TopStoryResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                TopStoryQuery topStoryQuery = nytCalls.fetchTopStoriesFollowing();

                for (TopStoryResult topStoryResult : topStoryQuery.getTopStoryResults()) {
                    topStoryArticle.add(loadArticle(topStoryResult));
                }
                return  topStoryArticle;

            case MOST_POPULAR:
                List<Article> mostPopularArticle = new ArrayList<>();
                MostPopularQuery mostPopularResults = nytCalls.fetchMostPopularFollowing();

                for (MostPopularResult mostPopularResult : mostPopularResults.getResults()) {
                    mostPopularArticle.add(loadArticle(mostPopularResult));

                }
                return mostPopularArticle;

            case FAVORITE:
                List<Article> favoritesArticle = new ArrayList<>();
                TopStoryQuery favoriteResults = nytCalls.fetchFavoriteFollowing();

                for (TopStoryResult topStoryResult : favoriteResults.getTopStoryResults()) {
                    favoritesArticle.add(loadArticle(topStoryResult));
                }
                return  favoritesArticle;

            case SEARCH:
                Map<String, String> map = new HashMap<>();
                map.put("", "");

                List<Article> searchArticle = new ArrayList<>();
                SearchQuery searchResponse = nytCalls.fetchSearchArticleFollowing(map);

                for (Docs doc : searchResponse.getResponse().getDocs()){
                    searchArticle.add(loadArticle(doc));
                }
                return searchArticle;
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        callback.get().onResult(articles);
    }

    private Article loadArticle(TopStoryResult result){
        Article article = new Article();

        article.setTitle(result.getTitle());
        article.setArticle(result.getAbstract());
        article.setDate(result.getPublishedDate().substring(0, 10));
        article.setMultimedia(result.getMultimedia().get(0));
        article.setSection(result.getSection());

        return article;
    }

    private Article loadArticle(Docs result){
        Article article = new Article();

        article.setTitle(result.getSnippet());
        article.setArticle(result.getAbstract());
        article.setDate(result.getPubDate().substring(0, 10));
        article.setSection(result.getSectionName());

        return article;
    }

    private Article loadArticle(MostPopularResult result){
        Article article = new Article();

        article.setTitle(result.getTitle());
        article.setArticle(result.getAbstract());
        article.setDate(result.getPublishedDate().substring(0, 10));
        article.setSection(result.getSection());

        return article;
    }
}

