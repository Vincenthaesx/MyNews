package com.example.megaport.mynews.controllers.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.example.megaport.mynews.controllers.utils.notifications.NotificationHelper
import com.example.megaport.mynews.models.notifications.NotificationsPreferences
import com.example.megaport.mynews.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.android.synthetic.main.search_checkboxes.*
import kotlinx.android.synthetic.main.search_query_term.*

class NotificationsActivity : AppCompatActivity() {

    private var mPreferences: SharedPreferences? = null

    private val selectedCheckboxes: List<String>
        get() {
            val selectedCheckboxes = ArrayList<String>()
            for (i in 0 until checkbox_container.childCount) {
                val view = checkbox_container.getChildAt(i)
                if (view is ViewGroup) {
                    for (y in 0 until view.childCount) {
                        val viewChild = view.getChildAt(y)
                        if (viewChild is CheckBox) {
                            if (viewChild.isChecked) {
                                selectedCheckboxes.add(viewChild.tag.toString())
                            }
                        }
                    }
                }
            }
            return selectedCheckboxes
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        image_button_notifications_return.setOnClickListener { view -> startActivity() }

        this.retrievePreferences()
        this.configureSwitchChangeListener()
    }

    override fun onDestroy() {
        saveNotificationsPreferences(search_query_term_input.text.toString(), selectedCheckboxes, activity_notifications_switch.isChecked)
        if (activity_notifications_switch.isChecked) {
            toggleNotifications(true)
            Log.e("Notifications", "onPause: True")
        } else {
            toggleNotifications(false)
            Log.e("Notifications", "onPause: False")
        }
        Toast.makeText(this@NotificationsActivity, "Notifications preferences saved", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    // -------------
    // CONFIGURATION
    // -------------


    private fun retrievePreferences() {
        mPreferences = baseContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val gson = Gson()
        val type = object : TypeToken<NotificationsPreferences>() {

        }.type
        val jsonState = mPreferences!!.getString(NOTIFICATIONS_STATE, "")
        val notificationsPreferences = gson.fromJson<NotificationsPreferences>(jsonState, type)
        val queryTerm: String?
        if (notificationsPreferences == null) { // Normally use for first time launch
            queryTerm = mPreferences!!.getString(PREF_KEY_QUERY_TERM, NotificationsPreferences.DEFAULT_QUERY_TERM)
            val retrieve = mPreferences!!.getString(PREF_KEY_CATEGORY_LIST, NotificationsPreferences.DEFAULT_CATEGORY_LIST)
            val categoryList = ArrayList<String>()
            categoryList.add(retrieve)
            saveNotificationsPreferences(queryTerm, categoryList, false)
        } else {
            queryTerm = notificationsPreferences.queryTerm
            val list = notificationsPreferences.categoryList
            if (queryTerm != "" && !list!!.isEmpty()) {
                search_query_term_input.setText(queryTerm)
                for (x in list.indices) {
                    for (i in 0 until checkbox_container.childCount) {
                        val view = checkbox_container.getChildAt(i)
                        if (view is ViewGroup) {
                            for (y in 0 until view.childCount) {
                                val viewChild = view.getChildAt(y)
                                if (viewChild is CheckBox) {
                                    if (viewChild.tag.toString() == list[x]) {
                                        viewChild.isChecked = true
                                    }
                                }
                            }
                        }
                    }
                }
                activity_notifications_switch.isChecked = notificationsPreferences.isEnabled
            }
        }
    }

    private fun saveNotificationsPreferences(queryTerm: String?, categoryList: List<String>, isEnabled: Boolean) {
        val gson = Gson()
        val notifPrefs = NotificationsPreferences(queryTerm, categoryList, isEnabled)
        val jsonNotifPrefs = gson.toJson(notifPrefs)
        mPreferences!!.edit().putString(NOTIFICATIONS_STATE, jsonNotifPrefs).apply()
    }

    private fun configureSwitchChangeListener() {
        activity_notifications_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (search_query_term_input.text.toString() != "") {
                    val selectedCheckboxes = selectedCheckboxes
                    if (selectedCheckboxes.isEmpty()) {
                        Toast.makeText(this@NotificationsActivity, "You have to select at least one category", Toast.LENGTH_SHORT).show()
                        buttonView.isChecked = false
                    }
                } else {
                    Toast.makeText(this@NotificationsActivity, "Query term can't be empty to enable notifications", Toast.LENGTH_SHORT).show()
                    buttonView.isChecked = false
                }
            } else {
                toggleNotifications(false)
            }
        }
    }

    private fun toggleNotifications(enableNotifications: Boolean) {
        if (enableNotifications) {
            NotificationHelper.scheduleRepeatingRTCNotification(this)
        } else {
            NotificationHelper.cancelAlarmRTC()
        }
    }

    private fun startActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {

        const val PREFS = "PREFS"
        const val NOTIFICATIONS_STATE = "NOTIFICATIONS_STATE"
        private const val PREF_KEY_QUERY_TERM = "PREF_KEY_QUERY_TERM"
        private const val PREF_KEY_CATEGORY_LIST = "PREF_KEY_CATEGORY_LIST"
    }
}
