package com.worksn.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.worksn.R;
import com.worksn.classes.MyImg;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.NetworkService;
import com.worksn.singleton.PUWindow;

import java.net.PortUnreachableException;
import java.util.Timer;
import java.util.TimerTask;

public class FrameProgressbar {
    private static boolean isShown = false;
    private static int cnt;

    Activity     activity;
    FrameLayout  progressBar;
    LinearLayout progressBarPos;
    LinearLayout progressBarBcgrnd;
    TextView     progressBarTxt;
    androidx.appcompat.widget.AppCompatButton progressBarCancel;

    private final int SET_POS   = 1;
    private final int SHOW      = 2;
    private final int SHOW_FULL = 3;
    private final int HIDE      = 4;

    final LinearLayout.LayoutParams[] pos = new LinearLayout.LayoutParams[1];
    final LinearLayout.LayoutParams[] bcgrnd = new LinearLayout.LayoutParams[1];

    public FrameProgressbar(Activity activity){
        this.activity = activity;
        initViewElements();
    }
    public void setPos(int val){
        renderView(SET_POS, val);
    }
    public void show(){
        cnt = 0;
        renderView(HIDE, 1);
        renderView(SHOW, -1);
    }
    public void showFull(){
        cnt = 0;
        renderView(HIDE, 1);
        renderView(SHOW_FULL, -1);
    }
    public void hide(){
        cnt = 0;
        isShown = false;
        TmpImg.sendImgIsRun = false;
        renderView(HIDE, 1);
    }
    private void initViewElements(){
        progressBar       = (FrameLayout) activity.findViewById(R.id.progressBar);
        progressBarPos    = (LinearLayout) activity.findViewById(R.id.progressBarPos);
        progressBarBcgrnd = (LinearLayout) activity.findViewById(R.id.progressBarBcgrnd);
        progressBarTxt    = (TextView) activity.findViewById(R.id.progressBarTxt );
        progressBarCancel = (androidx.appcompat.widget.AppCompatButton) activity.findViewById(R.id.progressBarCancel);

        progressBarCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkService.i(activity).cancelRequest();
                if (activity.findViewById(R.id.regFormTmpImg) != null)
                    new MyImg(activity).hideRegFormTmpImg();

                hide();
                TmpImg.clear();
            }
        });
    }
    private void renderView(int act, int val){
        String str = ((Integer)val).toString()+"%";
        LinearLayout.LayoutParams pos    = (LinearLayout.LayoutParams) progressBarPos .getLayoutParams();
        LinearLayout.LayoutParams bcgrnd = (LinearLayout.LayoutParams) progressBarBcgrnd .getLayoutParams();
        if (val >= 0){
            pos.weight = val;
            bcgrnd.weight = 100-val;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                switch (act){
                    case SET_POS:
                        if (val > 5 )progressBarPos.setVisibility(View.VISIBLE);
                        else         progressBarPos.setVisibility(View.GONE);
                        if (val < 95)progressBarBcgrnd.setVisibility(View.VISIBLE);
                        else         progressBarBcgrnd.setVisibility(View.GONE);
                        progressBarTxt.setText(str);
                        progressBarPos.setLayoutParams(pos);
                        progressBarBcgrnd.setLayoutParams(bcgrnd);
                        break;
                    case SHOW:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SHOW_FULL:
                        progressBar.setVisibility(View.VISIBLE);
                        progressBarCancel.setVisibility(View.VISIBLE);
                        break;
                    case HIDE:
                        progressBar.setVisibility(View.GONE);
                        progressBarCancel.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }
    private interface CB{
        void cb();
    }
}
