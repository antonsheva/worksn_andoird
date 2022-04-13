package com.worksn.classes;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.exifinterface.media.ExifInterface;
import com.worksn.objects.C_;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.Usr;

public class TargetFileData {
    Context context;
    int actionType;
    String mCreateId = "";
    InputStream mStream = null;
    Uri mSelectedImageUri = null;
    Bitmap bitmapImg = null;
    String mPrepFileName;
    ContentResolver contentResolver;

    public Bitmap rotateBitmapOrientation(String photoFilePath)  {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFilePath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
        // Read EXIF Data
        ExifInterface exif;
        try{
            exif = new ExifInterface(photoFilePath);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        return Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
    }
    public TargetFileData(Context context, ActivityResult result, int actionType){
        this.context = context;
        this.actionType = actionType;
        contentResolver = context.getContentResolver();

        mPrepFileName = "f"+((Double)Math.random()).toString().substring(3,7);
        if (Usr.i().getUser() != null) {
            mPrepFileName = (Usr.i().getUser().getId()).toString();
        }
        mCreateId = mPrepFileName+"_"+(((Double)Math.random()).toString().substring(3,7));


        if (result.getResultCode() == RESULT_OK){
            if (TmpImg.photoPath != null){
                createBmpFromPhoto(result);
            }else {
                createBmpFromImg(result);
            }
            convertImg();
        }else {
            error("TargetFileData 1");
        }
    }
    private void createBmpFromImg(ActivityResult result){
        if (result.getData() != null && result.getData().getData() != null){
            mSelectedImageUri = result.getData().getData();
            try{
                mStream = contentResolver.openInputStream(mSelectedImageUri);
                bitmapImg  = BitmapFactory.decodeStream(mStream);
            }catch (Exception e){
                e.printStackTrace();
                error("createBmpFromImg 1");
            }
        }else {
            error("createBmpFromImg 2");
        }


    }
    private void createBmpFromPhoto(ActivityResult result){
        try{
            Bitmap bm = rotateBitmapOrientation(TmpImg.photoPath);
            if (bm != null){
                bitmapImg  = bm;
            }else {
                bitmapImg  = BitmapFactory.decodeFile(TmpImg.photoPath);
            }
            mSelectedImageUri =  Uri.parse(TmpImg.photoPath);

        }catch (Exception e){
            e.printStackTrace();
            error("createBmpFromPhoto 1");
        }
        TmpImg.photoPath = null;
    }


    private void convertImg(){
        String filePath = getDir();
        File outImg  = new File(filePath + File.separator + "img_"+mCreateId+".jpg");
        File outIcon = new File(filePath + File.separator + "icon_"+mCreateId+".jpg");

        try{
            float hPrep = bitmapImg.getHeight();
            float wPrep = bitmapImg.getWidth();
            float startX = 0, startY = 0;
            int size;
            if (hPrep > wPrep){
                startY = (hPrep-wPrep)/2;
                size = (int)wPrep;
            }else {
                startX = (wPrep-hPrep)/2;
                size = (int)hPrep;
            }
            Bitmap tmpBitmap = Bitmap.createBitmap(bitmapImg, (int)startX, (int)startY, size, size);
            Bitmap bitmapIcon = Bitmap.createScaledBitmap(tmpBitmap,300,300,false);

            Resources res = context.getResources();
            RoundedBitmapDrawable bmdf = RoundedBitmapDrawableFactory.create(res,bitmapIcon);
            bmdf.setCornerRadius(20);

            TmpImg.bitMapIconSend = bmdf;

            FileOutputStream fos     = new FileOutputStream(outImg);
            FileOutputStream fosIcon = new FileOutputStream(outIcon);
            bitmapImg.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            bitmapIcon.compress(Bitmap.CompressFormat.JPEG, 50, fosIcon);
            fos.flush();
            fos.close();
            fosIcon.flush();
            fosIcon.close();

            TmpImg.img  = outImg;
            TmpImg.icon = outIcon;
            TmpImg.createId = mCreateId;
            Intent intent = new Intent();

            intent.putExtra(C_.STR_RESP_TYPE       , actionType);
            ((Activity)context).setResult(RESULT_OK, intent);
            ((Activity)context).finish();
        }catch (Exception e){
            e.printStackTrace();
            error("convertImg");
        }
    }
    private String getDir(){
        boolean createDirResult = false;
        File dir = new File(context.getFilesDir(), "WorksnImages");
        if (!dir.exists()){
            try {
                createDirResult = dir.mkdir();
            }catch (SecurityException e){
                e.printStackTrace();
                error("getDir 126");
            }
        }else{
            createDirResult = true;
        }
        if (!createDirResult){
            error("getDir 132");
            return null;
        }else
            return dir.toString();
    }
    private void error(String str){
        TmpImg.img = null;
        Intent intent = new Intent();
        TmpImg.photoPath = null;
        intent.putExtra(C_.STR_RESP_TYPE, actionType);
        ((Activity)context).setResult(RESULT_CANCELED, intent);
        ((Activity)context).finish();
    }
}
