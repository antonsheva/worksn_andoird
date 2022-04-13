package com.worksn.classes;

import android.content.Context;
import android.content.Intent;

import com.worksn.objects.C_;

import java.util.HashMap;
import java.util.Map;

public class BroadCastMsg {
    public BroadCastMsg(Context context, String act, String filter){
        Intent intent = new Intent(filter);
        intent.putExtra(C_.STR_ACT, act);
        context.sendBroadcast(intent);
    }
    public BroadCastMsg(Context context, HashMap<String, Object>data, String filter){
        Intent intent = new Intent(filter);
        String valClass = null;
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
