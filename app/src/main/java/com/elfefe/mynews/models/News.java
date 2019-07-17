package com.elfefe.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class News {


    @SerializedName("starred_url")
    @Expose
    private String url;

    @SerializedName("results")
    @Expose
    private Object[] results;


    @SerializedName("login")
    @Expose
    private String title;

    @SerializedName("last_updated")
    @Expose
    private String date;

    @SerializedName("section")
    @Expose
    private String section;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Object[] getResults() {
        return results;
    }

    public void setResults(Object[] results) {
        this.results = results;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

