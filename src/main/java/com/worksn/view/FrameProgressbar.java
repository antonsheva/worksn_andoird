package com.worksn.view;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.worksn.R;
import com.worksn.classes.MyImg;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.NetworkService;

public class FrameProgressbar {
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

    public FrameProgressbar(Activity activity){
        this.activity = activity;
        initViewElements();
    }
    public void setPos(int val){
        renderView(SET_POS, val);
    }
    public void show(){
        renderView(HIDE, 1);
        renderView(SHOW, -1);
    }
    public void showFull(){
        renderView(HIDE, 1);
        renderView(SHOW_FULL, -1);
    }
    public void hide(){
        TmpImg.sendImgIsRun = false;
        renderView(HIDE, 1);
    }
    private void initViewElements(){
        progressBar       = (FrameLayout) activity.findViewById(R.id.progressBar);
        progressBarPos    = (LinearLayout) activity.findViewById(R.id.progressBarPos);
        progressBarBcgrnd = (LinearLayout) activity.findViewById(R.id.progressBarBcgrnd);
        progressBarTxt    = (TextView) activity.findViewById(R.id.progressBarTxt );
        progressBarCancel = (androidx.appcompat.widget.AppCompatButton) activity.findViewById(R.id.progressBarCancel);

        progressBarCancel.setOnClickListener(v -> {
            NetworkService.i().cancelRequest();
            if (activity.findViewById(R.id.regFormTmpImg) != null)
                new MyImg(activity).hideRegFormTmpImg();

            hide();
            TmpImg.clear();
        });
    }
    private void renderView(int act, int val){
        String str = (Integer)val +"%";
        LinearLayout.LayoutParams pos    = (LinearLayout.LayoutParams) progressBarPos .getLayoutParams();
        LinearLayout.LayoutParams bcgrnd = (LinearLayout.LayoutParams) progressBarBcgrnd .getLayoutParams();
        if (val >= 0){
            pos.weight = val;
            bcgrnd.weight = 100-val;
        }

        activity.runOnUiThread(() -> {
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
        });
    }
}
