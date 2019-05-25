package com.elfefe.mynews.models;

import com.elfefe.mynews.models.topstory.Multimedium;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("published_date")
    @Expose
    private String date;

    @SerializedName("abstract")
    @Expose
    private String article;

    @SerializedName("multimedia")
    @Expose
    private Multimedium multimedia;

    @SerializedName("media")
    @Expose
    private Object[] media;

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

    public Multimedium getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedium multimedia) {
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

    public Object[] getMedia() {
        return media;
    }

    public void setMedia(Object[] media) {
        this.media = media;
    }
}
