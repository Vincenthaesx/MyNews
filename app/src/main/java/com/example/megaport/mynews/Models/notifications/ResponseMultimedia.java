package com.example.megaport.mynews.Models.notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class ResponseMultimedia {
    @SerializedName("url")
    @Expose
    private final String url;

    public ResponseMultimedia(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}