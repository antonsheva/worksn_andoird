package com.worksn.websocket;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.worksn.objects.C_;


public class WsAlarmManager {
    @SuppressLint("ShortAlarm")
    public void SetAlarm(Context context, long tm)
    {
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WsAlarmReceiver.class);
        intent.putExtra(C_.STR_ACT, C_.ACT_WAKEUP);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        if (alarmManager != null)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * tm , pi);
    }
    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, WsAlarmReceiver.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null)
            alarmManager.cancel(sender);
    }
}
