package com.elfefe.mynews.utils;

import android.os.AsyncTask;

import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.SearchQuery;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationAsyncTask extends AsyncTask<Search,Void, List<Article>> {

    private final WeakReference<PagesAsyncTask.Listeners> callback;

    public NotificationAsyncTask(PagesAsyncTask.Listeners callback) {
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
        for(int x = 0;x < search.getSections().size();x++){
            if (search.getChecked()[x]){
                map.put("fq",search.getSections().get(x));
            }
        }

        map.put("q",search.getSearch());

        map.put("begin-date",search.getDateBegin());
        map.put("end-date",search.getDateEnd());

        List<Article> searchArticle = new ArrayList<>();
        SearchQuery searchResponse = nytCalls.fetchSearchArticleFollowing(map);

        for (Docs doc : searchResponse.getResponse().getDocs()){
            searchArticle.add(loadArticle(doc));
        }
        return searchArticle;
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        callback.get().onResult(articles);
    }

    private Article loadArticle(Docs result){
        Article article = new Article();

        article.setTitle(result.getSnippet().substring(0,15));
        article.setArticle(result.getAbstract());
        article.setSection(result.getSectionName());
        article.setUrl(result.getWebUrl());

        if(result.getMultimedia() != null && result.getMultimedia().size() > 0) {
        }

        return article;
    }
}
