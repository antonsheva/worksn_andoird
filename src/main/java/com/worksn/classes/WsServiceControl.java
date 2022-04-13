package com.worksn.classes;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.core.app.ActivityCompat;

import com.worksn.objects.MyStorageConst;
import com.worksn.singleton.MyStorage;
import com.worksn.websocket.WsAlarmManager;
import com.worksn.websocket.WsAlarmReceiver;
import com.worksn.websocket.WsService;

public class WsServiceControl {
    Context context;
    public WsServiceControl(Context context){
        this.context = context;
    }
    public void start(){
        WsAlarmReceiver wsAlarmReceiver = new WsAlarmReceiver();
        context.registerReceiver(wsAlarmReceiver, new IntentFilter(WsAlarmReceiver.BROADCAST_FILTER));

        new WsAlarmManager().CancelAlarm(context);
        new WsAlarmManager().SetAlarm(context, 240);

        Intent intent = new Intent(new Intent(context, WsService.class));
        ActivityCompat.startForegroundService(context, intent);
    }
    public void stop(){
        new WsAlarmManager().CancelAlarm(context);
        Intent intent = new Intent(context, WsService.class);
        MyStorage.i().putData(MyStorageConst.STOP_FROM_ACTIVITY, true);
        context.stopService(intent);
    }
}
