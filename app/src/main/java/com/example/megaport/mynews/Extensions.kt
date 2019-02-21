package com.example.megaport.mynews

inline fun <reified T : Any> Any.safeCast(action : (T) -> Unit) {
    if (this is T){
        action.invoke(this)
    }
}