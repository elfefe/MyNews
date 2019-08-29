
package com.elfefe.mynews.models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Multimedium {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("subType")
    @Expose
    private String subType;

    public String getUrl() {
        return url;
    }

    public String getSubType() {
        return subType;
    }

}
