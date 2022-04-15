package com.worksn.view;

import android.app.Activity;
import android.content.Context;
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
        txt.setText(R.string.prints);
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
               ((Activity)context).runOnUiThread(() -> txt.setText(""));

            }
        }, 5000);
    }
    private void setText(Context context, int cnt){
        String txt1 = context.getString(R.string.prints);
        String txt2 = context.getString(R.string.prints)+".";
        String txt3 = context.getString(R.string.prints)+"..";
        String txt4 = context.getString(R.string.prints)+"...";
        ((Activity)context).runOnUiThread(() -> {
            switch (cnt & 3){
                case 0: txt.setText(txt1);   break;
                case 1: txt.setText(txt2);   break;
                case 2: txt.setText(txt3);   break;
                case 3: txt.setText(txt4);   break;
            }
        });
    }
}
