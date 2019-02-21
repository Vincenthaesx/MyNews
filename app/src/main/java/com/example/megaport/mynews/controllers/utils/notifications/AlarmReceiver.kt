package com.example.megaport.mynews.controllers.utils.notifications

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import android.util.Log
import com.example.megaport.mynews.controllers.utils.MyNewsStreams
import com.example.megaport.mynews.models.notifications.MyNewsResultAPI
import com.example.megaport.mynews.models.notifications.NotificationsPreferences
import com.example.megaport.mynews.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Calendar
import io.reactivex.observers.DisposableObserver
import android.content.Context.MODE_PRIVATE
import com.example.megaport.mynews.controllers.activities.NotificationsActivity.Companion.NOTIFICATIONS_STATE
import com.example.megaport.mynews.controllers.activities.NotificationsActivity.Companion.PREFS
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class AlarmReceiver : BroadcastReceiver() {

    private var mContext: Context? = null
    private var numberOfArticle: Int? = 0

    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        this.mContext = context
        this.retrieveSharedPreferences()
    }

    private fun retrieveSharedPreferences() {
        val preferences = mContext!!.getSharedPreferences(PREFS, MODE_PRIVATE)
        val gson = Gson()
        val type = object : TypeToken<NotificationsPreferences>() {

        }.type
        val jsonState = preferences.getString(NOTIFICATIONS_STATE, "")
        val notificationsPreferences = gson.fromJson<NotificationsPreferences>(jsonState, type)
        if (notificationsPreferences != null) {
            val queryTerm = notificationsPreferences.queryTerm
            val categoryList = notificationsPreferences.categoryList
            this.executeHttpRequestWithRetrofit(queryTerm, categoryList)
        }
    }

    private fun executeHttpRequestWithRetrofit(queryTerm: String?, categoryList: List<String>?) {
        MyNewsStreams.streamFetchNotifications(queryTerm, categoryList, todayDate(), null).subscribeWith(object : DisposableObserver<MyNewsResultAPI>() {
            override fun onNext(nyTimesResultAPI: MyNewsResultAPI) {
                if (nyTimesResultAPI.response?.docs?.isEmpty() == false) {
                    numberOfArticle = nyTimesResultAPI.response.docs.size
                    sendNotifications()
                }
            }

            override fun onError(e: Throwable) {
                Log.e("TAG", "Error request")
            }

            override fun onComplete() {}
        }).addTo(disposable)
    }

    private fun sendNotifications() {


        //Build notification
        val repeatedNotification = buildLocalNotification(mContext).build()

        //Send local notification
        NotificationHelper.getNotificationManager(mContext!!).notify(NotificationHelper.ALARM_TYPE_RTC, repeatedNotification)
    }

    private fun buildLocalNotification(context: Context?): NotificationCompat.Builder {

        return NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_openclassrooms)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText("$numberOfArticle articles may interest you!")
                .setAutoCancel(true)
    }

    private fun todayDate(): String {
        @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat("yyyyMMdd")
        return df.format(Calendar.getInstance().time)
    }

    companion object {

        private const val NOTIFICATION_TITLE = "MyNews"

    }
}