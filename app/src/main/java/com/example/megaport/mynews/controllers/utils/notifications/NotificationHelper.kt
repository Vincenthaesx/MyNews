package com.example.megaport.mynews.controllers.utils.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar
import android.content.Context.ALARM_SERVICE

object NotificationHelper {

    private var alarmManagerRTC: AlarmManager? = null
    private var alarmIntentRTC: PendingIntent? = null

    internal const val ALARM_TYPE_RTC = 100

    fun scheduleRepeatingRTCNotification(context: Context) {
        //get calendar instance to be able to select what time notification should be scheduled
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 14)
        calendar.set(Calendar.MINUTE, 29)
        calendar.set(Calendar.SECOND, 59)

        //Setting intent to class where Alarm broadcast message will be handled
        val intent = Intent(context, AlarmReceiver::class.java)
        //Setting alarm pending intent
        alarmIntentRTC = PendingIntent.getBroadcast(context, ALARM_TYPE_RTC, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //getting instance of AlarmManager service
        alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager

        alarmManagerRTC?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntentRTC)

    }

    fun cancelAlarmRTC() {
        if (alarmManagerRTC != null) {
            alarmManagerRTC!!.cancel(alarmIntentRTC)
        }
    }
}
