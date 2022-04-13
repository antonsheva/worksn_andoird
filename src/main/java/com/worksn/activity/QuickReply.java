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

import com.worksn.R;
import com.worksn.classes.BroadCastMsg;
import com.worksn.classes.ConfirmDeliverMsg;
import com.worksn.classes.ConvertMsgData;
import com.worksn.objects.C_;
import com.worksn.objects.MyStorageConst;
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
        Intent intent     = getIntent();
        int consumerId    = intent.getIntExtra(C_.STR_SENDER_ID,  0);
        long adsId         = intent.getLongExtra(C_.STR_ADS_ID,     0);
        long discusId      = intent.getLongExtra(C_.STR_DISCUS_ID,  0);
        String createDate = intent.getStringExtra(C_.STR_CREATE_DATE);


        Bundle results = RemoteInput.getResultsFromIntent(intent);
        if ((results != null) && (consumerId != 0)&&(adsId != 0) && (discusId != 0)) {
            replyText = results.getCharSequence(C_.STR_NOTIFY_FIELD);

            PostSubData subData = new PostSubData();
            subData.setSenderId(Usr.i().getUser().getId());
            subData.setConsumerId(consumerId);
            subData.setAds_id(adsId);
            subData.setContent( replyText.toString());

            StructMsg msg = new StructMsg();
            msg.setConsumerId(consumerId);
            msg.setSender_id(Usr.i().getUser().getId());
            msg.setAds_id(adsId);
            msg.setCreateDate(createDate);
            msg.setContent(replyText.toString());
            msg.setDiscus_id(discusId);
            Context context = getApplicationContext();
            new ConfirmDeliverMsg(this, consumerId, discusId, C_.CODE_CONFIRM_VIEWED);
            MyStorage.i().putData(MyStorageConst.NEW_MSG_SIGN, false);
            Post.sendRequest(context,C_.ACT_ADD_MSG, subData, new NetCallback() {
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
                        StructMsg errorMsg = new StructMsg();
                        errorMsg.setContent(stringData);
                        new MyNotify().newNotify(context, errorMsg, context.getString(R.string.someThrowable), false);
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
