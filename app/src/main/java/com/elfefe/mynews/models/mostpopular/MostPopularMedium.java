
package com.elfefe.mynews.models.mostpopular;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("CanBeFinal")
public class MostPopularMedium {

    @SerializedName("media-metadata")
    @Expose
    private List<MostPopularMediaMetadatum> mediaMetadata = null;

    public List<MostPopularMediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }

}
