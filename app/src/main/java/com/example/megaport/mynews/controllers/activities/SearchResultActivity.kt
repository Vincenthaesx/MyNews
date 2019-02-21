package com.example.megaport.mynews.controllers.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.megaport.mynews.controllers.fragments.SearchFragment
import com.example.megaport.mynews.R

class SearchResultActivity : AppCompatActivity() {

    private var searchFragment = supportFragmentManager.findFragmentById(R.id.activity_search_frame_layout) as SearchFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        // Retrieving extras from the intent
        val intent = intent
        val sDate = intent.getStringExtra("start_date")
        val eDate = intent.getStringExtra("end_date")
        val query = intent.getStringExtra("SearchQuery")
        val section = intent.getStringExtra("section")


        if (savedInstanceState == null) {
            this.configureAndShowSearchFragment(query, sDate, eDate, section)
        } else {
            searchFragment = supportFragmentManager.findFragmentById(R.id.activity_search_frame_layout) as SearchFragment?
        }

    }

    private fun configureAndShowSearchFragment(query: String, sDate: String, eDate: String?, section: String) {
        if (searchFragment == null) {
            searchFragment = SearchFragment()
            val bundle = Bundle()
            bundle.putString("query", query)
            bundle.putString("sDate", sDate)
            bundle.putString("eDate", eDate)
            bundle.putString("section", section)
            searchFragment?.arguments = bundle
            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_search_frame_layout, searchFragment!!)
                    .commit()
        }
    }
}
