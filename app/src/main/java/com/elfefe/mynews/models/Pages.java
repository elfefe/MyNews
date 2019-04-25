package com.elfefe.mynews.models;

import com.elfefe.mynews.R;

public enum Pages {
    TOP_STORIES(R.string.page_title_topstories,""),
    MOST_POPULAR(R.string.page_title_mostpopular,""),
    SEARCH_ARTICLE(R.string.page_title_favorites,"");

    private int title;
    private String url;

    Pages(int title, String url){

        this.title = title;
        this.url = url;
    }

    public int getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
