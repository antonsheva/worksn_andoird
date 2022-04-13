package com.worksn.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.List;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.worksn.BuildConfig;
import com.worksn.classes.TargetFileData;
import com.worksn.objects.C_;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.AppMode;

public class MakePhoto  extends AppCompatActivity {
    private final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 911;
    Context mContext = this;
    private static Integer actionType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppMode.i().setPage(C_.APP_PAGE_MAKE_PHOTO);
        TmpImg.imgSend = null;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent1 = getIntent();
        actionType = intent1.getIntExtra(C_.STR_ACTION_TYPE, 0);
        makePhoto();
    }
    ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    new TargetFileData(mContext, result, actionType);
                }
            });

    @SuppressLint("QueryPermissionsNeeded")
    private void makePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            try{
                File tmpFile = new File(getFilesDir(), File.separator + C_.FILE_NAME_TMP_PHOTO);
                Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", tmpFile);
                List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                TmpImg.photoPath = tmpFile.getAbsolutePath();
                mLauncher.launch(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else  Log.i("MyFile", "Error getPackageManager");
    }
}
