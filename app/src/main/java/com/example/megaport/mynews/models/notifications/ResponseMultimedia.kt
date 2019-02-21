package com.example.megaport.mynews.models.notifications

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseMultimedia(@field:SerializedName("url")
                                  @field:Expose
                                  val url: String)