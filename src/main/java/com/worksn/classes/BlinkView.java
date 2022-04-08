package com.worksn.classes;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


public class BlinkView {
    private static boolean busy;
    private static BlinkView i;
    public static BlinkView i(){
        if (i == null)i = new BlinkView();
        return i;
    }

    Timer timer;
    public void blink(Activity context, View v){
        if (busy)return;
        busy = true;
        Drawable dr = v.getBackground();
        int color = ((ColorDrawable) dr).getColor();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        timer = new Timer();

        timer.schedule(new TimerTask() {
            int cnt = 8;
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if((cnt % 2) == 0){
                            v.setBackgroundColor(Color.RED);
                        }else {
                            v.setBackgroundColor(Color.GREEN);
                        }
                    }
                });
                cnt--;
                if (cnt == 0){
                    timer.cancel();
                    busy = false;
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            v.setBackgroundColor(color);
                        }
                    });

                }
            }
        }, 1, 50);
    }
}
