package com.worksn.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import com.worksn.R;
import com.worksn.classes.Kbrd;
import com.worksn.objects.C_;
import com.worksn.objects.PostSubData;
import com.worksn.singleton.PUWindow;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.MyContext;
import com.worksn.singleton.Usr;
import com.worksn.classes.MyNet;

public class ChangePassword extends AppCompatActivity {
    EditText oldPassword;
    EditText newPassword;
    EditText newPasswordRepeat;
    androidx.appcompat.widget.AppCompatButton chngPassBtSend;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_change_password);
        initViewElements();
    }
    @Override
    protected void onStart() {
        super.onStart();
        new Kbrd().hide(this);
    }
    private void initViewElements(){
        oldPassword       = (EditText)findViewById(R.id.oldPassword);
        newPassword       = (EditText)findViewById(R.id.newPassword);
        newPasswordRepeat = (EditText)findViewById(R.id.newPasswordRepeat);
        chngPassBtSend    = (androidx.appcompat.widget.AppCompatButton)findViewById(R.id.chngPassBtSend);

        chngPassBtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }
    private void sendData(){
        String oldPass       = oldPassword.getText().toString();
        String newPass       = newPassword.getText().toString();
        String newPassRepeat = newPasswordRepeat.getText().toString();
        if (oldPass.equals("")){
            PUWindow.i().show(R.string.enterOldPass);
            return;
        }
        if (newPass.equals("")){
            PUWindow.i().show(R.string.enterNewPass);
            return;
        }
        if (!newPass.equals(newPassRepeat)){
            PUWindow.i().show(R.string.passwordsAreNotEqual);
            return;
        }
        PostSubData data = new PostSubData();
        data.setPassword(oldPass);
        data.setNewPass(newPass);
        data.setLogin(Usr.i().getUser().getLogin());
        MyNet.sendRequest(this, C_.ACT_CHNG_PASSWORD, data, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                PUWindow.i().show(stringData);
                if(result == 1){
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                }
            }
        });
    }
}
