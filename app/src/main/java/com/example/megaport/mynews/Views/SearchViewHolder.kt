package com.example.megaport.mynews.Views

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.example.megaport.mynews.Models.Article
import kotlinx.android.synthetic.main.fragment_main_item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat

class SearchViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    fun updateWithResult(article: Article) {
        // Add an arrow head to the section of the article when it has a subsection
        var section = article.sectionName
        if (TextUtils.isEmpty(section)) {
            section = article.newDesk
            if (section == null || section == "None")
                section = "News"
        }
        itemView.fragment_main_item_section.text = section

        // Parsing the updated date of the article to a new format and setting it the corresponding TextView
        var dtStart = article.pubDate
        dtStart = dtStart!!.replace("T", " ")
        @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat("yyyy-MM-dd")
        @SuppressLint("SimpleDateFormat") val formatR = SimpleDateFormat("dd/MM/yyyy")
        try {
            val date = format.parse(dtStart)
            dtStart = formatR.format(date)
            itemView.fragment_main_item_date.text = dtStart
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val images = article.multimedia

        if (images != null) {
            for (i in images.indices) {
                if (images[i].subtype == "thumbnail") {
                    val url = "https://static01.nyt.com/" + images[i].url!!
                    Glide.with(itemView.context).load(url).into(itemView.fragment_main_item_image)
                }
            }
        }

        itemView.fragment_main_item_title.text = article.snippet
    }
}
