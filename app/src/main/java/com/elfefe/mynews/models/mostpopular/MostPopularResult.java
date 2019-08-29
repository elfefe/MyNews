
package com.elfefe.mynews.models.mostpopular;

import com.elfefe.mynews.utils.EmptyStringAsNullTypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings({"unused", "ConstantConditions"})
public class MostPopularResult {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("media")
    @JsonAdapter(EmptyStringAsNullTypeAdapter.class)
    @Expose
    private final List<MostPopularMedium> media = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<MostPopularMedium> getMedia() {
        return media;
    }
}
