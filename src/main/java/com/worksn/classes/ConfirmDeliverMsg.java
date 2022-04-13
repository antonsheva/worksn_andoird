package com.worksn.classes;

import android.content.Context;

import java.util.HashMap;

import com.worksn.objects.C_;
import com.worksn.singleton.MyStorage;
import com.worksn.websocket.WsBroadcastReceiver;

public final class ConfirmDeliverMsg {

    public ConfirmDeliverMsg(Context context, int consumerId, long discusId, int status){
        switch (status){
            case C_.CODE_CONFIRM_DELIVER:
                if (!MyStorage.i().getBoolen(C_.STR_SWITCH_CONFIRM_DELIVER)) return;
                break;
            case C_.CODE_CONFIRM_VIEWED:
                if (!MyStorage.i().getBoolen(C_.STR_SWITCH_CONFIRM_VIEWED)) return;
                break;
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(C_.STR_ACT, C_.ACT_CONFIRM_DELIVER_MSG);
        hashMap.put(C_.STR_CONSUMER_ID, consumerId);
        hashMap.put(C_.STR_DISCUS_ID, discusId);
        hashMap.put(C_.STR_STATUS_MSG, status);
        new BroadCastMsg(context, hashMap, WsBroadcastReceiver.BROADCAST_FILTER);
    }
}