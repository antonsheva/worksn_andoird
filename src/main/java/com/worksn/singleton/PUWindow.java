package com.worksn.singleton;

import android.view.Gravity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import com.worksn.activity.MyApp;

public class PUWindow {
    private static PUWindow i;
    public  static PUWindow i(){
        if (i == null) i = new PUWindow();
        return i;
    }
    boolean isBusy = false;
    Timer tm = new Timer();
    public void show(String msg){
        if (!isBusy){
            try{
                isBusy = true;
                Toast toast = Toast.makeText(MyApp.context, msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }catch (RuntimeException ignored){}
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    isBusy = false;
                }
            }, 2000);
        }
    }
    public void show(int msgId){
        String msg = MyApp.context.getString(msgId);
        if (!isBusy){
            try{
                isBusy = true;
                Toast toast = Toast.makeText(MyApp.context, msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }catch (RuntimeException ignored){}
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    isBusy = false;
                }
            }, 2000);
        }

    }
}
