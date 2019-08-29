
package com.elfefe.mynews.models.mostpopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("ConstantConditions")
public class MostPopularQuery {

    @SerializedName("results")
    @Expose
    private final List<MostPopularResult> results = null;
    public List<MostPopularResult> getResults() {
        return results;
    }
}
