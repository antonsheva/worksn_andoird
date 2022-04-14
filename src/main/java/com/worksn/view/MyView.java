package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.worksn.R;
import com.worksn.classes.MyImg;
import com.worksn.singleton.MyStorage;

public class MyView {
    public static void setHeightLl(Activity activity, View ll, int val){
        final int height = val;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int ht = height;
                if(height>0)ht = dpToPx(activity, height);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ht);
                ll.setLayoutParams(params);
            }
        });
    }
    public static void setSizeFrameLayout(Activity activity, View fl, int width, int height){
        if(width>0)width = dpToPx(activity, width);
        if(height>0)height = dpToPx(activity, height);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        fl.setLayoutParams(params);
    }
    public static void setBell(Activity activity) {
        MyImg myImg = new MyImg(activity);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView uMenuBell;
                boolean newMsgSign = MyStorage.i().getBoolen("new_msg_sign");
                uMenuBell = (ImageView) activity.findViewById(R.id.uMenuBell);
                if (newMsgSign) {
                    myImg.setImgSrc(uMenuBell, R.drawable.bell_act);
                    MyStorage.i().putData("new_msg_sign", true);
                }
                else {
                    myImg.setImgSrc(uMenuBell, R.drawable.no_bell);
                }
            }
        });
    }
    public static void setNotifySign(Activity activity, boolean sign){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView img = (ImageView) activity.findViewById(R.id.btSettingImg);
                if (sign)
                    img.setImageResource(R.drawable.setting_notify);
                else
                    img.setImageResource(R.drawable.setting);
            }
        });
    }
    public static int dpToPx(Activity activity, float dp) {
        return (int)(dp * activity.getResources().getDisplayMetrics().density);
    }
}
