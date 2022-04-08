package com.worksn.websocket;

import android.content.Context;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.worksn.interfaces.NetCallback;
import com.worksn.objects.C_;
import com.worksn.objects.MyContext;
import com.worksn.objects.StorageConst;
import com.worksn.singleton.MyStorage;
import com.worksn.static_class.Post;

public class WsTimer {
    private static final int sek = 1000;
    private static final int min = 60000;
    private static final int hour = 3600000;
    private static final int day  = 3600000*24;

    public static final int CB_CODE_NO_CONNECTION = 1;
    private static WsTimer i;
    public static WsTimer i(){
        if (i == null) i = new WsTimer();
        return i;
    }
    CB cb;
    Timer checkNewMsg = null;
    Timer timerCheckConnect = null;
    Timer mTimerRefreshSession = null;
    public long lastPingTime;

    public void refreshLastPingTime(){
        lastPingTime = new Date().getTime();
    }
    public void initCbListener(CB cb){
        this.cb = cb;
    }
    public void initCheckConnectionTimer(boolean stt){
        refreshLastPingTime();
        if(timerCheckConnect != null){
            timerCheckConnect.cancel();
            timerCheckConnect = null;
        }
        if (stt) {
            timerCheckConnect = new Timer();
            timerCheckConnect.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (Ws.connectionState){
                        if(!Ws.socketAuth){
                            Ws.sendAuthUserData();
                        }
                    }else {
                        cb.cb(CB_CODE_NO_CONNECTION);
                    }
                }
            }, 2000, 30000);
        }
    }

    public void setCheckNewMsg(){
        if (checkNewMsg != null){
            checkNewMsg.cancel();
            checkNewMsg = null;
        }
        checkNewMsg = new Timer();
        checkNewMsg.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean newMsgSign = MyStorage.i().getBoolen(StorageConst.NEW_MSG_SIGN);
                if (!newMsgSign)
                    Ws.sendCheckNewMsg();
            }
        }, 3000);
    }
    public void initRefreshSessionTimer(Context context, boolean stt){
        if(mTimerRefreshSession != null){
            mTimerRefreshSession.cancel();
            mTimerRefreshSession = null;
        }
        if (!stt)return;
        mTimerRefreshSession = new Timer();
        mTimerRefreshSession.schedule(new TimerTask() {
            @Override
            public void run() {
                Post.sendRequest(context,C_.ACT_REFRESH_SESSION, null, new NetCallback() {
                    @Override
                    public void callback(MyContext data, Integer result, String stringData) {
                        Log.i("MySession","REFRESH_SESSION");
                    }
                });
            }
        }, 30*min, 80*min);
    }
    public interface CB{
        void cb(int code);
    }
}