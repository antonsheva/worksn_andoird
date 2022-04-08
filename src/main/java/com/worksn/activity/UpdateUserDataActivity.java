package com.worksn.activity;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

import android.app.Activity;
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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.worksn.R;
import com.worksn.classes.Kbrd;
import com.worksn.classes.MyFile;
import com.worksn.classes.MyHref;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.PUWindow;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.C_;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostDataRegistration;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Funcs;
import com.worksn.static_class.Post;
import com.worksn.view.FrameProgressbar;


public class UpdateUserDataActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView regFormImg;
    EditText  regFormLogin;
    EditText  regFormName;
    EditText  regFormSName;
    EditText  regFormEmail;
    EditText  regFormAbout;
    TextView  regFormChangePass;
    LinearLayout regFormImgSource;
    ImageView     regFormCamera;
    ImageView     regFormGallery;
    androidx.appcompat.widget.AppCompatButton btSend;

    MyFile myFile = null;
    boolean changeAvatarFlag = false;

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    saveDataChange();
                    onBackPressed();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    changeAvatarFlag       = false;
                    TmpImg.imgSend     = null;
                    TmpImg.imgIconSend = null;
                    onBackPressed();
                    break;
            }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registration);
        initViewElements();
        new Kbrd().hide(this);
        myFile = new MyFile();
    }
    @Override
    protected void onStart() {
        super.onStart();
        MyStorage.i().init(this);
        AppMode.i().setPage(C_.APP_PAGE_MY_PROFILE);
        new Kbrd().hide(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regFormImg : showImgSourcePanel(); break;
            case R.id.btSend     : saveDataChange();   break;
            case R.id.regFormChangePass: changePassword();break;
            case R.id.regFormCamera: makePhoto();break;
            case R.id.regFormGallery: chooseFile();break;
        }
    }
    private void initViewElements(){

        regFormLogin      = (EditText) findViewById(R.id.regFormLogin);
        regFormName       = (EditText) findViewById(R.id.regFormName);
        regFormSName      = (EditText) findViewById(R.id.regFormSName);
        regFormEmail      = (EditText) findViewById(R.id.regFormEmail);
        regFormAbout      = (EditText) findViewById(R.id.regFormAbout);
        regFormChangePass = (TextView) findViewById(R.id.regFormChangePass);
        regFormImgSource  = (LinearLayout) findViewById(R.id.regFormImgSource);
        regFormImg        = (ImageView)findViewById(R.id.regFormImg);
        regFormCamera     = (ImageView)findViewById(R.id.regFormCamera);
        regFormGallery    = (ImageView)findViewById(R.id.regFormGallery);
        btSend = (androidx.appcompat.widget.AppCompatButton)findViewById(R.id.btSend);


        regFormChangePass.setVisibility(View.VISIBLE);

        regFormImg.setOnClickListener(this);
        btSend.setOnClickListener(this);
        regFormChangePass.setOnClickListener(this);
        regFormCamera.setOnClickListener(this);
        regFormGallery.setOnClickListener(this);

        initUserDataFields();
        initTxtListener();
    }

    private void initUserDataFields(){
        Activity activity = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                regFormLogin.setText(Usr.i().getUser().getLogin());
                regFormLogin.setEnabled(false);

                if (Usr.i().getUser().getName() != null)regFormName.setText(Usr.i().getUser().getName());
                if (Usr.i().getUser().getSName() != null)regFormSName.setText(Usr.i().getUser().getSName());
                if (Usr.i().getUser().getEmail() != null)regFormEmail.setText(Usr.i().getUser().getEmail());
                if (Usr.i().getUser().getAboutUser() != null)regFormAbout.setText(Usr.i().getUser().getAboutUser());
                if (Usr.i().getUser().getImgIcon() != null) Funcs.loadImgNecessarily( activity, regFormImg, C_.URL_BASE+Usr.i().getUser().getImgIcon(), 5);
            }
        });
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

                    Log.i("MyKey",  " a1 -> "+a);


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
    private void changePassword(){
        Intent intent = new Intent(UpdateUserDataActivity.this, ChangePassword.class);
        startActivity(intent);
    }
    private void sendData(PostDataRegistration userData){
        Post.sendRequest(this,C_.ACT_UPDATE_USER_DATA, userData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                PUWindow.i().show(stringData);
                if (result == 1) {
                    Usr.i().setUser(data.getUser());
                    finish();
                }
            }
        });
    }

    private void saveDataChange(){
        if (TmpImg.sendImgIsRun){
            new PUWindow().show(R.string.waitUploadImg);
            return;
        }

        String name  = regFormName.getText()  != null ? regFormName.getText() .toString() : null;
        String sName = regFormSName.getText() != null ? regFormSName.getText().toString() : null;
        String email = regFormEmail.getText() != null ? regFormEmail.getText().toString() : null;
        String about = regFormAbout.getText() != null ? regFormAbout.getText().toString() : null;

        if (Usr.i().getUser() == null) {
            PUWindow.i().show("Что-то пошло не так :-(");
            return;
        }

        PostDataRegistration userData = new PostDataRegistration();
        userData.setName(name);
        userData.setsName(sName);
        userData.setEmail(email);
        userData.setAboutUser(about);
        sendData(userData);
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
        regFormImgSource.setVisibility(View.GONE);
        myFile.uploadNewAvatar(this, regFormImg, requestCode, resultCode, data, result ->{
            Log.i("MyFile", "end upload file");
        } );
        changeAvatarFlag = true;
    }

}
