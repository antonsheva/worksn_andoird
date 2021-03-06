package com.worksn.activity;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.worksn.R;
import com.worksn.classes.Kbrd;
import com.worksn.classes.MyFile;
import com.worksn.objects.PostSubData;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.PUWindow;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.C_;
import com.worksn.objects.MyContext;
import com.worksn.objects.TmpImg;
import com.worksn.objects.User;
import com.worksn.singleton.Usr;
import com.worksn.classes.MyNet;
import com.worksn.view.FrameProgressbar;


public class UpdateAnonymUserDataActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView    regFormImg;
    EditText     regFormLogin;
    EditText     regFormPassword;
    EditText     regFormRepeatPassword;
    EditText     regFormName;
    EditText     regFormSName;
    EditText     regFormEmail;
    EditText     regFormAbout;
    ImageView    regFormCamera;
    ImageView    regFormGallery;
    LinearLayout regFormImgSource;
    androidx.appcompat.widget.AppCompatButton btSend;

    MyFile myFile = null;
    boolean changeAvatarFlag = false;

    DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                saveDataChange();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                changeAvatarFlag       = false;
                TmpImg.imgSend     = null;
                TmpImg.imgIconSend = null;
                onBackPressed();
                break;
        }
    };

    @Override
    public void onBackPressed() {
        new Kbrd().hide(this);
        if (changeAvatarFlag){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.qSaveChange).setPositiveButton(R.string.yes, dialogClickListener)
                    .setNegativeButton(R.string.no, dialogClickListener).show();

            changeAvatarFlag = false;
        }else {
            new FrameProgressbar(this).hide();
            super.onBackPressed();
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registration);
        initViewElements();
        myFile = new MyFile();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyStorage.i().init(this);
        AppMode.i().setPage(C_.APP_PAGE_ANONYMOUS_USER_PAGE);
        new Kbrd().hide(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regFormImg      : showImgSourcePanel(); break;
            case R.id.btSend          : saveDataChange()    ; break;
            case R.id.regFormCamera   : makePhoto()         ; break;
            case R.id.regFormGallery  : chooseFile()        ; break;
        }
    }

    private void initViewElements(){
        regFormImg            = findViewById(R.id.regFormImg);
        regFormLogin          = findViewById(R.id.regFormLogin);
        regFormPassword       = findViewById(R.id.regFormPassword);
        regFormRepeatPassword = findViewById(R.id.regFormRepeatPassword);
        regFormName           = findViewById(R.id.regFormName);
        regFormSName          = findViewById(R.id.regFormSName);
        regFormEmail          = findViewById(R.id.regFormEmail);
        regFormAbout          = findViewById(R.id.regFormAbout);
        regFormCamera         = findViewById(R.id.regFormCamera);
        regFormGallery        = findViewById(R.id.regFormGallery);
        regFormImgSource      = findViewById(R.id.regFormImgSource);
        btSend                = findViewById(R.id.btSend);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                regFormPassword      .setVisibility(View.VISIBLE);
                regFormRepeatPassword.setVisibility(View.VISIBLE);
            }
        });
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

    private void showImgSourcePanel(){
        regFormImgSource.setVisibility(View.VISIBLE);
    }
    private void chooseFile(){
        myFile.chooseFile(this, 0);
    }
    private void makePhoto(){
        myFile.makePhoto(this, 0);
    }
    private void saveDataChange(){
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
        Context context = (Context)this;
        MyNet.sendRequest(this,C_.ACT_UPDATE_AUTO_AUTH_DATA, userData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                PUWindow.i().show(stringData);
                if (result == 1) {
                    Usr.i().loginUser(context, userData.getLogin(), userData.getPassword(), new Usr.CB() {
                        @Override
                        public void callback(int code, Object data) {
                            Usr.i().setUser((User)data);
                            PUWindow.i().show(stringData);
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        regFormImgSource.setVisibility(View.GONE);
        myFile.uploadNewAvatar(this, regFormImg, requestCode, resultCode, data, result ->{
            Log.i("MyFile", "end upload file");
        } );
        changeAvatarFlag = true;
    }

}
