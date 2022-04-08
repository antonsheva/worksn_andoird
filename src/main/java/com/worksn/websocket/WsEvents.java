package com.worksn.websocket;



import android.content.Context;
import android.util.Log;

import com.worksn.classes.ConvertMsgData;
import com.worksn.objects.StructMsg;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.worksn.activity.ActivityBroadcastReceiver;
import com.worksn.activity.MainActivity;
import com.worksn.classes.BroadCastMsg;
import com.worksn.classes.ConvertMsgData;
import com.worksn.objects.C_;
import com.worksn.objects.StorageConst;
import com.worksn.singleton.MyStorage;
import com.worksn.activity.MyNotify;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.Usr;

public class WsEvents {
    private static boolean sOnConnectRequestTimeout = false;

    public void wsEventOnConnect() {
        Log.i("MyConnect", "ok");
        Timer tm = new Timer();
        if (!sOnConnectRequestTimeout){
            sOnConnectRequestTimeout = true;
            Ws.sendAuthUserData();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    sOnConnectRequestTimeout = false;
                    tm.cancel();
                }
            }, 3000);
        }
    }
    public void wsEventOnClose(){
        Ws.socketAuth = false;
    }

    public void wsRcvAuthUser(String newToken){
        if (newToken != null) {
            Usr.i().setNewToken(newToken);
            Ws.setNewToken(newToken);
        }

        WsTimer.i().setCheckNewMsg();
    }
    public void receivedNewMsg(Context context, StructMsg msg){
        Log.i("MyMsg", "new msg WsService");
        if (msg.getDiscus_id() == null)return;
        HashMap<String, Object>map = new ConvertMsgData().rcvMsgToMap(msg);
        MyStorage.i().putData(StorageConst.NEW_MSG_SIGN, true);
        MyStorage.i().putData(StorageConst.NEW_MSG_DISCUS_ID, msg.getDiscus_id());
        new BroadCastMsg(context, map, ActivityBroadcastReceiver.BROADCAST_FILTER);
        if(!MyStorage.i().getBoolen(StorageConst.MAIN_ACTIVITY_IS_ACTIVE))
            new MyNotify().newNotify(context, msg, "Сообщение от ", true);
    }
    public void wsRcvConfirmDeliverMsg(Context context, WsReceiveData data){
        Log.i("MyMsg", " confirmViewedMsg ");
        HashMap<String, Object>map = new HashMap<>();
        map.put("act", C_.ACT_CONFIRM_DELIVER_MSG);
        map.put(C_.VAR_DISCUS_ID, data.getDiscusId());
        map.put(C_.VAR_STATUS_MSG, data.getStatusMsg());
        new BroadCastMsg(context, map, ActivityBroadcastReceiver.BROADCAST_FILTER);
    }
    public void wsRvcOnlineList(Context context, String idListStr){
        HashMap<String, Object>map = new HashMap<>();
        map.put("act", C_.ACT_ONLINE_LIST);
        map.put(C_.VAR_ID_LIST, idListStr);
        new BroadCastMsg(context, map, ActivityBroadcastReceiver.BROADCAST_FILTER);
    }
    public void wsRvcBadAuthData(Context context){
        Log.i("MyWs", "ACT_BAD_AUTH_DATA");
        new BroadCastMsg(context, C_.ACT_BAD_AUTH_DATA, ActivityBroadcastReceiver.BROADCAST_FILTER);
    }
    public void wsRcvPrintTxt(Context context, Long discusId){
        Log.i("MyPrint", "wsRcvPrintTxt ok");
        HashMap<String, Object>map = new HashMap<>();
        map.put("act", C_.ACT_PRINT_MSG_PROCESS);
        map.put(C_.VAR_DISCUS_ID, discusId);
        new BroadCastMsg(context, map, ActivityBroadcastReceiver.BROADCAST_FILTER);
    }
    public void wsRcvPing(){
        WsTimer.i().refreshLastPingTime();
        Ws.sendPong();
    }
    public void wsRcvPongServer(){
        WsTimer.i().refreshLastPingTime();
    }
    public void bindImageToMessage(Context context, WsReceiveData data){
        HashMap<String, Object>map = new HashMap<>();
        map.put("act", C_.ACT_BIND_IMG_TO_MSG);
        map.put(C_.VAR_MSG_ID, data.getMsgId());
        map.put(C_.VAR_IMG, data.getImg());
        map.put(C_.VAR_IMG_ICON, data.getImgIcon());
        new BroadCastMsg(context, map, ActivityBroadcastReceiver.BROADCAST_FILTER);
    }
}
