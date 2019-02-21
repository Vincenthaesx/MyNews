package com.example.megaport.mynews.Views

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.megaport.mynews.Models.Article
import com.example.megaport.mynews.Models.Multimedium
import java.text.ParseException
import java.text.SimpleDateFormat
import kotlinx.android.synthetic.main.fragment_main_item.view.*

class FragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SimpleDateFormat")
    fun updateWithResult(article: Article) {

        // Add an arrow head to the section of the article when it has a subsection
        var section = article.section
        if (article.subsection != null) {
            if (!article.subsection!!.isEmpty()) {
                section = section + " > " + article.subsection
            }
        }
        itemView.fragment_main_item_section.text = section

        // Parsing the updated date of the article to a new format and setting it the corresponding TextView
        var dtStart = article.publishedDate
        dtStart = dtStart?.replace("T", " ")
        val format = SimpleDateFormat("yyyy-MM-dd")
        val formatR = SimpleDateFormat("dd/MM/yyyy")
        try {
            val date = format.parse(dtStart)
            dtStart = formatR.format(date)
            itemView.fragment_main_item_date?.text = dtStart
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val images: List<Multimedium>? = if (article.media == null) {
            // Retrieving the thumbnail picture of the article
            article.multimedia
        } else {
            article.media!![0].multimedia
        }
        // If the article does not have any thumbnail : set a default thumbnail in the ImageView
        images?.let {
                for (i in it.indices) {
                    Glide.with(itemView.context).load(images[0].url).into(itemView.fragment_main_item_image)
               }
        }
        // Else set the corresponding thumbnail in the ImageView
        // Setting the title of the article
        itemView.fragment_main_item_title.text = article.title
    }

}
