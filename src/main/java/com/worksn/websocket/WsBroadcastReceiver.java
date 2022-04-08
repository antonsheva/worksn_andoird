package com.worksn.websocket;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.security.Provider;
import java.util.Timer;
import java.util.TimerTask;

import com.worksn.classes.ConvertMsgData;
import com.worksn.objects.C_;
import com.worksn.objects.SaveImgData;
import com.worksn.objects.StructMsg;
import com.worksn.objects.User;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.Usr;

public class WsBroadcastReceiver extends BroadcastReceiver{
    public final static String BROADCAST_FILTER = "activity_to_ws_filter";
    private CB cb;

    private static WsBroadcastReceiver i;
    public static WsBroadcastReceiver i(){
        if (i == null) i = new WsBroadcastReceiver();
        return i;
    }

    public void init(Context context, CB cb){
        this.cb = cb;
        IntentFilter intentFilter = new IntentFilter(BROADCAST_FILTER);
        try{
            context.registerReceiver(this, intentFilter);
        }catch(Exception ignored){}
    }
    public void clear(Context context){
        try{
            context.unregisterReceiver(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void processingReceiveData(Context context, Intent intent){
        String act = intent.getStringExtra("act");
        if (act == null)return;
        switch (act){
            case C_.ACT_EXIT               : exit();                         break;
            case C_.ACT_CHECK_NEW_MSG      : checkNewMsg();                  break;
            case C_.ACT_CONFIRM_DELIVER_MSG: confirmDeliverMsg(intent);      break;
            case C_.ACT_NEW_MSG            : sendMsg(intent);                break;
            case C_.ACT_GET_ONLINE_STATUS  : getUserStatus(intent);          break;
            case C_.ACT_CLOSE_ACTIVITY     : openActivity(false);        break;
            case C_.ACT_OPEN_ACTIVITY      : openActivity(true);         break;
            case C_.ACT_WAKEUP             : actWakeup();                    break;
            case C_.ACT_PRINT_MSG_PROCESS  : wsSendPrintMsgProcess(intent);  break;
            case C_.ACT_BIND_IMG_TO_MSG    : wsSendBindImgToMsg(intent);     break;
            case C_.ACT_CONNECTION_OK      : connectionStateChange(true);    break;
            case C_.ACT_CONNECTION_ERROR   : connectionStateChange(false);    break;
        }
    }

    private void connectionStateChange(boolean state){
        Log.i("MyConnect", "-> "+state);
        if (state)
            Ws.initSocket();
    }
    private void wsSendBindImgToMsg(Intent intent){
        Log.i("MyBroadcast", " -> start");
        String createId   = intent.getStringExtra("create_id");
        String img        = intent.getStringExtra("img");
        String imgIcon    = intent.getStringExtra("img_icon");
        long   msgId      = intent.getLongExtra  ("msg_id", 0);
        int    consumerId = intent.getIntExtra   ("consumer_id", 0);

        SaveImgData saveImgData = new SaveImgData();
        saveImgData.setCreateId(createId);
        saveImgData.setImg(img);
        saveImgData.setImgIcon(imgIcon);
        saveImgData.setMsgId(msgId);
        saveImgData.setConsumerId(consumerId);

        Ws.sendBindImgToMsg(saveImgData);
    }
    private void wsSendPrintMsgProcess(Intent intent){
        if (!MyStorage.i().getBoolen(C_.VAR_SWITCH_SEND_PRINT_TEXT))return;

        long discusId  = intent.getLongExtra("discus_id", 0);
        int consumerId = intent.getIntExtra("consumer_id", 0);
        Ws.sendPrintMsgProcess(discusId, consumerId);
    }
    private void actWakeup(){
        Log.i("MyWakeup", "enable Notify");
        cb.cb(C_.CODE_WAKEUP, null);
    }

    private void openActivity(boolean stt){
        Ws.setEnableNotification(!stt);
    }

    private void exit(){
        cb.cb(C_.CODE_EXIT, null);
    }
    private void sendMsg(Intent intent){
        StructMsg msg = new ConvertMsgData().intentExtrasToMsg(intent);
        Ws.sendMsg(msg);
    }
    private void confirmDeliverMsg(Intent intent){
        long discusId;
        int consumerId, statusMsg;

        discusId   = intent.getLongExtra("discusId", 0);
        consumerId = intent.getIntExtra("consumerId", 0);
        statusMsg  = intent.getIntExtra("status_msg", 0);

        Ws.sendConfirmDeliverMsg(consumerId, discusId, statusMsg);
    }
    private void checkNewMsg(){
        Ws.sendCheckNewMsg();
    }
    private Timer mGetStatusTimer;
    private void getUserStatus(Intent intent){
        if (mGetStatusTimer != null){
            mGetStatusTimer.cancel();
            mGetStatusTimer = null;
        }
        mGetStatusTimer = new Timer();
        mGetStatusTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                String idList = intent.getStringExtra("list_id");
                Ws.sendGetOnlineStatus(idList);
            }
        }, 2000);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        processingReceiveData(context, intent);
    }

    public interface CB{
        void cb(int code, Object o);
    }
}
