 package com.worksn.classes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.worksn.R;
import com.worksn.objects.MyScreen;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.MyAds;
import com.worksn.singleton.NetworkService;
import com.worksn.static_class.Funcs;
import com.worksn.view.FrameProgressbar;
import com.worksn.view.Render;

 public class MyImg implements View.OnClickListener {
    Activity activity;
    FrameLayout frmLoadImgs;
    LinearLayout loadImgsScroll;
    LinearLayout ekran;
    FrameLayout addAdsLoadImgsIcon;
    LinearLayout imageView;
    ImageView btHideLoadImgs;
    ImageView    bigImg;
    ImageView    bigImgRmvSn;
    ImageView    regFormImg;
    ImageView    regFormTmpImg;
    CB cb;

    static int sShiftLoadImg;

    public MyImg(Activity activity){
        this.activity = activity;
        initViewElements();
    }
    public void addImgIconToView(String img, FrameLayout imgWindow, int height, int width){
        LayoutInflater inflater = (activity.getLayoutInflater());
        View v = inflater.inflate(R.layout.layout_img_icon, (ViewGroup)imgWindow, false);
        ImageView tmpImg = v.findViewById(R.id.shellImg);
        FrameLayout.LayoutParams lpV = (FrameLayout.LayoutParams)v.getLayoutParams();
        lpV.height = Funcs.dpToPx(activity, height);
        lpV.width  = Funcs.dpToPx(activity, width);
        lpV.leftMargin = sShiftLoadImg *2;
        v.setLayoutParams(lpV);
        Funcs.loadImg(activity, tmpImg, img, 5, null);
        imgWindow.addView(v);
        sShiftLoadImg += 5;
        if(sShiftLoadImg > 20)sShiftLoadImg = 3;
    }
    public void addImgIconsToView(List<String> imgs, FrameLayout v, int height, int width){
        activity.runOnUiThread(() -> {
            v.removeAllViews();
            for (String img : imgs) {
                addImgIconToView(img, v, height, width);
            }
        });
    }
    public void addImgToGroup(String img, String imgIcon){
         addAdsLoadImgsIcon = activity.findViewById(R.id.addAdsLoadImgsIcon);
         MyAds.i().addImg(img, imgIcon);
         addImgIconToView(imgIcon, addAdsLoadImgsIcon, 40, 40);
     }
    public List<Integer> showImgsArray(List<String> imgs, boolean removePossible){
        List<Integer> idList = new ArrayList<>();
        for(String img : imgs) {
            int id = View.generateViewId();
            LayoutInflater inflater = (activity.getLayoutInflater());
            View v = inflater.inflate(R.layout.layout_big_img, (ViewGroup)loadImgsScroll, false);
            ImageView tmpImg   = v.findViewById(R.id.shellImg);
            ImageView removeSn = v.findViewById(R.id.removeSn);
            if (!removePossible) removeSn.setVisibility(View.GONE);
            LinearLayout.LayoutParams lpV = (LinearLayout.LayoutParams)v.getLayoutParams();

            lpV.width  =  MyScreen.sizePxX;
            lpV.height =  (int)(MyScreen.sizePxX * 1.3);
            v.setLayoutParams(lpV);
            v.setId(id);
            idList.add(id);
            removeSn.setOnClickListener(this);
            Funcs.loadImg(activity, tmpImg, img, 5, null);
            loadImgsScroll.addView(v);
            v.setOnClickListener(view -> setWebView(img));
        }
        return idList;
    }
    public void clearLoadImgsScroll(){
        loadImgsScroll.removeAllViews();
    }
    private void initViewElements(){
        btHideLoadImgs = activity.findViewById(R.id.btHideLoadImgs);
        frmLoadImgs    = activity.findViewById(R.id.frmLoadImgs);
        loadImgsScroll = activity.findViewById(R.id.loadImgsScroll);
        ekran          = activity.findViewById(R.id.ekran);
        imageView      = activity.findViewById(R.id.frmBigImg);
        bigImg         = activity.findViewById(R.id.bigImg);
        bigImgRmvSn    = activity.findViewById(R.id.bigImgRmvSn);
        regFormImg     = activity.findViewById(R.id.regFormImg);
        regFormTmpImg  = activity.findViewById(R.id.regFormTmpImg);


        if (btHideLoadImgs == null)return;
        btHideLoadImgs.setOnClickListener(v -> {
            sShiftLoadImg = 0;
            loadImgsScroll.removeAllViews();
            new Render(activity).hideBigImagesList();
        });
    }
    @Override
    public void onClick(View v) {
        FrameLayout img = (FrameLayout)v.getParent();
        int id = img.getId();
        Log.i("MyClick", ((Integer)img.getId()).toString());
        loadImgsScroll.removeView(img);
        cb.callback(1, id);
    }
    public void initCb(CB cb){
        this.cb = cb;
    }
    public void setWebView(String img){
         FrameLayout webView    = activity.findViewById(R.id.webView);
         WebView webViewImg     = activity.findViewById(R.id.webViewImg);
         ImageView webViewRmvSn = activity.findViewById(R.id.webViewImgRmvSn);
         if (img == null){
             webViewImg.loadUrl("about:blank");
             webView.setVisibility(View.GONE);
         }else {
             webView.setVisibility(View.VISIBLE);
             webViewImg.getSettings().setSupportZoom(true);
             webViewImg.getSettings().setBuiltInZoomControls(true);
             webViewImg.getSettings().setLoadWithOverviewMode(true);
             webViewImg.getSettings().setUseWideViewPort(true);
             webViewImg.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
             webViewImg.getSettings().setDisplayZoomControls(false);
             webViewImg.loadUrl(img);

             if(webViewRmvSn == null)return;
             webViewRmvSn.setOnClickListener(view -> {
                 webViewImg.loadUrl("about:blank");
                 webView.setVisibility(View.GONE);
             });
         }
    }
    public void setImageView(String img){
        try{
            if (img == null){
                bigImg.setImageResource(R.drawable.empty_100_100);
                imageView.setVisibility(View.GONE);
            }else {
                imageView.setVisibility(View.VISIBLE);
                bigImg.setImageURI(Uri.fromFile(new File(img)));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if (bigImgRmvSn == null)return;
        bigImgRmvSn.setOnClickListener(view -> {
            bigImg.setImageResource(0);
            imageView.setVisibility(View.GONE);
            if (TmpImg.sendImgIsRun){
                NetworkService.i(activity).cancelRequest();
                new FrameProgressbar(activity).hide();
                TmpImg.clear();
            }else {
                if (TmpImg.imgSend != null) new MyFile(activity).removeTmpFile(null);
            }
        });
    }
    public void setTmpAvatar(File img){
        activity.runOnUiThread(() -> {
            boolean createTmpFileError = true;
            if (img != null){
                if(img.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
                    regFormTmpImg.setImageBitmap(myBitmap);
                    createTmpFileError = false;
                }
            }
            if (createTmpFileError)regFormTmpImg.setImageResource(R.drawable.no_avatar);
            regFormTmpImg.setVisibility(View.VISIBLE);
            regFormImg.setVisibility(View.GONE);
        });
    }
    public void setNewAvatar(String imgSrc){
        activity.runOnUiThread(() -> {
            regFormImg.setVisibility(View.VISIBLE);
            Funcs.loadImg(activity, regFormImg, imgSrc, 5, null);
        });
    }
    public void hideRegFormTmpImg(){
        activity.runOnUiThread(() -> {
            regFormTmpImg.setImageResource(R.drawable.no_avatar);
            regFormImg.setVisibility(View.VISIBLE);
            regFormTmpImg.setVisibility(View.GONE);
        });
    }
    public interface CB {
         void callback(int code, Object object);
     }

}
