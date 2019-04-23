package com.elfefe.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("updated_date")
    @Expose
    private String date;

    @SerializedName("abstract")
    @Expose
    private String article;

    @SerializedName("multimedia")
    @Expose
    private Object[] multimedia;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object[] getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Object[] multimedia) {
        this.multimedia = multimedia;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
