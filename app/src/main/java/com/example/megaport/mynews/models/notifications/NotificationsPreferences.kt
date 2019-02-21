package com.example.megaport.mynews.models.notifications

// Model for notifications

class NotificationsPreferences(var queryTerm: String?, var categoryList: List<String>?, var isEnabled: Boolean) {

    companion object {
        const val DEFAULT_QUERY_TERM = ""
        const val DEFAULT_CATEGORY_LIST = ""
    }
}