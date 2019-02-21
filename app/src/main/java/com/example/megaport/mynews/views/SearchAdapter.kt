package com.example.megaport.mynews.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.megaport.mynews.models.Article
import com.example.megaport.mynews.R

class SearchAdapter(private val articleList: MutableList<Article>) : RecyclerView.Adapter<SearchViewHolder>() {

    // Create view holder and inflating its xml layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_main_item, parent, false)

        return SearchViewHolder(view)
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    override fun onBindViewHolder(viewHolder: SearchViewHolder, position: Int) {
        viewHolder.updateWithResult(articleList[position])
    }


    fun updateData(articleList: List<Article>){
        this.articleList.clear()
        this.articleList.addAll(articleList)
        notifyDataSetChanged()
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return this.articleList.size
    }

    // Returns a specific result in the results list
    fun getApplications(position: Int): Article {
        return this.articleList[position]
    }

}