package com.worksn.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.worksn.classes.BroadCastMsg;
import com.worksn.classes.WsServiceControl;
import com.worksn.objects.C_;
import com.worksn.singleton.MainActivityTimers;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.Usr;
import com.worksn.view.Render;
import com.worksn.websocket.WsBroadcastReceiver;
import com.worksn.websocket.WsService;

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i("MyApp", "auto restart application");
            onStart(context);
        }
    }
    private void onStart(Context context){
        new BroadCastMsg(context, C_.ACT_OPEN_ACTIVITY, WsBroadcastReceiver.BROADCAST_FILTER);
        MyStorage.i().init(context);
        if (Usr.i().auth()){
            Log.i("MyApp", "user is authorised");
            Log.i("MyAlarm", "startWsService");
            Intent intent = new Intent(new Intent(context, WsService.class));
            ActivityCompat.startForegroundService(context, intent);
        }else {
            Log.i("MyApp", "user is not authorised");
        }
    }

}
