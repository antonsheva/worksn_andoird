package com.worksn.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.worksn.classes.BroadCastMsg;
import com.worksn.classes.ConfirmDeliverMsg;
import com.worksn.classes.ConvertMsgData;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostSubData;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Post;
import com.worksn.websocket.WsBroadcastReceiver;

public class NotifyConfirmViewed extends Activity {
    Context context = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MyQuick", "------------------");
        Intent intent     = getIntent();
        int consumerId    = intent.getIntExtra("sender_id",  0);
        long discusId      = intent.getLongExtra("discus_id",  0);
        G_.isQuickResponse = true;
        if ((consumerId != 0) && (discusId != 0)) {
            new ConfirmDeliverMsg(this, consumerId, discusId, C_.CODE_CONFIRM_VIEWED);
            MyStorage.i().putData("new_msg_sign", false);

        }
        new MyNotify().removeNotify(context);
        finish();
    }
}
