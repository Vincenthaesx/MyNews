package com.example.megaport.mynews.controllers.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import com.example.megaport.mynews.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        val imageButton = findViewById<ImageButton>(R.id.image_button_about)

        imageButton.setOnClickListener { startActivity() }

    }

    private fun startActivity() {
        Intent (this, MainActivity::class.java)
        startActivity((intent))
    }

}
