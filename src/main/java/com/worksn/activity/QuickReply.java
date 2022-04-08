package com.worksn.activity;

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
import com.worksn.objects.C_;
import com.worksn.singleton.MyStorage;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.G_;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostSubData;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Post;
import com.worksn.websocket.WsBroadcastReceiver;

public class QuickReply extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CharSequence replyText = null;
        Log.i("MyQuick", "------------------");
        Intent intent     = getIntent();
        int consumerId    = intent.getIntExtra("sender_id",  0);
        long adsId         = intent.getLongExtra("ads_id",     0);
        long discusId      = intent.getLongExtra("discus_id",  0);
        String createDate = intent.getStringExtra("create_date");


        Bundle results = RemoteInput.getResultsFromIntent(intent);
        if ((results != null) && (consumerId != 0)&&(adsId != 0) && (discusId != 0)) {
            replyText = results.getCharSequence("notify_field");
            Log.i("MyQuickReply", replyText.toString());

            PostSubData subData = new PostSubData();
            subData.setSenderId(Usr.i().getUser().getId());
            subData.setConsumerId(consumerId);
            subData.setAds_id(adsId);
            subData.setContent( replyText.toString());

            StructMsg msg = new StructMsg();
            msg.setConsumer_id(consumerId);
            msg.setSender_id(Usr.i().getUser().getId());
            msg.setAds_id(adsId);
            msg.setCreateDate(createDate);
            msg.setContent(replyText.toString());
            msg.setDiscus_id(discusId);
            Context context = getApplicationContext();
            new ConfirmDeliverMsg(this, consumerId, discusId, C_.CODE_CONFIRM_VIEWED);
            MyStorage.i().putData("new_msg_sign", false);
            Post.sendRequest(context,"add_msg", subData, new NetCallback() {
                @Override
                public void callback(MyContext data, Integer result, String stringData) {
                    if (result == 1){
                        HashMap<String, Object>map = new ConvertMsgData().sendMsgToMap(msg);
                        new BroadCastMsg(context, map, WsBroadcastReceiver.BROADCAST_FILTER);
                        new MyNotify().removeNotify(context);
                        G_.isQuickResponse = true;
                        finish();
                    }
                    if (result == -1){
                        Log.i("MyQuickResponse", "rere");
                        StructMsg errorMsg = new StructMsg();
                        errorMsg.setContent(stringData);
                        new MyNotify().newNotify(context, errorMsg, "Что-то пошло не так :-( ", false);
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                new MyNotify().removeNotify(context);
                                G_.isQuickResponse = true;
                                finish();
                            }
                        }, 7000);
                    }
                }
            });
        }
    }
}
