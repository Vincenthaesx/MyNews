package com.example.megaport.mynews.controllers.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.megaport.mynews.R
import kotlinx.android.synthetic.main.about_activity.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        image_button_about.setOnClickListener{ finish() }
    }
}
