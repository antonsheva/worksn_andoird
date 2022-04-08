package com.worksn.activity;

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
import com.worksn.singleton.PUWindow;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostDataRegistration;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Post;

public class ChangePassword extends AppCompatActivity {
    EditText oldPassword;
    EditText newPassword;
    EditText newPasswordRepeat;
    androidx.appcompat.widget.AppCompatButton chngPassBtSend;
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
            PUWindow.i().show("Введите старый пароль");
            return;
        }
        if (newPass.equals("")){
            PUWindow.i().show("Введите новый пароль");
            return;
        }
        if (!newPass.equals(newPassRepeat)){
            PUWindow.i().show("Пароли не совпадают");
            return;
        }
        PostDataRegistration data = new PostDataRegistration();
        data.setPassword(oldPass);
        data.setNewPassword(newPass);
        data.setLogin(Usr.i().getUser().getLogin());
        Post.sendRequest(this,"chng_password", data, new NetCallback() {
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
