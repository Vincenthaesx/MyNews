package com.example.megaport.mynews.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import com.example.megaport.mynews.Controllers.Utils.MyApplication;
import com.example.megaport.mynews.Models.Article;
import com.example.megaport.mynews.Models.Multimedium;
import com.example.megaport.mynews.R;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class searchViewHolder extends RecyclerView.ViewHolder{

    // FOR DESIGN
    @BindView(R.id.fragment_search_item_section) TextView sectionSearch;
    @BindView(R.id.fragment_search_item_title) TextView titleSearch;
    @BindView(R.id.fragment_search_item_date) TextView dateSearch;
    @BindView(R.id.fragment_search_item_image) ImageView imageSearch;

    private final Context context =  MyApplication.getAppContext();
    private final Drawable nytimeslogo = context.getResources().getDrawable(R.drawable.ic_nytimes_logo);

    searchViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    // Update the UI with new results

    void updateWithResult(Article article, RequestManager glide, searchAdapter.Listener callback){
        // Add an arrow head to the section of the article when it has a subsection
        String section = article.getSectionName();
        if (TextUtils.isEmpty( section )){
            section = article.getNewDesk();
            if(section == null || section.equals("None"))
                section = "News";
        }
        this.sectionSearch.setText( section );

        // Parsing the updated date of the article to a new format and setting it the corresponding TextView
        String dtStart = article.getPubDate();
        dtStart = dtStart.replace("T"," ");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatR = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dtStart);
            dtStart = formatR.format(date);
            this.dateSearch.setText(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Multimedium> images = article.getMultimedia();

        // If the article does not have any thumbnail : set a default thumbnail in the ImageView
        if (images.isEmpty()) {
            this.imageSearch.setImageDrawable(nytimeslogo);
        }
        // Else set the corresponding thumbnail in the ImageView
        else {
            for (int i = 0; i < images.size(); i++) {
                if (images.get(i).getSubtype().equals("thumbnail")){
                    String url = "https://static01.nyt.com/" + images.get(i).getUrl();
                    glide.load(url).into(this.imageSearch);
                }
            }
        }
        // Setting the title of the article
        this.titleSearch.setText(article.getSnippet());
        WeakReference<searchAdapter.Listener> callbackWeakRef = new WeakReference<>(callback);
    }
}
