package com.example.megaport.mynews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Headline {

    @SerializedName("main")
    @Expose
    var main: String? = null
    @SerializedName("kicker")
    @Expose
    var kicker: Any? = null
    @SerializedName("content_kicker")
    @Expose
    var contentKicker: Any? = null
    @SerializedName("print_headline")
    @Expose
    var printHeadline: Any? = null
    @SerializedName("name")
    @Expose
    var name: Any? = null
    @SerializedName("seo")
    @Expose
    var seo: Any? = null
    @SerializedName("sub")
    @Expose
    var sub: Any? = null

}