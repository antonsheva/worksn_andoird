package com.worksn.static_class;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.worksn.R;
import com.worksn.interfaces.ComCallback;

import java.io.File;
import java.io.FileInputStream;


public class Funcs {
    public static void loadImg(Context context, ImageView imageView, String src, int radius, ComCallback comCallback){
        try{
            Glide
                    .with(context)
                    .load(src)
                    .transform(new RoundedCorners(radius))
                    .into(imageView);
            if(comCallback != null)comCallback.callback(null, 1);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }



//        Handler uiHandler = new Handler(Looper.getMainLooper());
//        uiHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                Glide
//                        .with(context)
//                        .load(src)
//                        .transform(new RoundedCorners(radius))
//                        .into(imageView);
//                if(comCallback != null)comCallback.callback(null, 1);
//            }
//        });
    }
    public static void loadImgNecessarily(Context context, ImageView imageView, String src, int radius){
        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                if ((src == null)||(src.length() < 10)){
                    imageView.setImageResource(R.drawable.no_avatar_r50);
                }else {
                    Glide
                            .with(context)
                            .load(src)
                            .error(R.drawable.no_avatar_r50)
                            .transform(new RoundedCorners(radius))
                            .into(imageView);
                }

            }
        });
    }
    public static void setImgSrc(ImageView imageView, int src){
        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource( src);
            }
        });
    }
    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getUriSrc(int id){
        return Uri.parse("android.resource://com.worksn/" +id).toString();
    }
    public static Uri getUri(int id){
        return Uri.parse("android.resource://com.worksn/" +id);
    }
    public static Bitmap getBitmap(String path) {
        try {
            Bitmap bitmap=null;
            File f= new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int dpToPx(Context context, float dp) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}
