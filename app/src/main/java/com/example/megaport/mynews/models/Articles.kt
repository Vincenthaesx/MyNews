package com.example.megaport.mynews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Class used to manipulate a list of applications

class Articles {

    @SerializedName(value = "results", alternate = ["docs"])
    @Expose
    var articles: List<Article>? = null

    @SerializedName("status")
    val status: String? = null

    fun getArticle(position: Int): Article? {
        return articles?.get(position)
    }

}
