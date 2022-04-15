package com.worksn.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.worksn.R;
import com.worksn.classes.BroadCastMsg;
import com.worksn.classes.ConvertMsgData;
import com.worksn.classes.CreateInfoContent;
import com.worksn.objects.C_;
import com.worksn.objects.HtmlSS;
import com.worksn.objects.StructMsg;
import com.worksn.objects.SysNotify;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.PUWindow;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.Usr;
import com.worksn.classes.MyNet;
import com.worksn.view.FrameNotify;
import com.worksn.websocket.Ws;
import com.worksn.websocket.WsBroadcastReceiver;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

public class SettingActivity extends AppCompatActivity {

    TextView settingNotifyTitle;
    EditText adminMsgEditor;
    LinearLayout frmContent;
    LinearLayout msgToAdminHidden;
    LinearLayout frmMsgToAdmin;
    LinearLayout settingNotify;
    LinearLayout settingNotifyContent;
    ImageView    signMsgToAdmin;
    SwitchCompat switchConfirmDeliverMsg;
    SwitchCompat switchConfirmViewedMsg;
    SwitchCompat switchSendPrintText;
    SwitchCompat switchShowStatus;
    androidx.appcompat.widget.AppCompatButton btSendMsgToAdmin;
    Activity activity = this;
    private static boolean sTxtListenerFlag;
    private static boolean sSwitchOpenNotify = false;
    private static boolean sMsgToAdminState = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_setting);
        MyStorage.i().init(this);
        initViewElements();
        new CreateInfoContent(this);
        getNotify(C_.ACT_GET_NEW_NOTIFY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppMode.i().setPage(C_.APP_PAGE_SETTING);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void getNotify(String act){
        MyNet.sendRequest(this,act, null, (data, result, stringData) ->{
            if (result != -1){
                ArrayList<SysNotify>notifies = data.getNotifies();
                runOnUiThread(() -> {
                    if (notifies.size()>0){
                        settingNotify.setVisibility(View.VISIBLE);
                        for (SysNotify notify : notifies){
                            notify.setContent(new HtmlSS().strDecode(notify.getContent()));
                            addNotify(notify);
                        }
                    }else {
                        if (act.equals(C_.ACT_GET_ALL_NOTIFY))
                            settingNotifyTitle.setText(R.string.noNotifies);
                    }
                });

            }
        });
    }
    private void addNotify(SysNotify content){
        LayoutInflater inflater = (this.getLayoutInflater());
        View v = inflater.inflate(R.layout.shell_layout_wc, (ViewGroup) settingNotifyContent, false);
        new FrameNotify(this, v, content);
        settingNotifyContent.addView(v);
    }

    private void initViewElements(){
        adminMsgEditor       = findViewById(R.id.adminMsgEditor);
        msgToAdminHidden     = findViewById(R.id.msgToAdminHidden);
        frmMsgToAdmin        = findViewById(R.id.frmMsgToAdmin);
        settingNotify        = findViewById(R.id.settingNotify);
        settingNotifyContent = findViewById(R.id.settingNotifyContent);
        settingNotifyTitle   = findViewById(R.id.settingNotifyTitle);

        signMsgToAdmin   = findViewById(R.id.signMsgToAdmin);
        btSendMsgToAdmin = findViewById(R.id.btSendMsgToAdmin);

        switchShowStatus        = findViewById(R.id.switchShowStatus);
        switchConfirmDeliverMsg = findViewById(R.id.switchConfirmDeliverMsg);
        switchConfirmViewedMsg  = findViewById(R.id.switchConfirmViewedMsg);
        switchSendPrintText     = findViewById(R.id.switchSendPrintText);

        switchShowStatus.setChecked(MyStorage.i().getBoolen(C_.STR_SWITCH_SHOW_STATUS));
        switchConfirmDeliverMsg.setChecked(MyStorage.i().getBoolen(C_.STR_SWITCH_CONFIRM_DELIVER));
        switchConfirmViewedMsg.setChecked(MyStorage.i().getBoolen(C_.STR_SWITCH_CONFIRM_VIEWED));
        switchSendPrintText.setChecked(MyStorage.i().getBoolen(C_.STR_SWITCH_SEND_PRINT_TEXT));

        switchShowStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyStorage.i().putData(C_.STR_SWITCH_SHOW_STATUS, b);
                MyStorage.i().putData(C_.STR_SHOW_STATUS_CHANGE, true);
                if (b) Ws.sendEnableShowStatus();
                else{
                    MyStorage.i().putData(C_.STR_SWITCH_CONFIRM_DELIVER, false);
                    MyStorage.i().putData(C_.STR_CONFIRM_DELIVER_CHANGE, true);
                    switchConfirmDeliverMsg.setChecked(false);

                    MyStorage.i().putData(C_.STR_SWITCH_CONFIRM_VIEWED, false);
                    MyStorage.i().putData(C_.STR_CONFIRM_VIEWED_CHANGE, true);
                    switchConfirmViewedMsg.setChecked(false);

                    MyStorage.i().putData(C_.STR_SWITCH_SEND_PRINT_TEXT, false);
                    MyStorage.i().putData(C_.STR_SEND_PRINT_TEXT_CHANGE, true);
                    switchSendPrintText.setChecked(false);

                    Ws.sendDisableShowStatus();
                }
            }
        });
        switchConfirmDeliverMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyStorage.i().putData(C_.STR_SWITCH_CONFIRM_DELIVER, b);
                MyStorage.i().putData(C_.STR_CONFIRM_DELIVER_CHANGE, true);
            }
        });
        switchConfirmViewedMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyStorage.i().putData(C_.STR_SWITCH_CONFIRM_VIEWED, b);
                MyStorage.i().putData(C_.STR_CONFIRM_VIEWED_CHANGE, true);
            }
        });
        switchSendPrintText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyStorage.i().putData(C_.STR_SWITCH_SEND_PRINT_TEXT, b);
                MyStorage.i().putData(C_.STR_SEND_PRINT_TEXT_CHANGE, true);
            }
        });

        frmMsgToAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAdminMsgFrm();
            }
        });
        btSendMsgToAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsgToAdmin();
            }
        });
        initTxtListener();

        settingNotifyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingNotifyContent.removeAllViews();
                if(!sSwitchOpenNotify){
                    getNotify(C_.ACT_GET_ALL_NOTIFY);

                }
                sSwitchOpenNotify = !sSwitchOpenNotify;
            }
        });
    }

    private void initTxtListener(){

        adminMsgEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String a, a1, tmp;
                if (adminMsgEditor.length()>1){
                    tmp = adminMsgEditor.getText().toString();
                    a = tmp.substring(tmp.length()-2);

                    if (sTxtListenerFlag){
                        int pos = adminMsgEditor.length();
                        adminMsgEditor.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_MULTI_LINE);
                        adminMsgEditor.setSelection(pos);
                        sTxtListenerFlag = false;
                    }

                    if (a.equals(". ") || a.equals("? ") || a.equals("! ")){
                        int pos = adminMsgEditor.length();
                        adminMsgEditor.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_CAP_CHARACTERS | TYPE_TEXT_FLAG_MULTI_LINE);
                        adminMsgEditor.setSelection(pos);
                        sTxtListenerFlag = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void clickAdminMsgFrm(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (sMsgToAdminState){
                    signMsgToAdmin.setImageResource(R.drawable.submenu);
                    msgToAdminHidden.setVisibility(View.GONE);
                }else {
                    signMsgToAdmin.setImageResource(R.drawable.submenu_open);
                    msgToAdminHidden.setVisibility(View.VISIBLE);
                }
                sMsgToAdminState = !sMsgToAdminState;
            }
        });

    }
    private void sendMsgToAdmin(){
        String msgContent = adminMsgEditor.getText().toString();
        if (msgContent.length()<1){
            PUWindow.i().show(R.string.enterMsgTxt);
        }else {
            StructMsg msg = new StructMsg();
            msg.setSender_id(Usr.i().getUser().getId());
            msg.setConsumerId(1);
            msg.setAds_id(1L);
            msg.setContent(msgContent);
            MyNet.sendRequest(this,C_.ACT_ADD_MSG, msg, (data, result, stringData)->{
                if (result == 0)
                    PUWindow.i().show(stringData);
                if (result == 1){
                    HashMap<String, Object> map = new ConvertMsgData().sendMsgToMap(msg);
                    new BroadCastMsg(this, map, WsBroadcastReceiver.BROADCAST_FILTER);
                    adminMsgEditor.setText("");
                    PUWindow.i().show(R.string.msgWasSend_discusInMsgs);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            clickAdminMsgFrm();
                        }
                    }, 1000);

                }
                if (result == -1)
                    PUWindow.i().show(stringData);
            });
        }
    }
}
