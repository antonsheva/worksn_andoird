package com.worksn.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

import com.worksn.R;
import com.worksn.classes.Kbrd;
import com.worksn.objects.C_;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.PUWindow;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostDataRegistration;
import com.worksn.static_class.Post;

public class RecoveryPasswordActivity extends AppCompatActivity {
    EditText recoveryMail;
    androidx.appcompat.widget.AppCompatButton recoveryButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_recovery_password);
        initViewElements();
    }
    @Override
    protected void onStart() {
        super.onStart();
        AppMode.i().setPage(C_.APP_PAGE_RECOVERY_PASSWORD);
        new Kbrd().hide(this);
    }
    private void initViewElements(){
        recoveryMail   = (EditText)findViewById(R.id.recoveryMail);
        recoveryButton = (androidx.appcompat.widget.AppCompatButton)findViewById(R.id.recoveryButton);
        recoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }
    private void sendData(){
        String email = recoveryMail.getText().toString();
        if (email.equals("")){
            PUWindow.i().show("Введите почту");
            return;
        }
        PostDataRegistration data = new PostDataRegistration();
        data.setEmail(email);
        Context context = (Context)this;
        Post.sendRequest(this,"recovery_password", data, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                PUWindow.i().show(stringData);
                if(result == 1){
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1500);
                }
            }
        });
    }
}
