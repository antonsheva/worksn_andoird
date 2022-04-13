package com.worksn.classes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.worksn.R;
import com.worksn.objects.Ads;
import com.worksn.objects.C_;
import com.worksn.objects.MyScreen;
import com.worksn.objects.StructMsg;
import com.worksn.singleton.PUWindow;
import com.worksn.static_class.Funcs;

public class SubMenu {
    private Object targetObject = null;

    private int pos;
    private CB cb;

    public void initCb(CB cb){
        this.cb = cb;
    }
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private boolean isShow = false;
    private static SubMenu i;
    public static SubMenu i(){
        if (i == null) i = new SubMenu();
        return i;
    }
    @SuppressLint("ResourceType")
    public void show(Activity activity, Object targetObject, int targetObjectType, int left, int top){
        LinearLayout subMenu    = activity.findViewById(R.id.subMenu);
        LinearLayout btRmv      = activity.findViewById(R.id.btRmv);
        LinearLayout btCopy     = activity.findViewById(R.id.btCopy);
        LinearLayout btReply    = activity.findViewById(R.id.btReply);
        LinearLayout btRecovery = activity.findViewById(R.id.btRecovery);
        LinearLayout btHidden   = activity.findViewById(R.id.btHidden);
        LinearLayout btShow     = activity.findViewById(R.id.btShow);
        LinearLayout btEdit     = activity.findViewById(R.id.btEdit);

        btRmv     .setVisibility(View.GONE);
        btCopy    .setVisibility(View.GONE);
        btReply   .setVisibility(View.GONE);
        btRecovery.setVisibility(View.GONE);
        btHidden  .setVisibility(View.GONE);
        btShow    .setVisibility(View.GONE);
        btEdit    .setVisibility(View.GONE);

        this.targetObject = targetObject;
        int h = Funcs.dpToPx(activity, 50) ;
        int w = Funcs.dpToPx(activity,130);

        switch (targetObjectType){
            case C_.SUBMENU_OBJECT_TYPE_MSG :

                btCopy.setVisibility(View.VISIBLE);
                btRmv.setVisibility(View.VISIBLE);
                btReply.setVisibility(View.VISIBLE);
                top -= h*3.5;
                break;
            case C_.SUBMENU_OBJECT_TYPE_MSG_GROUP :
                btRmv.setVisibility(View.VISIBLE);
                top -= h*1.5;
                break;
            case C_.SUBMENU_OBJECT_TYPE_ADS :
                Ads targetAds = (Ads)targetObject;
                btEdit.setVisibility(View.VISIBLE);
                switch (targetAds.getVisibleMode()){
                    case C_.ADS_VISIBLE_HIDDEN_MANUAL :
                        btShow.setVisibility(View.VISIBLE);
                        top -= h*2.5;
                        break;
                    case C_.ADS_VISIBLE_HIDDEN_REMOVE :
                        btRecovery.setVisibility(View.VISIBLE);
                        top -= h*2.5;
                        break;
                    default:
                        btHidden.setVisibility(View.VISIBLE);
                        btRmv.setVisibility(View.VISIBLE);
                        top -= h*3.5;
                }
                break;
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); // высота и ширина
        params.setMargins(0, 0,0,0);
        subMenu.setLayoutParams(params);

        if(left> MyScreen.sizePxX /2)left -= w+10;
        else left += 20;

        if (top < Funcs.dpToPx(activity, 100) )top = Funcs.dpToPx(activity, 100);
        params.setMargins(left, top,0,0);
        subMenu.setLayoutParams(params);

        btRmv.setOnClickListener(view -> {
            hide(activity);
            int code = 0;
            switch (targetObjectType){
                case C_.SUBMENU_OBJECT_TYPE_ADS       : code = C_.SUBMENU_CODE_ADS_REMOVE;  break;
                case C_.SUBMENU_OBJECT_TYPE_MSG       : code = C_.SUBMENU_CODE_REMOVE_MSG;  break;
                case C_.SUBMENU_OBJECT_TYPE_MSG_GROUP : code = C_.SUBMENU_CODE_REMOVE_MSG_GROUP;  break;
            }
            cb.cb(code, targetObject);
        });
        btRecovery.setOnClickListener(v -> {
            hide(activity);
            int code = C_.SUBMENU_CODE_ADS_RECOVERY;
            cb.cb(code, targetObject);
        });
        btShow.setOnClickListener(v -> {
            hide(activity);
            int code = C_.SUBMENU_CODE_ADS_SHOW;
            cb.cb(code, targetObject);
        });
        btHidden.setOnClickListener(v -> {
            hide(activity);
            int code = C_.SUBMENU_CODE_ADS_HIDDEN;
            cb.cb(code, targetObject);
        });
        btEdit.setOnClickListener(v -> {
            hide(activity);
            int code = C_.SUBMENU_CODE_ADS_EDIT;
            cb.cb(code, targetObject);
        });
        btCopy.setOnClickListener(view -> {
            try{
                ClipboardManager clipboardManager;
                clipboardManager = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                StructMsg msg = (StructMsg)targetObject;
                String text = msg.getContent();
                ClipData clipData = ClipData.newPlainText("copyText", text);
                clipboardManager.setPrimaryClip(clipData);
                PUWindow.i().show(R.string.txtWasCopy);
            }catch (Exception e){
                e.printStackTrace();
            }
            hide(activity);
        });
        btReply.setOnClickListener(v -> {
            hide(activity);
            cb.cb(C_.SUBMENU_CODE_REPLY_TO_MSG, targetObject);
        });
        isShow = true;
    }

    public boolean hide(Activity activity){
        boolean result = isShow;

        LinearLayout subMenu = (LinearLayout)activity.findViewById(R.id.subMenu);

        activity.runOnUiThread(() -> {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1,1); // высота и ширина
            params.setMargins(0,-(Funcs.dpToPx(activity,200)),0,0);
            subMenu.setLayoutParams(params);
        });
        isShow = false;
        return result;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public interface CB{
        void cb(int code, Object object);
    }
}
