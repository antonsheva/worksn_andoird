package com.worksn.websocket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class WsAlarmReceiver extends BroadcastReceiver {
    public static final String BROADCAST_FILTER = "ws_alarm_receiver_filter";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyAlarm", "WsAlarmReceiver");
        startWsService(context);
    }

    private void startWsService(Context context){
        Log.i("MyAlarm", "startWsService");
        Intent intent = new Intent(new Intent(context, WsService.class));
        ActivityCompat.startForegroundService(context, intent);
    }

}
