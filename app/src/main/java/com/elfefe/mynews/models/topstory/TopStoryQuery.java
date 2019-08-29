
package com.elfefe.mynews.models.topstory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("CanBeFinal")
public class TopStoryQuery {
    @SerializedName("results")
    @Expose
    private List<TopStoryResult> topStoryResults = null;

    public List<TopStoryResult> getTopStoryResults() {
        return topStoryResults;
    }

}
