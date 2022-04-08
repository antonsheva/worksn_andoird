package com.worksn.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.worksn.classes.ConfirmDeliverMsg;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.singleton.MyStorage;

public class NotifyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyBroadcast", "i'm here");
        int consumerId    = intent.getIntExtra(C_.VAR_CONSUMER_ID,  0);
        long discusId      = intent.getLongExtra(C_.VAR_DISCUS_ID,  0);
        G_.isQuickResponse = true;
        if ((consumerId != 0) && (discusId != 0)) {
            new ConfirmDeliverMsg(context, consumerId, discusId, C_.CODE_CONFIRM_VIEWED);
            MyStorage.i().putData("new_msg_sign", false);
        }
        new MyNotify().removeNotify(context);
    }
}
