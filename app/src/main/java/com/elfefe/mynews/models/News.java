package com.elfefe.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {


    @SerializedName("starred_url")
    @Expose
    private String url;

    @SerializedName("gravatar_id")
    @Expose
    private String img;


    @SerializedName("login")
    @Expose
    private String title;

    @SerializedName("2")
    @Expose
    private String date;

    @SerializedName("3")
    @Expose
    private String article;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return article;
    }

    public void setContent(String article) {
        this.article = article;
    }

}
