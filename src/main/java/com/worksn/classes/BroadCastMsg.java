package com.worksn.classes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.worksn.activity.ActivityBroadcastReceiver;
import com.worksn.activity.BootCompletedReceiver;
import com.worksn.activity.MainActivity;
import com.worksn.websocket.WsBroadcastReceiver;

public class BroadCastMsg {
    public BroadCastMsg(Context context, String act, String filter){
        Intent intent = new Intent(filter);
        intent.putExtra("act", act);
        context.sendBroadcast(intent);
    }
    public BroadCastMsg(Context context, HashMap<String, Object>data, String filter){
        Intent intent = new Intent(filter);
        String valClass = null;
        Log.i("MyPrint", "BroadCastMsg ok");
        for (Map.Entry<String, Object> pair : data.entrySet()){
            if (pair.getValue() != null){
                valClass = pair.getValue().getClass().getSimpleName();
                switch (valClass){
                    case "String"  : intent.putExtra(pair.getKey(), (String)  pair.getValue());break;
                    case "Boolean" : intent.putExtra(pair.getKey(), (Boolean) pair.getValue());break;
                    case "Integer" : intent.putExtra(pair.getKey(), (Integer) pair.getValue());break;
                    case "Float"   : intent.putExtra(pair.getKey(), (Float)   pair.getValue());break;
                    case "Long"    : intent.putExtra(pair.getKey(), (Long)    pair.getValue());break;
                }
            }
        }
        context.sendBroadcast(intent);
    }
}
