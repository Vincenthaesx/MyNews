package com.example.megaport.mynews.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
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

// -----------------------------------------------------
// Class used to create a new result item in the UI
// -----------------------------------------------------

class FragmentViewHolder extends RecyclerView.ViewHolder{

    // FOR DESIGN
    @BindView(R.id.fragment_main_item_section) TextView mSection;
    @BindView(R.id.fragment_main_item_title) TextView mTitle;
    @BindView(R.id.fragment_main_item_date) TextView mDate;
    @BindView(R.id.fragment_main_item_image) ImageView mImage;

    private final Context context =  MyApplication.getAppContext();
    private final Drawable nytimeslogo = context.getResources().getDrawable(R.drawable.ic_nytimes_logo);

    FragmentViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    // Update the UI with new results

    void updateWithResult(Article article, RequestManager glide, FragmentAdapter.Listener callback){
        // Add an arrow head to the section of the article when it has a subsection
        String section = article.getSection();
        if(article.getSubsection() != null) {
            if (!article.getSubsection().isEmpty()) {
                section = section + " > " + article.getSubsection();
            }
        }
        this.mSection.setText( section );

        // Parsing the updated date of the article to a new format and setting it the corresponding TextView
        String dtStart = article.getPublishedDate();
        dtStart = dtStart.replace("T"," ");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatR = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dtStart);
            dtStart = formatR.format(date);
            this.mDate.setText(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Multimedium> images;
        if (article.getMedia() == null) {
            // Retrieving the thumbnail picture of the article
            images = article.getMultimedia();
        }
        else {
            images = article.getMedia().get(0).getMultimedia();
        }
        // If the article does not have any thumbnail : set a default thumbnail in the ImageView
        if (images.isEmpty()) {
            this.mImage.setImageDrawable(nytimeslogo);
        }
        // Else set the corresponding thumbnail in the ImageView
        else {
            for (int i = 0; i < images.size(); i++) {
                glide.load( images.get(0).getUrl()).into(this.mImage);
            }
        }
        // Setting the title of the article
        this.mTitle.setText(article.getTitle());
        WeakReference<FragmentAdapter.Listener> callbackWeakRef = new WeakReference<>(callback);
    }

}
