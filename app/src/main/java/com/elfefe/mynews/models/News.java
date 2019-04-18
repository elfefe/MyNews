package com.elfefe.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {


    @SerializedName("login")
    @Expose
    private String url;

    @SerializedName("0")
    @Expose
    private String img;


    @SerializedName("1")
    @Expose
    private String title;

    @SerializedName("2")
    @Expose
    private String date;

    @SerializedName("3")
    @Expose
    private String content;


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
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
