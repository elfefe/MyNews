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

    @SerializedName("short_url")
    @Expose
    private String url;

    @SerializedName("section")
    @Expose
    private String section;



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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
