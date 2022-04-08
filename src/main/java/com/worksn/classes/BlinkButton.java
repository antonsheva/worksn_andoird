package com.worksn.classes;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.worksn.R;

import java.util.Timer;
import java.util.TimerTask;

import androidx.core.content.ContextCompat;


public class BlinkButton {
    Timer timer;
    public BlinkButton(Activity context, androidx.appcompat.widget.AppCompatButton button){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        timer = new Timer();

        timer.schedule(new TimerTask() {
            int cnt = 6;
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    Drawable stl;
                    @Override
                    public void run() {
                        if((cnt % 2) == 0){
                            stl = ContextCompat.getDrawable(context, R.drawable.blink_button_red);
                        }else {
                            stl = ContextCompat.getDrawable(context, R.drawable.blink_button_green);
                        }
                        button.setBackground(stl);
                    }
                });
                cnt--;
                if (cnt == 0){
                    timer.cancel();
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Drawable stl;
                            stl = ContextCompat.getDrawable(context, R.drawable.no_active_btn);
                            button.setBackground(stl);
                        }
                    });

                }
            }
        }, 1, 50);
    }
}
