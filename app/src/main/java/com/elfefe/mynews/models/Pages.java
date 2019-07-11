package com.elfefe.mynews.models;

import com.elfefe.mynews.R;

public enum Pages {
    TOP_STORIES(R.string.page_title_topstories),
    MOST_POPULAR(R.string.page_title_mostpopular),
    FAVORITE(R.string.page_title_favorites),
    FILTERED(R.string.title_filtered),
    SEARCH(R.string.title_search);

    private int title;

    Pages(int title){

        this.title = title;
    }

    public int getTitle() {
        return title;
    }
}
