package com.worksn.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.worksn.R;
import com.worksn.classes.Kbrd;
import com.worksn.classes.TargetFileData;
import com.worksn.objects.C_;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.AppMode;
import com.worksn.view.FrameProgressbar;


public class FileChooser extends AppCompatActivity {
    private static Integer actionType;

    Context mContext = this;
    ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                new TargetFileData(mContext, result, actionType);
            }
        });

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppMode.i().setPage(C_.APP_PAGE_CHOOSE_IMG);
        TmpImg.img = null;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent1 = getIntent();
        actionType = intent1.getIntExtra(C_.STR_ACTION_TYPE, 0);
        showFileChooser();
    }
    private void showFileChooser() {
        Intent intent = new Intent(
           Intent.ACTION_PICK,
           android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent intentForLaunch = Intent.createChooser(intent, this.getString(R.string.chooseImg));
        mLauncher.launch(intentForLaunch);
    }
}
