package com.example.megaport.mynews.Controllers.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import com.example.megaport.mynews.R

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_activity)

        val imageButton = findViewById<ImageButton>(R.id.image_button_help)

        imageButton.setOnClickListener { startActivity() }
    }

    private fun startActivity() {
        Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
