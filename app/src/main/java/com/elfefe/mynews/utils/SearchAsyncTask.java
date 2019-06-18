package com.elfefe.mynews.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;

import com.elfefe.mynews.controllers.MainActivity;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Search;
import com.elfefe.mynews.models.search.Docs;
import com.elfefe.mynews.models.search.Multimedium;
import com.elfefe.mynews.models.search.SearchQuery;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAsyncTask extends AsyncTask<Search,Void, List<Article>> {

    private final WeakReference<Listeners> callback;
    private int pixelDimension;

    public SearchAsyncTask(Listeners callback, int pixelDimension) {
        this.callback = new WeakReference<>(callback);
        this.pixelDimension = pixelDimension;
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
                        Log.d("FIELDS", x + search.getSections().get(x));
                    }
                }

                map.put("q",search.getSearch());

        String toDateBegin, toDateEnd,fromDateBegin, fromDateEnd;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fromSimpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat toSimpleDateFormat = new SimpleDateFormat("ddMMyy");

        try {
            fromDateBegin = fromSimpleDateFormat.parse(search.getDateBegin()).toString();
            fromDateEnd = fromSimpleDateFormat.parse(search.getDateEnd()).toString();

            toDateBegin = toSimpleDateFormat.parse(fromDateBegin).toString();
            toDateEnd = toSimpleDateFormat.parse(fromDateEnd).toString();



            //map.put("begin-date",toDateBegin);
            //map.put("end-date",toDateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        article.setDate(result.getPubDate().substring(0, 10));
        article.setSection(result.getSectionName());
        article.setUrl(result.getWebUrl());
        if(result.getMultimedia() != null && result.getMultimedia().size() > 0) {
            for(Multimedium multimedia : result.getMultimedia()){
                if (multimedia.getHeight() >= pixelDimension -5){
                    article.setMultimediaUrl(multimedia.getUrl());
                    break;
                }
            }
        }

        return article;
    }
}
