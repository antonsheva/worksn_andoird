package com.worksn.classes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.worksn.R;
import com.worksn.objects.Ads;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.singleton.MsgManager;
import com.worksn.singleton.UiClick;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Funcs;
import com.worksn.static_class.MyLog;
import com.worksn.view.RatingStars;
import com.worksn.view.MyView;
import com.worksn.view.Render;

public class AdsCard{
    @SuppressLint("ClickableViewAccessibility")


    FrameLayout frmAdsCard;
    ImageView adsCardProfileImg;
    LinearLayout adsCardProfileStars;
    ScrollView adsCardDescription;
    FrameLayout  adsCardLoadImg;
    ImageView btHideCard;
    ImageView btHideLoadImgs;
    TextView  adsCardLogin;
    ImageView adsCardProfileOnline;

    TextView cost;
    TextView time;
    TextView category;
    TextView descriptionText;

    FrameLayout  frmLoadImgs   ;
    LinearLayout loadImgsScroll;

    CB cb;
    Activity activity;

    ArrayList<String> loadImgs = new ArrayList<>();
    ArrayList<String> loadImgsIcon = new ArrayList<>();

    String userImgIcon;
    Float rating = 0f;
    static Integer userId;
    public AdsCard(Activity activity) {
        this.activity = activity;
        initViewElements();
    }
    public void init(Ads d, boolean descriptionClickable, boolean descriptionState, CB cb){
        this.cb = cb;
        if (d.getUserId() == null) d.setUserId(0);
        userId = d.getUserId();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (userId == 1){
                    initDiscusWithAdmin(d);
                }else{
                    initAdsData(d);
                    new RatingStars(activity, adsCardProfileStars, rating);
                }
            }
        });

        initLoadImgsIconClickListener(loadImgs);
        initBtHideCardClickListener();
        initDescriptionScrollListener();
        if(descriptionClickable) initDescriptionTouchListener();
        adsCardProfileOnline.setVisibility(View.GONE);
        G_.expandAdsCard = !descriptionState;
        showAdsDescription();
        if (Usr.i().getUser()==null)return;
        if(d.getUserId().equals(Usr.i().getUser().getId())){
            activity.runOnUiThread(() -> adsCardProfileOnline.setVisibility(View.VISIBLE));
        }else {
            Usr.i().requestUsersStatus(activity, false);
        }
    }
    public void setStatus(List<Integer> idList){
         try{
            boolean status = idList.contains(MsgManager.i().getMsgContext().getSpeaker().getId());
            activity.runOnUiThread(() -> {
                if (status){
                    adsCardProfileOnline.setVisibility(View.VISIBLE);
                } else {
                    adsCardProfileOnline.setVisibility(View.GONE);
                }
            });
        }catch (NullPointerException e){
            new MyLog().sendLogData(activity, "setStatus: status = null");
        }
    }
    private void showImgsIcon(boolean show){
        activity.runOnUiThread(() -> {
            if (show) {
                adsCardLoadImg.setVisibility(View.VISIBLE);
                try {
                    new MyImg(activity).addImgIconsToView(loadImgsIcon, adsCardLoadImg, 45, 45);
                } catch (Exception e){
                    adsCardLoadImg.setVisibility(View.GONE);
                };
            }else {
                adsCardLoadImg.removeAllViews();
                adsCardLoadImg.setVisibility(View.GONE);
            }
        });
    }
    private void initDiscusWithAdmin(Ads d){
        rating = 0.0f;
        //  --- avatar --------------------------------------------------------------------
        if(d.getUserImgIcon() != null){
            userImgIcon = C_.URL_BASE +d.getUserImgIcon();
            try{
                Funcs.loadImg(activity, adsCardProfileImg, userImgIcon, 10, null);
            }catch (Exception ignored){};
        }else{
            adsCardProfileImg.setImageResource(R.drawable.no_avatar);
        }

//  --- login  ---------------------------------------------------------------
        if(d.getUserLogin() != null) {
            adsCardLogin.setText(d.getUserLogin());
        } else {
            adsCardLogin.setText(activity.getString(R.string.login));
        }

        if(d.getDescription() != null){
            descriptionText.setText(d.getDescription());
        }else {
            descriptionText.setText(activity.getString(R.string.someUseful));
        }

        if(d.getCreateDate() != null) {
            time.setText(d.getCreateDate().substring(5, d.getCreateDate().length() - 3));
        } else {
            time.setText("--:--");
        }

        category.setText(activity.getString(R.string.discusWithAdmin));
    }
    private void initAdsData(Ads d) {

//  --- rating  ------------------------------------------------------------------
        rating = 0.0f;
        if(d.getUserRating()!=null)rating = (float)d.getUserRating()/100;

//  --- imgs ----------------------------------------------------------------
        if((d.getImgIcon() != null) && (d.getImg() != null) && (d.getImg().length()>4) && (d.getImgIcon().length()>4)) {
            String[] imgs     = d.getImg().split(",");
            String[] imgsIcon = d.getImgIcon().split(",");
            for (int i = 0; i< imgs.length; i++) {
                imgs[i] = C_.URL_BASE+imgs[i];
                imgsIcon[i] = C_.URL_BASE+imgsIcon[i];
            }
            Collections.addAll(loadImgs, imgs);
            Collections.addAll(loadImgsIcon, imgsIcon);
            showImgsIcon(true);
        }else {
            showImgsIcon(false);
        }

//  --- avatar --------------------------------------------------------------------
        if(d.getUserImgIcon() != null){
            userImgIcon = C_.URL_BASE +d.getUserImgIcon();
            try{
                Funcs.loadImgNecessarily(activity, adsCardProfileImg, userImgIcon, 10);
            }catch (Exception ignored){};
        }else{
            adsCardProfileImg.setImageResource(R.drawable.no_avatar);
        }

//  --- login  ---------------------------------------------------------------
        if(d.getUserLogin() != null) {
            adsCardLogin.setText(d.getUserLogin());
        } else {
            adsCardLogin.setText(activity.getString(R.string.login));
        }


//  --- description   ---------------------------------------------------------------
        if(d.getDescription() != null){
            descriptionText.setText(d.getDescription());
        }else {
            descriptionText.setText(activity.getString(R.string.someUseful));
        }

//  --- create date   ---------------------------------------------------------------
        if(d.getCreateDate() != null) {
            time.setText(d.getCreateDate());
        } else {
            time.setText("--:--");
        }

//--------------- category -------
        try{
            category.setText(G_.catList.get(d.getCategory()).getName());
        }catch (Exception e){
            Log.e("MyEx", e.fillInStackTrace().toString());
        }
//---------------- cost  ----------
        String cst;
        if(d.getCost() != null) {
            cst = d.getCost().toString();
            cost.setText(cst.concat("p."));
        } else {
            cost.setText(" ");
        }
    }
    private void initLoadImgsIconClickListener(ArrayList<String> imgs) {
        adsCardLoadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Render(activity).showBigImagesList();
                new MyImg(activity).showImgsArray(imgs, false);
            }
        });
    }
    private void initBtHideCardClickListener() {
        btHideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb.callback();
            }
        });
    }
    private void initDescriptionScrollListener() {
        adsCardDescription.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                G_.noClick = true;
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    private void initDescriptionTouchListener() {
        adsCardDescription.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable background;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        Log.i("MyTouch", "ACTION_DOWN");
                    }; break;
                    case MotionEvent.ACTION_UP:{
                        if(G_.noClick){
                            G_.noClick = false;
                            return false;
                        }
                        Log.i("MyTouch", "ACTION_UP");
                        showAdsDescription();
                    }; break;
                    case MotionEvent.ACTION_CANCEL:{
                        Log.i("MyTouch", "ACTION_CANCEL");
                    }; break;
                }
                return false;
            }
        });
    }
    private void showAdsDescription(){
        if(!G_.expandAdsCard){
            MyView.setHeightLl(activity, frmAdsCard, ViewGroup.LayoutParams.MATCH_PARENT);
            G_.expandAdsCard = true;
        }else {
            MyView.setHeightLl(activity, frmAdsCard, 90);
            G_.expandAdsCard = false;
        }
    }
    private void initViewElements(){
        btHideCard           = activity.findViewById(R.id.btHideDiscus);
        btHideLoadImgs       = activity.findViewById(R.id.btHideLoadImgs);
        frmAdsCard           = activity.findViewById(R.id.frmAdsCard);
        adsCardDescription   = activity.findViewById(R.id.adsCardDescription);
        adsCardProfileImg    = activity.findViewById(R.id.adsCardProfileAvatar);
        adsCardProfileStars  = activity.findViewById(R.id.adsCardProfileStars);
        adsCardLogin         = activity.findViewById(R.id.adsCardLogin);
        adsCardLoadImg       = activity.findViewById(R.id.adsCardLoadImg);
        adsCardProfileOnline = activity.findViewById(R.id.adsCardProfileOnline);

        cost                 = activity.findViewById(R.id.adsCardCost);
        category             = activity.findViewById(R.id.adsCardCategory);
        time                 = activity.findViewById(R.id.adsCardTime);
        descriptionText      = activity.findViewById(R.id.adsCardDescriptionText);
        frmLoadImgs          = activity.findViewById(R.id.frmLoadImgs);
        loadImgsScroll       = activity.findViewById(R.id.loadImgsScroll);

        adsCardProfileImg.setOnClickListener(v -> UiClick.i().showUserPage(activity, userId));
    }
    public interface CB{
         void callback();
    }
}
