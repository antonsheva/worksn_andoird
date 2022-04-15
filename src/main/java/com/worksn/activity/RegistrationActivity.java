package com.worksn.activity;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.worksn.R;
import com.worksn.classes.CreateInfoContent;
import com.worksn.classes.Kbrd;
import com.worksn.classes.MyFile;
import com.worksn.objects.PostSubData;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.PUWindow;
import com.worksn.objects.C_;
import com.worksn.objects.TmpImg;
import com.worksn.objects.User;
import com.worksn.singleton.Usr;
import com.worksn.classes.MyNet;
import com.worksn.view.FrameProgressbar;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView regFormImg;
    EditText  regFormLogin;
    EditText  regFormPassword;
    EditText  regFormRepeatPassword;
    EditText  regFormName;
    EditText  regFormSName;
    EditText  regFormEmail;
    EditText  regFormAbout;

    FrameLayout  regFormTopPanel;
    LinearLayout regFormImgSource;
    ImageView    regFormCamera;
    ImageView    regFormGallery;
    androidx.appcompat.widget.AppCompatButton btSend;

    MyFile myFile = null;


    @Override
    public void onBackPressed() {
        new Kbrd().hide(this);
        TmpImg.imgSend     = null;
        TmpImg.imgIconSend = null;
        new FrameProgressbar(this).hide();
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registration);
        initViewElements();
        myFile = new MyFile();
        new CreateInfoContent(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        AppMode.i().setPage(C_.APP_PAGE_REGISTRATION);
        new Kbrd().hide(this);

    }
    private void initViewElements(){
        regFormLogin          = findViewById(R.id.regFormLogin);
        regFormPassword       = findViewById(R.id.regFormPassword);
        regFormRepeatPassword = findViewById(R.id.regFormRepeatPassword);
        regFormName           = findViewById(R.id.regFormName);
        regFormSName          = findViewById(R.id.regFormSName);
        regFormEmail          = findViewById(R.id.regFormEmail);
        regFormAbout          = findViewById(R.id.regFormAbout);

        regFormTopPanel       = findViewById(R.id.regFormTopPanel);
        regFormImgSource      = findViewById(R.id.regFormImgSource);
        regFormImg            = findViewById(R.id.regFormImg);
        regFormCamera         = findViewById(R.id.regFormCamera);
        regFormGallery        = findViewById(R.id.regFormGallery);

        btSend                = (androidx.appcompat.widget.AppCompatButton)findViewById(R.id.btSend);

        runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 regFormPassword      .setVisibility(View.VISIBLE);
                 regFormRepeatPassword.setVisibility(View.VISIBLE);
             }
         });
        regFormTopPanel.setOnClickListener(this);
        regFormImg.setOnClickListener(this);
        btSend.setOnClickListener(this);
        regFormCamera.setOnClickListener(this);
        regFormGallery.setOnClickListener(this);
        initTxtListener();
    }

    private void initTxtListener(){
        final boolean[] flg = {false};
        regFormAbout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String a, tmp;
                if (regFormAbout.length()>1){
                    tmp = regFormAbout.getText().toString();
                    a = tmp.substring(tmp.length()-2);
                    if (flg[0]){
                        int pos = regFormAbout.length();
                        regFormAbout.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_MULTI_LINE);
                        regFormAbout.setSelection(pos);
                        flg[0] = false;
                    }

                    if (a.equals(". ") || a.equals("? ") || a.equals("! ")){
                        int pos = regFormAbout.length();
                        regFormAbout.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_CAP_CHARACTERS | TYPE_TEXT_FLAG_MULTI_LINE);
                        regFormAbout.setSelection(pos);
                        flg[0] = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regFormTopPanel       : hideImgSourcePanel();      break;
            case R.id.regFormImg            : showImgSourcePanel();      break;
            case R.id.btSend                : saveData();                break;
            case R.id.regFormCamera         : makePhoto();               break;
            case R.id.regFormGallery        : chooseFile();              break;
        }
    }

    private void hideImgSourcePanel(){
        regFormImgSource.setVisibility(View.GONE);
    }
    private void showImgSourcePanel(){
        regFormImgSource.setVisibility(View.VISIBLE);
    }
    private void chooseFile(){
        myFile.chooseFile(this, 0);
    }
    private void makePhoto(){
        myFile.makePhoto(this, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideImgSourcePanel();
        myFile.uploadNewAvatar(this, regFormImg, requestCode, resultCode, data, result -> {

        } );
    }
    private void saveData(){
        String login          = regFormLogin.getText().toString();
        String password       = regFormPassword.getText().toString();
        String repeatPassword = regFormRepeatPassword.getText().toString();
        String name           = regFormName.getText().toString();
        String sName          = regFormSName.getText().toString();
        String email          = regFormEmail.getText().toString();
        String about          = regFormAbout.getText().toString();

        if (TmpImg.sendImgIsRun){
            new PUWindow().show(R.string.waitUploadImg);
            return;
        }


        if (login.equals("")){
            PUWindow.i().show(R.string.enterLogin);
            return;
        }
        if (password.equals("")) {
            PUWindow.i().show(R.string.enterPassword);
            return;
        }
        if (repeatPassword.equals("")) {
            PUWindow.i().show(R.string.repeatPassword);
            return;
        }
        if (!password.equals(repeatPassword)){
            PUWindow.i().show(R.string.passwordsAreNotEqual);
            return;
        }

        PostSubData userData = new PostSubData();
        userData.setLogin(login);
        userData.setPassword(password);
        userData.setName(name);
        userData.setS_name(sName);
        userData.setEmail(email);
        userData.setAboutUser(about);
        sendData(userData);
    }
    private void sendData(PostSubData userData){
        Context context = this;
        MyNet.sendRequest(this,C_.ACT_REG_NEW_USER, userData, (data, result, stringData) -> {
            PUWindow.i().show(stringData);
            if (result == 1) {
                Usr.i().loginUser(context, userData.getLogin(), userData.getPassword(), (code, data1) -> {
                    Usr.i().setUser((User) data1);
                    finish();
                });
            }
        });
    }
}