package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.Multimedium;
import com.elfefe.mynews.models.search.SearchQuery;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAsyncTask extends AsyncTask<Search,Void, List<Article>> {

    private final WeakReference<Listeners> callback;

    public SearchAsyncTask(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }
    public interface Listeners{
        void onResult(List<Article> articles);
    }

    @Override
    protected List<Article> doInBackground(Search... url) {
        NYTCalls nytCalls = new NYTCalls();
        Map<String, String> map = new HashMap<>();



        Search search = url[0];

        map.put("fq", search.getSections());
        map.put("q",search.getSearch());

        map.put("begin-date",search.getDateBegin());
        map.put("end-date",search.getDateEnd());


        List<Article> searchArticle = new ArrayList<>();
        SearchQuery searchResponse = nytCalls.fetchSearchArticleFollowing(map);
        if(searchResponse.getResponse() != null) {
            for (Docs doc : searchResponse.getResponse().getDocs()) {
                searchArticle.add(loadArticle(doc));
            }
        }
        return searchArticle;
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        callback.get().onResult(articles);
    }

    private Article loadArticle(Docs result){
        Article article = new Article();

        if(result.getSnippet().length() > 15) {
            article.setTitle(result.getSnippet().substring(0, 15));
        }else{
            article.setTitle(result.getSnippet());
        }
        article.setArticle(result.getAbstract());
        article.setDate(result.getPubDate().substring(0, 10));
        article.setSection(result.getSectionName());
        article.setUrl(result.getWebUrl());
        if(result.getMultimedia() != null && result.getMultimedia().size() > 0) {
            for(Multimedium multimedia : result.getMultimedia()){
                if (multimedia.getSubType().equals("square640")){
                    article.setMultimediaUrl("https://static01.nyt.com/"+multimedia.getUrl());
                    break;
                }
            }
            if(article.getMultimediaUrl() == null){
                article.setMultimediaUrl("https://static01.nyt.com/"+result.getMultimedia().get(0).getUrl());
            }
        }

        return article;
    }
}
