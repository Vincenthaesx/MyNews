package com.example.megaport.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.megaport.mynews.Controllers.Fragments.search_fragment;
import com.example.megaport.mynews.R;

public class SearchResultActivity extends AppCompatActivity {

    private search_fragment searchFragment = (search_fragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_frame_layout);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // Retrieving extras from the intent
        Intent intent = getIntent();
        String sDate = intent.getStringExtra("start_date");
        String eDate = intent.getStringExtra("end_date");
        String query = intent.getStringExtra("SearchQuery");
        String section = intent.getStringExtra("section");


        if(savedInstanceState == null) {
            this.configureAndShowSearchFragment(query, sDate, eDate, section);
        }
        else{
            searchFragment = (search_fragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_frame_layout);
        }

    }

    private void configureAndShowSearchFragment(String query, String sDate, String eDate, String section){
        if(searchFragment == null ) {
            searchFragment = new search_fragment();
            Bundle bundle = new Bundle();
            bundle.putString("query", query);
            bundle.putString("sDate", sDate);
            bundle.putString("eDate", eDate);
            bundle.putString("section", section);
            searchFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_frame_layout, searchFragment)
                    .commit();
        }
    }
}
