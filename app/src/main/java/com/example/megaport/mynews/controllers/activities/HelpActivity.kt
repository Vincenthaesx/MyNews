package com.example.megaport.mynews.controllers.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.megaport.mynews.R
import kotlinx.android.synthetic.main.help_activity.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_activity)

        image_button_help.setOnClickListener{ finish() }
    }
}
