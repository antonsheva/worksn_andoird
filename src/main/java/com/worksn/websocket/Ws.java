package com.worksn.websocket;


import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.worksn.objects.C_;
import com.worksn.websocket.init_ssl.RequestData;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.PostSubData;
import com.worksn.objects.SaveImgData;
import com.worksn.objects.StructMsg;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import com.worksn.objects.User;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Post;
import com.worksn.websocket.init_ssl.RequestData;

public class Ws {
    private static int badConnectionQt = 0;
    private static final ArrayDeque<String> sendMsgQueue = new ArrayDeque<>();
    private static WebSocket socket;
    private static WsCallback cb;
    private static WsUserStatusCb userStatusCb;
    private static WsUserStatusCb1 userStatusCb1;
    private static User user;
    private static boolean enableNotification = false;
    public static boolean connectionState;
    private static WsReceiveData tmpData;


    public static boolean isEnableNotification() {
        return enableNotification;
    }

    public static void setEnableNotification(boolean enableNotification) {
        Ws.enableNotification = enableNotification;
    }




    public static boolean socketAuth;

    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static final int ABORT_CONNECTION_CLOSURE_STATUS = 1001;

    static OkHttpClient sClient;
    static Request sRequest;
    static OkHttpClient.Builder sBuilder;

    public static void clearSocketData(){
        if (socket != null){
            try{
                socket.close(NORMAL_CLOSURE_STATUS,"111");
            }catch (Exception ignored){}
        }
        sClient = null;
        sRequest = null;
        sBuilder = null;
        socket = null;

        socketAuth = false;
        connectionState = C_.CONN_OFF;
    }
    public static void initSocket(){
        clearSocketData();
        sBuilder = new OkHttpClient.Builder();
        sClient = sBuilder.build();
        sRequest = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url("wss://worksn.ru:8000")
                .build();


        socket = sClient.newWebSocket(sRequest, new WebSocketListener() {


            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                Log.i("MyWs", "Connection accepted!");
                    connectionState = C_.CONN_ON;
                    cb.callback(C_.CODE_ON_CONNECT, null);
            }
            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                processingMsg(text);
            }
            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {

            }
            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                webSocket.close(NORMAL_CLOSURE_STATUS, null);
                Log.i("MyWs", "Closing : " + code + " / " + reason);
                connectionState = C_.CONN_OFF;
                cb.callback(C_.CODE_ON_CLOSE, null);
            }
            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
                Log.i("MyWsOnFailure", "Error : " + t.getMessage());
                webSocket.close(ABORT_CONNECTION_CLOSURE_STATUS, null);
                connectionState = C_.CONN_OFF;
                cb.callback(C_.CODE_ON_FAILURE, null);

            }});
    }

    private static void processingMsg(String msg){
        Log.i("MyWsReceive", "  data -> "+msg);
        WsReceiveData wsData = new WsReceiveData();
        wsData = new Gson().fromJson(msg,wsData.getClass());
        G_.date_time = wsData.getDate_time();
        switch (wsData.getType()){
            case C_.ACT_AUTH_USER           : rcvAuthUser(wsData);                 break;
            case C_.ACT_ONLINE_LIST         : rcvOnlineList(wsData);         break;
            case C_.ACT_NEW_MSG             : rcvNewMsg(wsData);             break;
            case C_.ACT_CONFIRM_DELIVER_MSG : rcvConfirmViewed(wsData);      break;
            case C_.ACT_EXIT                : rcvWsExit();                   break;
            case C_.ACT_NEW_AUTH_DATA       : rcvNewAuthData();              break;
            case C_.ACT_PING                : rcvPing();                 break;
            case C_.ACT_PONG_SERVER         : rcvPongServer();               break;
            case C_.ACT_PRINT_MSG_PROCESS   : rcvPrintMsgProcess(wsData);    break;
            case C_.ACT_BIND_IMG_TO_MSG     : rcvBindImgToMsg(wsData); break;
            case C_.ACT_NEW_TOKEN           : rcvNewToken(wsData);break;
        }
    }

    public static User getUser() {
        return user;
    }
    public static void setUser(User usr) {
        if (usr == null){
            user = null;
        }else {
            user = usr;
        }
    }
    public static void setNewToken(String newToken){
        user.setWsToken(newToken);
    }

    public static void initCallback(WsCallback wsCallback){
        cb = wsCallback;
    }

    public static void sendBindImgToMsg(SaveImgData data){

        WsSendData d = new WsSendData(C_.ACT_BIND_IMG_TO_MSG, data);
        send(d);
    }
    public static void sendMsg(StructMsg msg){
        WsSendData d = new WsSendData(C_.ACT_NEW_MSG, msg);
        send(d);
    }
    public static void sendExit(){
        send(new WsSendData(C_.ACT_EXIT));
        clearSocketData();
    }
    public static void sendGetOnlineStatus(String listId){
        WsSendData d = new WsSendData(C_.ACT_GET_ONLINE_STATUS);
        d.setIdList(listId);
        send(d);
    }
    public static void sendCheckNewMsg(){
        WsSendData d = new WsSendData(C_.ACT_CHECK_NEW_MSG);
        send(d);
    }
    public static void sendPingServer(){
        WsSendData d = new WsSendData(C_.ACT_PING_SERVER);
        send(d);
    }
    public static void sendPrintMsgProcess(long discusId, int consumerId){

        WsSendData d = new WsSendData(C_.ACT_PRINT_MSG_PROCESS);
        StructMsg msg = new StructMsg();
        msg.setDiscus_id(discusId);
        msg.setConsumer_id(consumerId);
        d.setData_group(msg);
        send(d);
    }
    public static void sendConfirmDeliverMsg(int consumerId, long discusId, int statusMsg){
        ArrayMap<String, Object> sendData = new ArrayMap<>();
        sendData.put("consumer_id",consumerId);
        sendData.put("discus_id", discusId);
        sendData.put("status_msg", statusMsg);
        WsSendData d = new WsSendData(C_.ACT_CONFIRM_DELIVER_MSG, sendData);
        Ws.send(d);
    }
    public static void sendAuthUserData() {
        if (user != null){
            int showStatus = MyStorage.i().getBoolen(C_.VAR_SWITCH_SHOW_STATUS) ? 1 : 0;
            WsSendData d = new WsSendData(C_.ACT_AUTH_USER);
            d.setUser_login(user.getLogin());
            d.setShowStatus(showStatus);
            Ws.send(d);
        }
    }
    public static void sendPong(){
        WsTimer.i().refreshLastPingTime();
        WsSendData d = new WsSendData(C_.ACT_PONG);
        send(d);
    }
    public static void sendEnableShowStatus(){
        WsSendData d = new WsSendData(C_.ACT_ENABLE_SHOW_STATUS);
        send(d);
    }
    public static void sendDisableShowStatus(){
        WsSendData d = new WsSendData(C_.ACT_DISABLE_SHOW_STATUS);
        send(d);
    }
    public static void send(WsSendData data){
        String jsonStr = new Gson().toJson(data);

        Log.i("MyWsSend", "-> "+jsonStr);
        if(!connectionState){
            cb.callback(C_.CODE_ON_FAILURE, null);
        }

        try{
            socket.send(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            cb.callback(C_.CODE_ON_FAILURE, null);
        }

    }
    private static void rcvBindImgToMsg(WsReceiveData data){

        cb.callback(C_.CODE_WS_BIND_IMG_TO_MSG, data);
    }
    private static void rcvNewAuthData(){

        cb.callback(C_.CODE_WS_NEW_AUTH_DATA, null);
    }
    private static void rcvConfirmViewed(WsReceiveData data){

        cb.callback(C_.CODE_CONFIRM_DELIVER, data);
    }
    private static void rcvAuthUser(WsReceiveData wsReceiveData){
        socketAuth = true;
        setUser(Usr.i().getUser());
        WsTimer.i().refreshLastPingTime();
        cb.callback(C_.CODE_WS_AUTH_USER, wsReceiveData.getNewToken());
    }
    private static void rcvNewToken(WsReceiveData wsReceiveData){
        cb.callback(C_.CODE_WS_NEW_TOKEN, wsReceiveData.getNewToken());
    }
    private static void rcvPing(){
        cb.callback(C_.CODE_WS_PING, null);
    }
    private static void rcvPongServer(){

        cb.callback(C_.CODE_WS_PONG_SERVER, null);
    }
    private static void rcvNewMsg(WsReceiveData data){
        StructMsg msg = new StructMsg();
        if (data.getMsg() == null){
            msg.setCreateDate(data.getCreateDate());
            msg.setContent(data.getContent());
            msg.setId(data.getId());
            msg.setSender_id(data.getSenderId());
            msg.setSender_login(data.getSenderLogin());
            msg.setConsumer_id(data.getConsumerId());
            msg.setAds_id(data.getAdsId());
            msg.setDiscus_id(data.getDiscusId());
            msg.setImg(data.getImg());
            msg.setImgIcon(data.getImgIcon());
            msg.setSenderAvatar(data.getSenderAvatar());

            msg.setReplyMsgId       (data.getReplyMsgId());
            msg.setReplySenderId    (data.getReplySenderId());
            msg.setReplySenderLogin (data.getReplySenderLogin());
            msg.setReplyContent     (data.getReplyContent());
            msg.setReplyImg         (data.getReplyImg());


        }else
            msg = data.getMsg();
        cb.callback(C_.CODE_NEW_MSG, msg);
    }
    private static void rcvOnlineList(WsReceiveData d){
        String idListStr = d.getIdList();
        cb.callback(C_.CODE_WS_ONLINE_LIST, idListStr);
    }
    private static void rcvWsExit(){
        WsTimer.i().initCheckConnectionTimer(false);
        socketAuth = false;
        if (badConnectionQt < 3){
            clearSocketData();
            cb.callback(C_.CODE_WS_SEND_ERROR, null);
            badConnectionQt++;
            Log.i("MyWs", "badConnectionQt -> "+badConnectionQt);
        }else {
            Log.i("MyWs", "CODE_WS_BAD_AUTH_DATA 1");
            badConnectionQt = 0;
            cb.callback(C_.CODE_WS_BAD_AUTH_DATA, null);
        }

    }
    private static void rcvPrintMsgProcess(WsReceiveData data){
        tmpData = new WsReceiveData();

        tmpData = data;
        if (data.getDiscusId() == null)return;
        cb.callback(C_.CODE_WS_PRINT_TXT, tmpData.getDiscusId());
    }

    public interface WsCallback {
        public void callback(int code, Object object);
    }
    public interface WsUserStatusCb{
        public void callback(ArrayList<Integer> idList);
    }
    public interface WsUserStatusCb1{
        public void callback(ArrayList<Integer> idList);
    }
    public interface MyCallBack {
        public void callback(int code);
        public RequestData getObject();
    }
}




