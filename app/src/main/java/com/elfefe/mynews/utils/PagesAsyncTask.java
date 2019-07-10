package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Pages;
import com.elfefe.mynews.models.mostpopular.MostPopularMediaMetadatum;
import com.elfefe.mynews.models.mostpopular.MostPopularMedium;
import com.elfefe.mynews.models.mostpopular.MostPopularQuery;
import com.elfefe.mynews.models.mostpopular.MostPopularResult;
import com.elfefe.mynews.models.topstory.TopStoryMultimedium;
import com.elfefe.mynews.models.topstory.TopStoryQuery;
import com.elfefe.mynews.models.topstory.TopStoryResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PagesAsyncTask extends AsyncTask<Pages,Void, List<Article>> {

    private final WeakReference<Listeners> callback;
    private int pixelDimension;

    public PagesAsyncTask(Listeners callback, int pixelDimension) {
        this.callback = new WeakReference<>(callback);
        this.pixelDimension = pixelDimension;
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

                if(topStoryQuery.getTopStoryResults() != null) {
                    for (TopStoryResult topStoryResult : topStoryQuery.getTopStoryResults()) {
                        topStoryArticle.add(loadArticle(topStoryResult));
                    }
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

        if(result.getTitle().length() > 15) {
            article.setTitle(result.getTitle().substring(0, 15));
        }else {
            article.setTitle(result.getTitle());
        }
            article.setArticle(result.getAbstract());
        article.setDate(result.getPublishedDate().substring(0, 10));
        article.setSection(result.getSection());
        article.setUrl(result.getUrl());
        if(result.getMultimedia() != null && result.getMultimedia().size() > 0) {
            for(TopStoryMultimedium multimedia : result.getMultimedia()){
                if (multimedia.getHeight() >= pixelDimension * 0.9 && multimedia.getHeight() <= pixelDimension * 1.2){
                    article.setMultimediaUrl(multimedia.getUrl());
                    break;
                }
            }
            if(article.getMultimediaUrl() == null){
                article.setMultimediaUrl(result.getMultimedia().get(0).getUrl());
            }
        }

        return article;
    }

    private Article loadArticle(MostPopularResult result){
        Article article = new Article();

        article.setTitle(result.getTitle().substring(0,15));
        article.setArticle(result.getAbstract());
        article.setDate(result.getPublishedDate().substring(0, 10));
        article.setSection(result.getSection());
        article.setUrl(result.getUrl());
        if (result.getMedia() != null && result.getMedia().size() > 0) {
            MostPopularMedium media = result.getMedia().get(0);
            if (media.getMediaMetadata() != null && media.getMediaMetadata().size() > 0) {
                for (MostPopularMediaMetadatum multimedia : media.getMediaMetadata()) {
                    if (multimedia.getHeight() >= pixelDimension * 0.9 && multimedia.getHeight() <= pixelDimension * 1.2) {
                        article.setMultimediaUrl(multimedia.getUrl());
                        break;
                    }
                }
                if(article.getMultimediaUrl() == null){
                    article.setMultimediaUrl(media.getMediaMetadata().get(0).getUrl());
                }
            }
        }

        return article;
    }
}

