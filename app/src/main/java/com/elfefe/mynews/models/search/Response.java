
package com.elfefe.mynews.models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings({"unused", "CanBeFinal"})
public class Response {

    @SerializedName("docs")
    @Expose
    private List<Docs> docs = null;

    @SuppressWarnings("unused")
    public List<Docs> getDocs() {
        return docs;
    }

}
