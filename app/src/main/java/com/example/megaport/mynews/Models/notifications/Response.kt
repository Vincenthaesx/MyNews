package com.example.megaport.mynews.Models.notifications

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("docs")
    @Expose
    val docs: List<Doc>? = null
}
