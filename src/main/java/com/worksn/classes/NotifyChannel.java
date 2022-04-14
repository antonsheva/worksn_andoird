package com.worksn.classes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import com.worksn.R;
import com.worksn.objects.C_;

public class NotifyChannel {

    public NotifyChannel(Context context){
        initMainChanel(context);
        initForegroundChanel(context);
    }
    private void initMainChanel(Context context) {
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name_main);
            String description = context.getString(R.string.channel_main_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(C_.NOTIFY_CHANNEL_MAIN, name, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setDescription(description);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{0, 500, 1000});
            if (ringtoneUri != null){
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build();
                channel.setSound(ringtoneUri,audioAttributes);
            }
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void initForegroundChanel(Context context) {
        Uri ringtoneUri = Uri.parse("android.resource://com.worksn/" +R.raw.snd1);
//        Uri ringtoneUri = Funcs.getUri(R.raw.snd1);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name_foreground);
            String description = context.getString(R.string.channel_foreground_description);
            int importance = NotificationManager.IMPORTANCE_LOW; //NotificationManager.IMPORTANCE_DEFAULT
            NotificationChannel channel = new NotificationChannel(C_.NOTIFY_CHANNEL_FOREGROUND, name, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setDescription(description);
            if (ringtoneUri != null){
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build();
                channel.setSound(ringtoneUri,audioAttributes);
            }
            notificationManager.createNotificationChannel(channel);
        }
    }
}
