package com.elfefe.mynews.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

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

        StringBuilder sectionsField = new StringBuilder("section_name:( ");


        Search search = url[0];
        for (int x = 0; x < search.getSections().size(); x++) {
            if (search.getChecked()[x]) {
                sectionsField.append("\"").append(search.getSections().get(x)).append("\" ");
                Log.d("FIELDS", x + search.getSections().get(x));
            }
        }

        sectionsField.append(")");

        map.put("fq", sectionsField.toString());

        map.put("q",search.getSearch());

        String toDateBegin, toDateEnd;
        Date fromDateBegin, fromDateEnd;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat fromSimpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat toSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            fromDateBegin = fromSimpleDateFormat.parse(search.getDateBegin());
            fromDateEnd = fromSimpleDateFormat.parse(search.getDateEnd());

            toDateBegin = toSimpleDateFormat.format(fromDateBegin);
            toDateEnd = toSimpleDateFormat.format(fromDateEnd);

            map.put("begin-date",toDateBegin);
            map.put("end-date",toDateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
