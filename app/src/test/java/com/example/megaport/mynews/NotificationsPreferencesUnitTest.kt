package com.example.megaport.mynews

import com.example.megaport.mynews.models.notifications.NotificationsPreferences
import org.junit.Test
import java.util.ArrayList
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue

class NotificationsPreferencesUnitTest {

    @Test
    fun createNotificationsPreferences() {
        val categoryList = ArrayList<String>()
        val queryTerm = "test"

        val notificationsPref = NotificationsPreferences(queryTerm, categoryList, false)

        assertEquals("test", notificationsPref.queryTerm)
        assertTrue(notificationsPref.categoryList!!.isEmpty())
        assertFalse(notificationsPref.isEnabled)
    }

    @Test
    fun setQueryTerm() {
        val notificationsPref = NotificationsPreferences("", null, false)

        notificationsPref.queryTerm = "test"

        assertEquals("test", notificationsPref.queryTerm)
    }

    @Test
    fun setCategoryList() {
        val notificationsPref = NotificationsPreferences("", null, false)

        val categoryList = ArrayList<String>()
        val entry1 = "Arts"
        val entry2 = "Politics"
        categoryList.add(entry1)
        categoryList.add(entry2)

        notificationsPref.categoryList = categoryList

        assertEquals("Politics", notificationsPref.categoryList!![1])
    }

    @Test
    fun setEnabled() {
        val notificationsPref = NotificationsPreferences("", null, false)

        notificationsPref.isEnabled = true

        assertTrue(notificationsPref.isEnabled)
    }
}