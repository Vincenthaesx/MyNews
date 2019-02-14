package com.example.megaport.mynews.Models.notifications;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {
    @SerializedName("docs")
    @Expose
    private final List<Doc> docs = null;

    public List<Doc> getDocs() {
        return docs;
    }
}
