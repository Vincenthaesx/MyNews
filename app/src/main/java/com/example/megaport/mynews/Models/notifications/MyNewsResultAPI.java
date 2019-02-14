package com.example.megaport.mynews.Models.notifications;


import com.example.megaport.mynews.Models.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyNewsResultAPI {
    @SerializedName("results")
    @Expose
    private final List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    //FOR NOTIFICATIONS
    @SerializedName("response")
    @Expose
    private static Response response;

    public static Response getResponse() {
        return response;
    }

}
