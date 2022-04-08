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
import com.worksn.interfaces.ComCallback;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
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

    public MyImg(Activity activity){
        this.activity = activity;
        initViewElements();
    }
    public void addImgIconToView(String img, FrameLayout imgWindow, int height, int width){
        Log.i("MyImg", img);
        LayoutInflater inflater = (activity.getLayoutInflater());
        View v = inflater.inflate(R.layout.layout_img_icon, (ViewGroup)imgWindow, false);
        ImageView tmpImg = v.findViewById(R.id.shellImg);
        FrameLayout.LayoutParams lpV = (FrameLayout.LayoutParams)v.getLayoutParams();
        lpV.height = Funcs.dpToPx(activity, height);
        lpV.width  = Funcs.dpToPx(activity, width);
        lpV.leftMargin = G_.shiftLoadImg *2;
        v.setLayoutParams(lpV);
        Funcs.loadImg(activity, tmpImg, img, 5, null);
        imgWindow.addView(v);
        G_.shiftLoadImg += 5;
        if(G_.shiftLoadImg > 20)G_.shiftLoadImg = 3;
    }
    public void addImgIconsToView(List<String> imgs, FrameLayout v, int height, int width){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                v.removeAllViews();
                for (String img : imgs) {
                    addImgIconToView(img, v, height, width);
                }
            }
        });
    }
    public void addImgToGroup(String img, String imgIcon){
         addAdsLoadImgsIcon = (FrameLayout)activity.findViewById(R.id.addAdsLoadImgsIcon);
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
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setWebView(img);
                }
            });
        }
        return idList;
    }
    public void clearLoadImgsScroll(){
        loadImgsScroll.removeAllViews();
    }
    private void initViewElements(){
        btHideLoadImgs = (ImageView)   activity.findViewById(R.id.btHideLoadImgs);
        frmLoadImgs    = (FrameLayout) activity.findViewById(R.id.frmLoadImgs);
        loadImgsScroll = (LinearLayout)activity.findViewById(R.id.loadImgsScroll);
        ekran          = (LinearLayout)activity.findViewById(R.id.ekran);

        imageView     = (LinearLayout) activity.findViewById(R.id.frmBigImg);
        bigImg        = (ImageView)    activity.findViewById(R.id.bigImg);
        bigImgRmvSn   = (ImageView)    activity.findViewById(R.id.bigImgRmvSn);
        regFormImg    = (ImageView)    activity.findViewById(R.id.regFormImg);
        regFormTmpImg = (ImageView)    activity.findViewById(R.id.regFormTmpImg);


        if (btHideLoadImgs == null)return;
        btHideLoadImgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G_.shiftLoadImg = 0;
                loadImgsScroll.removeAllViews();
                new Render(activity).hideBigImagesList();
            }
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
         FrameLayout webView = (FrameLayout) activity.findViewById(R.id.webView);
         WebView webViewImg  = (WebView) activity.findViewById(R.id.webViewImg);
         ImageView webViewRmvSn = (ImageView) activity.findViewById(R.id.webViewImgRmvSn);
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
             webViewRmvSn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     webViewImg.loadUrl("about:blank");
                     webView.setVisibility(View.GONE);
                 }
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
        bigImgRmvSn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyPost", "removeTmpFile 2");
                bigImg.setImageResource(0);
                imageView.setVisibility(View.GONE);
                if (TmpImg.sendImgIsRun){
                    Log.i("MyPost", "removeTmpFile 3");
                    NetworkService.i(activity).cancelRequest();
                    new FrameProgressbar(activity).hide();
                    TmpImg.clear();
                }else {
                    Log.i("MyPost", "removeTmpFile 4");
                    if (TmpImg.imgSend != null) new MyFile(activity).removeTmpFile(null);
                }
            }
        });
    }
    public void setTmpAvatar(File img){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                boolean createTmpFileError = true;
                if (img != null){
                    if(img.exists()){
                        Log.i("MyImg", "tmpImg -> "+img.getAbsolutePath());
                        Bitmap myBitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
                        regFormTmpImg.setImageBitmap(myBitmap);
                        createTmpFileError = false;
                    }
                }
                if (createTmpFileError)regFormTmpImg.setImageResource(R.drawable.no_avatar);
                regFormTmpImg.setVisibility(View.VISIBLE);
                regFormImg.setVisibility(View.GONE);
            }
        });
    }
    public void setNewAvatar(String imgSrc){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("MyImg", "newImg -> "+imgSrc);
                regFormImg.setVisibility(View.VISIBLE);
//                regFormTmpImg.setVisibility(View.GONE);
                Funcs.loadImg(activity, regFormImg, imgSrc, 5, null);
            }
        });
    }
    public void hideRegFormTmpImg(){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                regFormTmpImg.setImageResource(R.drawable.no_avatar);
                regFormImg.setVisibility(View.VISIBLE);
                regFormTmpImg.setVisibility(View.GONE);
            }
        });
    }
    public interface CB {
         void callback(int code, Object object);
     }

}
