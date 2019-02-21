package com.example.megaport.mynews.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Model for manipulating the list of images accompanying an article

class Multimedium {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("subtype")
    @Expose
    var subtype: String? = null
}
