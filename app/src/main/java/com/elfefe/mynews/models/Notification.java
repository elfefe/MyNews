package com.elfefe.mynews.models;

import android.content.Context;

import java.util.List;

public class Notification {
    private final Context context;
    private final String search;
    private final List<String> sections;
    private final boolean[] checked;

    public Notification(Context context, String search, List<String> sections, boolean[] checked) {
        this.context = context;
        this.search = search;
        this.sections = sections;
        this.checked = checked;
    }

    public Context getContext() {
        return context;
    }

    public String getSearch() {
        return search;
    }

    public List<String> getSections() {
        return sections;
    }

    public boolean[] getChecked() {
        return checked;
    }
}
