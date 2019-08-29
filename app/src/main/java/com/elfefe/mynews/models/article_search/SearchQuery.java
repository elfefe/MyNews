
package com.elfefe.mynews.models.article_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
class SearchQuery {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private Response response;

    @SuppressWarnings("unused")
    public String getStatus() {
        return status;
    }

    @SuppressWarnings("unused")
    public void setStatus(String status) {
        this.status = status;
    }

    @SuppressWarnings("unused")
    public String getCopyright() {
        return copyright;
    }

    @SuppressWarnings("unused")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @SuppressWarnings("unused")
    public Response getResponse() {
        return response;
    }

    @SuppressWarnings("unused")
    public void setResponse(Response response) {
        this.response = response;
    }

}
