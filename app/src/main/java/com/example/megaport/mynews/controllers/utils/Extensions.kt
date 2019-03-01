package com.example.megaport.mynews.controllers.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : Any> Any.safeCast(action : (T) -> Unit) {
    if (this is T){
        action.invoke(this)
    }
}

inline fun <reified Activity : AppCompatActivity> Context.openActivity(
        intent: Intent = Intent(this, Activity::class.java)) {
    this.startActivity(intent)
}
