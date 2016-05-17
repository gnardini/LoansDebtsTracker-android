package com.gnardini.frienddebttracker.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.DateUtils;

import com.gnardini.frienddebttracker.DebtsApp;
import com.gnardini.frienddebttracker.notification.ReminderBroadcastReceiver;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmScheduler {

    private static final String KEY_ALARM_UPDATE = "LoansAndDebts.KEY_ALARM_UPDATE";

    public static void setAlarm(Context context, boolean forceUpdate) {
        Intent brIntent = new Intent(context, ReminderBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, brIntent,0);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
        calendar.set(GregorianCalendar.MINUTE, 0);
        calendar.set(GregorianCalendar.SECOND, 0);
        calendar.set(GregorianCalendar.MILLISECOND, 0);
        long midDayMillis = calendar.getTimeInMillis();
        long currentMillis = System.currentTimeMillis();
        if (currentMillis > midDayMillis) {
            midDayMillis += DateUtils.DAY_IN_MILLIS;
        }

        SharedPreferences preferences = context.getSharedPreferences(
                DebtsApp.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(KEY_ALARM_UPDATE) && !forceUpdate) {
            return;
        }
        preferences.edit().putLong(KEY_ALARM_UPDATE, midDayMillis).apply();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, midDayMillis, pendingIntent);
    }

}
