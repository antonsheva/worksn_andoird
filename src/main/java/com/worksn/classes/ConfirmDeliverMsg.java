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
                if (!MyStorage.i().getBoolen(C_.VAR_SWITCH_CONFIRM_DELIVER)) return;
                break;
            case C_.CODE_CONFIRM_VIEWED:
                if (!MyStorage.i().getBoolen(C_.VAR_SWITCH_CONFIRM_VIEWED)) return;
                break;
        }


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("act", C_.ACT_CONFIRM_DELIVER_MSG);
        hashMap.put("consumerId", consumerId);
        hashMap.put("discusId", discusId);
        hashMap.put("status_msg", status);
        new BroadCastMsg(context, hashMap, WsBroadcastReceiver.BROADCAST_FILTER);
    }
}