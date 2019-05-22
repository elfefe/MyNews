package com.elfefe.mynews.models;

import com.elfefe.mynews.R;

public enum Pages {
    TOP_STORIES(R.string.page_title_topstories),
    MOST_POPULAR(R.string.page_title_mostpopular),
    SEARCH_ARTICLE(R.string.page_title_favorites);

    public int title;

    Pages(int title){

        this.title = title;
    }

    public int getTitle() {
        return title;
    }
}
