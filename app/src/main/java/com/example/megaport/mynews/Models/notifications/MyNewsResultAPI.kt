package com.example.megaport.mynews.Models.notifications


import com.example.megaport.mynews.Models.Result
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
