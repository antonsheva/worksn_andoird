package com.worksn.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.worksn.classes.ConvertMsgData;
import com.worksn.objects.SaveImgData;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.MsgManager;
import com.worksn.objects.C_;
import com.worksn.objects.StructMsg;

public class ActivityBroadcastReceiver extends BroadcastReceiver {
    public static String BROADCAST_FILTER     = "ws_to_activity_filter";

    private static ActivityBroadcastReceiver i;
    public static ActivityBroadcastReceiver i(){
        if (i == null) i = new ActivityBroadcastReceiver();
        return i;
    }
    CB cb;
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (AppMode.i().getPage()){
            case C_.APP_PAGE_MAIN               : processingPageMain(context, intent); break;
            case C_.APP_PAGE_MY_PROFILE         : processingPageMyProfile(context, intent); break;
            case C_.APP_PAGE_ANONYMOUS_USER_PAGE: processingPageAnonymousUserPage(context, intent); break;
            case C_.APP_PAGE_USER_PAGE          : processingPageUserPage(context, intent); break;
            case C_.APP_PAGE_REGISTRATION       : processingPageRegistration(context, intent); break;
            case C_.APP_PAGE_RECOVERY_PASSWORD  : processingPageRecoveryPassword(context, intent); break;
            case C_.APP_PAGE_SETTING            : processingPageSetting(context, intent); break;
            case C_.APP_PAGE_MAKE_PHOTO         : processingPageMakePhoto(context, intent); break;
            case C_.APP_PAGE_CHOOSE_IMG         : processingPageChooseImg(context, intent); break;
        }
    }
    public void init(Context context, CB callback){
        IntentFilter intentFilter = new IntentFilter(BROADCAST_FILTER);
        context.registerReceiver(this, intentFilter);
        cb = callback;
    }

    public void clear(Context context){
        try{
            context.unregisterReceiver(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void processingPageMain(Context context, Intent intent){
        String act = intent.getStringExtra("act");
        if (act == null) return;
        switch (act){
            case C_.ACT_ONLINE_LIST         : wsOnlineList(intent);                  break;
            case C_.ACT_NEW_MSG             : wsNewMsg(context, intent);             break;
            case C_.ACT_CONFIRM_DELIVER_MSG : wsConfirmViewed(context, intent);      break;
            case C_.ACT_NEW_AUTH_DATA       : wsNewAuthData();                       break;
            case C_.ACT_BAD_AUTH_DATA       : wsBadAuthData();                       break;
            case C_.ACT_PRINT_MSG_PROCESS   : wsPrintTxt(context, intent);           break;
            case C_.ACT_BIND_IMG_TO_MSG     : wsBindImageToMessage(context, intent); break;
//            case C_.ACT_EXIT                : wsExit();                     break;
        }
    }
    private void processingPageMyProfile(Context context, Intent intent){

    }
    private void processingPageAnonymousUserPage(Context context, Intent intent){

    }
    private void processingPageUserPage(Context context, Intent intent){
        String act = intent.getStringExtra(C_.STR_ACT);
        if (act == null) return;
        if (C_.ACT_ONLINE_LIST.equals(act)) {
            wsOnlineList(intent);
        }
    }
    private void processingPageRegistration(Context context, Intent intent){

    }
    private void processingPageRecoveryPassword(Context context, Intent intent){

    }
    private void processingPageSetting(Context context, Intent intent){

    }
    private void processingPageMakePhoto(Context context, Intent intent){

    }
    private void processingPageChooseImg(Context context, Intent intent){

    }




    private void wsBindImageToMessage(Context context, Intent intent){
        SaveImgData saveImgData = new SaveImgData();
        saveImgData.setMsgId(intent.getLongExtra(C_.STR_MSG_ID, 0));
        saveImgData.setImg(intent.getStringExtra(C_.STR_IMG));
        saveImgData.setImgIcon(intent.getStringExtra(C_.STR_IMG_ICON));
        MsgManager.i().wsRcvBindImageToMessage(context, saveImgData);
    }
    private void wsPrintTxt(Context context, Intent intent){
        long discusId = intent.getLongExtra(C_.STR_DISCUS_ID, 0);
        MsgManager.i().showPrintMsgProcess(context, discusId);
    }
    private void wsNewAuthData(){
        cb.cb(C_.CODE_WS_NEW_AUTH_DATA, null);
    }
    private void wsBadAuthData(){
        cb.cb(C_.CODE_WS_BAD_AUTH_DATA, null);
    }


    private void wsOnlineList(Intent intent){
        String idListStr = intent.getStringExtra("id_list");
        cb.cb(C_.CODE_WS_ONLINE_LIST, idListStr);
    }
    private void wsNewMsg(Context context,Intent intent){
        StructMsg msg = new ConvertMsgData().intentExtrasToMsg(intent);
        MsgManager.i().wsRcvNewMsg(context, msg, true);
    }
//    private void wsExit(){
//        Log.i("MyBroadcast", "wsExit");
//    }
    private void wsConfirmViewed(Context context,Intent intent){
        long discusId  = intent.getLongExtra(C_.STR_DISCUS_ID, 0);
        int statusMsg = intent.getIntExtra(C_.STR_STATUS_MSG, 0);
        if (discusId != 0)
            MsgManager.i().renderMsgViewedStatus(context, discusId, statusMsg);
    }

    public interface CB{
         void cb(int code, Object o);
    }
}
