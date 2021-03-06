package com.worksn.websocket;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.worksn.R;
import com.worksn.classes.BroadCastMsg;
import com.worksn.objects.StructMsg;
import com.worksn.websocket.init_ssl.NetResponse;
import com.worksn.websocket.init_ssl.NetService;
import retrofit2.Call;
import retrofit2.Callback;
import com.worksn.activity.MainActivity;
import com.worksn.activity.ActivityBroadcastReceiver;
import com.worksn.classes.WsServiceControl;
import com.worksn.singleton.MyStorage;
import com.worksn.objects.C_;
import com.worksn.singleton.Usr;
import com.worksn.websocket.init_ssl.RequestData;

public class WsService extends Service {
    private  WsEvents wsEvents;
    private static boolean sServiceRun = false;
    private static boolean sSendInitWs = false;
    private static boolean sSendPingServerRequest = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        MyStorage.i().init(this);
        WsTimer.i().refreshLastPingTime();
        init();
        initBroadcastReceiver();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long mTime = new Date().getTime();
        long mDelta = (mTime - WsTimer.i().lastPingTime)/1000;
        if (!sServiceRun){
            WsTimer.i().refreshLastPingTime();
            init();
        } else {
            if (mDelta < 200){
                sSendPingServerRequest = false;
            }else {
                if (!sSendPingServerRequest){
                    Ws.sendPingServer();
                    sSendPingServerRequest = true;
                } else {
                    WsTimer.i().refreshLastPingTime();
                    init();
                }
            }
        }
        return Service.START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        sServiceRun = false;
        WsBroadcastReceiver.i().clear(getApplicationContext());
        WsTimer.i().initRefreshSessionTimer(this, false);
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }
    private static void initNet(Ws.MyCallBack myCallBack){
        NetService.getInstance()
                .getWsnApi()
                .getData(myCallBack.getObject())
                .enqueue(new Callback<NetResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<NetResponse> call, retrofit2.Response<NetResponse> response) {
                        myCallBack.callback(1);
                    }
                    @Override
                    public void onFailure(@NotNull Call<NetResponse> call, @NotNull Throwable t) {

                    }
                });
    }

    private void init(){
        sServiceRun = true;
        createNotifyLabel();
        new BroadCastMsg(this,C_.ACT_WS_IS_START,ActivityBroadcastReceiver.BROADCAST_FILTER);
        wsEvents = new WsEvents();
        initWsEventListener();
        initTimerListener();
        WsTimer.i().initCheckConnectionTimer(true);
        WsTimer.i().initRefreshSessionTimer(this, true);
        Ws.setUser(Usr.i().getUser());
        initSsl();
    }
    private void initSsl(){
        RequestData postData = new RequestData(C_.ACT_GET_TOKEN);
        initNet(new Ws.MyCallBack() {
            @Override
            public void callback(int code) {
                if (code == 1){
                    Ws.initSocket();
                }else {
                    Log.i("MyNet", "error net request");
                }
            }
            @Override
            public RequestData getObject() {
                return postData;
            }
        });
    }
    private void initBroadcastReceiver(){
        WsBroadcastReceiver.i().init(this, new WsBroadcastReceiver.CB() {
            @Override
            public void cb(int code, Object o) {
                setBroadcastCb(code, o);
            }
        });
    }
    private void setBroadcastCb(int code, Object o){
        switch (code){
            case C_.CODE_EXIT: exit();      break;
            case C_.CODE_WAKEUP: wakeup();  break;
        }
    }
    private void wakeup(){
        init();
    }
    private void exit(){
        Ws.sendExit();
        Ws.setUser(null);
        Usr.i().setUser(null);
        WsTimer.i().initCheckConnectionTimer(false);
        Ws.clearSocketData();
//        stopSelf();
    }
    private void initTimerListener(){
        WsTimer.i().initCbListener(new WsTimer.CB() {
            @Override
            public void cb(int code) {
                switch (code){
                    case WsTimer.CB_CODE_NO_CONNECTION:
                        errorConnection();
                        break;
                }
            }
        });
    }
    private void errorConnection(){
        Timer tm = new Timer();
        if (!sSendInitWs){
            sSendInitWs = true;
            Ws.clearSocketData();
            Ws.initSocket();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    sSendInitWs = false;
                    tm.cancel();
                }
            }, 5000);
        }
    }
    private void initWsEventListener(){
        Context context = this;
        Ws.initCallback(new Ws.WsCallback() {
            @Override
            public void callback(int code, Object object) {
                switch (code){
                    case C_.CODE_NEW_MSG            : wsEvents.receivedNewMsg(context, (StructMsg) object);               break;
                    case C_.CODE_ON_CONNECT         : wsEvents.wsEventOnConnect();                                        break;
                    case C_.CODE_ON_CLOSE           : wsEvents.wsEventOnClose();                                          break;
                    case C_.CODE_ON_FAILURE         : wsEventOnFailure();                                                 break;
                    case C_.CODE_WS_AUTH_USER       : wsEvents.wsRcvAuthUser((String)object);                             break;
                    case C_.CODE_CONFIRM_DELIVER    : wsEvents.wsRcvConfirmDeliverMsg(context, (WsReceiveData) object);   break;
                    case C_.CODE_WS_ONLINE_LIST     : wsEvents.wsRvcOnlineList(context,(String)object);                   break;
                    case C_.CODE_WS_PRINT_TXT       : wsEvents.wsRcvPrintTxt(context, (Long) object);                     break;
                    case C_.CODE_WS_BAD_AUTH_DATA   : wsEvents.wsRvcBadAuthData(context);                                 break;
                    case C_.CODE_WS_NEW_AUTH_DATA   : receiveNewAuthData();                                               break;
                    case C_.CODE_WS_SEND_ERROR      : errorConnection();                                                  break;
                    case C_.CODE_WS_BIND_IMG_TO_MSG : wsEvents.bindImageToMessage(context, (WsReceiveData) object);       break;
                    case C_.CODE_WS_PING            : wsEvents.wsRcvPing();                                               break;
                    case C_.CODE_WS_PONG_SERVER     : wsEvents.wsRcvPongServer();                                         break;
                    case C_.CODE_WS_NEW_TOKEN       : receiveNewToken((String)object);
                }
            }
        });
    }
    private void wsEventOnFailure(){
        if (!Usr.i().auth())return;
        Ws.connectionState = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                initSsl();
            }
        }, 3000);
    }
    private void receiveNewToken(String token){
        Usr.i().setNewToken(token);
        Ws.setNewToken(token);
    }
    private void receiveNewAuthData(){
        new BroadCastMsg(this, C_.ACT_NEW_AUTH_DATA, ActivityBroadcastReceiver.BROADCAST_FILTER);
        new WsServiceControl(this).stop();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Ws.setUser(null);
                Usr.i().setUser(null);
            }
        }, 1000);
        stopSelf();
    }
    private void badAuthData(){
        Ws.initSocket();
    }
    private void createNotifyLabel(){
        Context context = this;
        String title  = context.getString(R.string.notifyTitle);
        String text   = context.getString(R.string.notifyText);
        String ticker = context.getString(R.string.notifyTicker);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        Notification notification =
                new NotificationCompat.Builder(this, C_.NOTIFY_CHANNEL_FOREGROUND)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setSmallIcon(R.drawable.notification)
                        .setContentIntent(pendingIntent)
                        .setTicker(ticker)
                        .build();
        startForeground(11, notification);
    }

    public static class AlarmReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            MyStorage.i().init(context);
            Intent intent1 = new Intent(context, WsBroadcastReceiver.class);
            intent.putExtra(C_.STR_ACT, C_.ACT_WAKEUP);
            context.sendBroadcast(intent1);
        }
    }
}




