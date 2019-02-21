package com.example.megaport.mynews.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Models used to manipulate an article object

class Article {

    @SerializedName("web_url")
    @Expose
    val webUrl: String? = null
    @SerializedName("snippet")
    @Expose
    val snippet: String? = null
    @SerializedName("pub_date")
    @Expose
    val pubDate: String? = null
    @SerializedName("document_type")
    @Expose
    private val documentType: String? = null
    @SerializedName("new_desk")
    @Expose
    val newDesk: String? = null
    @SerializedName("headline")
    @Expose
    private val headline: Headline? = null
    @SerializedName("type_of_material")
    @Expose
    private val typeOfMaterial: String? = null
    @SerializedName("section_name")
    @Expose
    val sectionName: String? = null
    @SerializedName("uri")
    @Expose
    private val uri: String? = null
    @SerializedName("section")
    @Expose
    var section: String? = null
    @SerializedName("subsection")
    @Expose
    var subsection: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null
    @SerializedName("multimedia")
    @Expose
    var multimedia: List<Multimedium>? = null
    @SerializedName("media")
    @Expose
    var media: List<Medium>? = null


}
