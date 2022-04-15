package com.worksn.classes;

import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.worksn.activity.MyApp;
import com.worksn.objects.C_;
import com.worksn.objects.PostSubData;
import com.worksn.singleton.Usr;

public class MyLog {
    public void sendLogger(Context context, String strData){
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            log.append(strData).append("\r\n");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line).append("\r\n");
            }
            PostSubData subData = new PostSubData();
            subData.setContent(log.toString());
            MyNet.sendRequest(context, C_.ACT_SEND_LOG_SERVICE, subData, (data, result, stringData)->{
                if (result == 1){
                    Log.d("MyLog", "Log was send to server");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendLogData(Context context, String strData){
        PostSubData subData = new PostSubData();
        if (Usr.i().getUser() != null){
            subData.setUserId(Usr.i().getUser().getId());
            subData.setLogin(Usr.i().getUser().getLogin());
        }
        subData.setContent(strData);
        MyNet.sendRequest(context,C_.ACT_SEND_LOG_SERVICE, subData, (data, result, stringData)->{
            if (result == 1){
                Log.d("MyLog", "Log was send to server");
            }
        });
    }
    public void sendException(Exception e){
        e.printStackTrace();
        StackTraceElement[] stack = e.getStackTrace();
        StringBuilder sendString = new StringBuilder();
        for (StackTraceElement element : stack){
            sendString.append(element).append("r\n");
        }
        PostSubData subData = new PostSubData();
        subData.setContent(sendString.toString());
        MyNet.sendRequest(MyApp.context,C_.ACT_SEND_LOG_SERVICE, subData, (data, result, stringData)->{
            if (result == 1){
                Log.d("MyLog", "Log was send to server");
            }
        });
    }
}



