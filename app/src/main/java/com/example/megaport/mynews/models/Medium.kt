package com.example.megaport.mynews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Medium {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("caption")
    @Expose
    var caption: String? = null
    @SerializedName("media-metadata")
    @Expose
    var multimedia: List<Multimedium>? = null

}