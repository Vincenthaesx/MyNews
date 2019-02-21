package com.example.megaport.mynews.models.notifications


import com.example.megaport.mynews.models.Result
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyNewsResultAPI {

    @SerializedName("results")
    @Expose
    val results: List<Result>? = null

    @SerializedName("response")
    @Expose
    val response: Response? = null

}
