package com.worksn.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.worksn.objects.PostSubData;

import com.worksn.static_class.Post;
import com.worksn.websocket.WsBroadcastReceiver;
import com.worksn.websocket.WsService;

public class TryMe implements Thread.UncaughtExceptionHandler{
    Thread.UncaughtExceptionHandler oldHandler;
    Context context;
    public TryMe(){

        oldHandler = Thread.getDefaultUncaughtExceptionHandler();
    }
    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        StackTraceElement[]arr  = throwable.getStackTrace();
        StringBuilder sendData = new StringBuilder();
        for (StackTraceElement str : arr){
            sendData.append(str.toString()).append("\r\n");
        }
        if(oldHandler != null){
            // если есть ранее установленный...
            String exceptionData = throwable.getMessage();
            exceptionData += "\r\n"+sendData;
            Log.d("MyException", exceptionData);
            PostSubData subData = new PostSubData();
            subData.setContent(exceptionData);
            Post.sendRequest(context,"send_exception", subData, (data, result, stringData)->{
                if (result == 1){
                    Log.d("MyException", "exception was send to server");
                }
            });
            oldHandler.uncaughtException(thread, throwable);
        }
        System.exit(0);
    }

}
