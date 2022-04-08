package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import com.worksn.R;

public class PrintMsgProcess {

    Timer tm, tmCycle;
    int cnt = 0;
    TextView txt;
    public PrintMsgProcess(Context context){
        try{
            txt = ((Activity)context).findViewById(R.id.activeChainMsgPrintTxt);
        }catch (NullPointerException e){
            return;
        }
        txt.setText("Печатает");
        if (tm != null){
            tm.cancel();
            tm = null;
        }
        if (tmCycle != null){
            tmCycle.cancel();
            tmCycle = null;
        }
        tm = new Timer();
        tmCycle = new Timer();
        tmCycle.schedule(new TimerTask() {
            @Override
            public void run() {
                setText(context, cnt);
                cnt++;
            }
        }, 10,200);

        tm.schedule(new TimerTask() {
            @Override
            public void run() {
               tmCycle.cancel();
               tmCycle = null;
               tm.cancel();
               tm = null;
               ((Activity)context).runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       txt.setText("");
                   }
               });

            }
        }, 5000);
    }
    private void setText(Context context, int cnt){
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (cnt & 3){
                    case 0: txt.setText("Печатает");   break;
                    case 1: txt.setText("Печатает.");   break;
                    case 2: txt.setText("Печатает..");   break;
                    case 3: txt.setText("Печатает...");   break;
                }
            }
        });
    }
}
