package com.example.megaport.mynews.Models.notifications

import com.example.megaport.mynews.Models.Headline
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Doc(@field:SerializedName("web_url")
                   @field:Expose
                   val webUrl: String, @field:SerializedName("headline")
                   @field:Expose
                   val headline: Headline, @field:SerializedName("pub_date")
                   @field:Expose
                   val pubDate: String, @field:SerializedName("section_name")
                   @field:Expose
                   val sectionName: String, @field:SerializedName("subsection_name")
                   @field:Expose
                   val subsectionName: String) {
    @SerializedName("multimedia")
    @Expose
    val multimedia: List<ResponseMultimedia>? = null
}
