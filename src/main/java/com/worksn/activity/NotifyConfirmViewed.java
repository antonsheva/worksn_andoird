package com.worksn.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.worksn.classes.ConfirmDeliverMsg;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.MyStorageConst;
import com.worksn.singleton.MyStorage;

public class NotifyConfirmViewed extends Activity {
    Context context = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent     = getIntent();
        int consumerId    = intent.getIntExtra(C_.STR_SENDER_ID,  0);
        long discusId      = intent.getLongExtra(C_.STR_DISCUS_ID,  0);
        G_.isQuickResponse = true;
        if ((consumerId != 0) && (discusId != 0)) {
            new ConfirmDeliverMsg(this, consumerId, discusId, C_.CODE_CONFIRM_VIEWED);
            MyStorage.i().putData(MyStorageConst.NEW_MSG_SIGN, false);
        }
        new MyNotify().removeNotify(context);
        finish();
    }
}
