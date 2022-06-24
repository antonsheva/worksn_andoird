package com.worksn.websocket;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.worksn.objects.C_;


public class WsAlarmManager {
    @SuppressLint("ShortAlarm")
    public void SetAlarm(Context context, long tm)
    {
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WsAlarmReceiver.class);
        intent.putExtra(C_.STR_ACT, C_.ACT_WAKEUP);
        try{
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            if (alarmManager != null)
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * tm , pi);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void CancelAlarm(Context context)
    {
        try{
            Intent intent = new Intent(context, WsAlarmReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null)
                alarmManager.cancel(sender);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
