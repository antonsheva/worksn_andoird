package com.worksn.singleton;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityTimers {
    Timer requestOnlineUsers;
    private static MainActivityTimers i;
    public  static MainActivityTimers i(){
        if (i == null) i = new MainActivityTimers();
        return i;
    }
    public void setOnlineStatusTimer(Context context, boolean stt){
        if (requestOnlineUsers != null){
            requestOnlineUsers.cancel();
            requestOnlineUsers = null;
        }
        if (stt){
            requestOnlineUsers = new Timer();
            requestOnlineUsers.schedule(new TimerTask() {
                @Override
                public void run() {
                    Usr.i().requestUsersStatus(context, true);
                }
            },100,30000);
        }
    }
}
