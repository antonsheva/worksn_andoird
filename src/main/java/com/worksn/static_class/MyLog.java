package com.worksn.static_class;

import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.worksn.activity.MyApp;
import com.worksn.objects.Ads;
import com.worksn.objects.JsonResponse;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostSubData;
import com.worksn.objects.StructMsg;
import com.worksn.objects.User;
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
            Post.sendRequest(context,"send_log_service", subData, (data, result, stringData)->{
                if (result == 1){
                    Log.d("MyLog", "Log was send to server");
                }
            });
//            TextView tv = (TextView) findViewById(R.id.textView1);
//            tv.setText(log.toString());
        } catch (IOException e) {
            Log.e("MyEx", " ---------------------- -----------------------------");
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
        Post.sendRequest(context,"send_log_service", subData, (data, result, stringData)->{
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
        Post.sendRequest(MyApp.context,"send_log_service", subData, (data, result, stringData)->{
            if (result == 1){
                Log.d("MyLog", "Log was send to server");
            }
        });
    }
}



