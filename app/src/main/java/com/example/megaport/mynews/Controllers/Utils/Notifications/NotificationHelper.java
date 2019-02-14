package com.example.megaport.mynews.Controllers.Utils.Notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.megaport.mynews.Controllers.Activities.NotificationsActivity;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class NotificationHelper {

    static final int ALARM_TYPE_RTC = 100;
    private static AlarmManager alarmManagerRTC;
    private static PendingIntent alarmIntentRTC;

    public static void scheduleRepeatingRTCNotification(Context context, String hour, String min) {
       // get calendar instance to be able to select what time notification should be scheduled
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,
                Integer.getInteger(hour, Integer.parseInt(NotificationsActivity.NOTIFICATIONS_HOUR)),
                Integer.getInteger(min,Integer.parseInt(NotificationsActivity.NOTIFICATIONS_MIN)));

        //Setting intent to class where Alarm broadcast message will be handled
        Intent intent = new Intent(context, AlarmReceiver.class);
        //Setting alarm pending intent
        alarmIntentRTC = PendingIntent.getBroadcast(context, ALARM_TYPE_RTC, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //getting instance of AlarmManager service
        alarmManagerRTC = (AlarmManager)context.getSystemService(ALARM_SERVICE);

        alarmManagerRTC.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000 * 5, AlarmManager.INTERVAL_FIFTEEN_MINUTES, alarmIntentRTC);

    }

    public static void cancelAlarmRTC() {
        if (alarmManagerRTC!= null) {
            alarmManagerRTC.cancel(alarmIntentRTC);
        }
    }

    static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
