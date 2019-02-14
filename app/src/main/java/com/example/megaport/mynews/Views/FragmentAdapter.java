package com.example.megaport.mynews.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.RequestManager;
import com.example.megaport.mynews.Models.Article;
import com.example.megaport.mynews.Models.Articles;
import com.example.megaport.mynews.R;

import java.util.List;

// --------------------------------------------------
// Class for the adapter specific to the articles
// --------------------------------------------------

public class FragmentAdapter extends RecyclerView.Adapter<FragmentViewHolder> {

    public interface Listener { }

    // FOR COMMUNICATION
    private final Listener callback;

    private final List<Article> articleList;
    private final RequestManager glide;

    // CONSTRUCTOR
    public FragmentAdapter(Articles articles, RequestManager glide, Listener callback) {
        this.articleList = articles.getArticles();
        this.glide = glide;
        this.callback = callback;
    }

    // Create view holder and inflating its xml layout

    @NonNull
    @Override
    public FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_top_stories_item, parent, false);

        return new FragmentViewHolder(view);
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder viewHolder, int position) {
        viewHolder.updateWithResult(this.articleList.get(position), this.glide, this.callback);
    }


    // Returns the total count of items in the list

    @Override
    public int getItemCount() { return this.articleList.size(); }


    // Returns a specific result in the results list

    public Article getArticle(int position){
        return this.articleList.get(position);
    }
}
