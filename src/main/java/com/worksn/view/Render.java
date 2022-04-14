package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.worksn.R;
import com.worksn.classes.Kbrd;
import com.worksn.classes.MyImg;
import com.worksn.classes.SubMenu;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.MyScreen;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.MsgManager;
import com.worksn.singleton.PUWindow;
import com.worksn.singleton.Usr;

public class Render {
    private LinearLayout frmAuthForm;
    private LinearLayout frmUserMenu;
    private LinearLayout frmMap;
    private LinearLayout frmAdsParam;
    private LinearLayout frmActive;
    private FrameLayout frmSearch;
    private FrameLayout frmAdsCard;
    private FrameLayout frmSendMsg;
    private FrameLayout frmLoadImgs;
    private EditText searchTxt;
    private androidx.appcompat.widget.AppCompatButton searchButton;
    private LinearLayout ekran;
    private LinearLayout frmAdsType;
    private TextView frmActiveTitle;
    private ImageView uMenuAvatar;

    private LinearLayout activeMsgGroup;
    private LinearLayout activeMsgChain;

    private LinearLayout activeCategory;
    private LinearLayout activeCategoryList;

    private LinearLayout activeLifetime;
    private LinearLayout activeLifetimeList;
    private LinearLayout activeUsers;
    private GridLayout   activeUsersGrid;
    private FrameLayout  activeAdd;
    private LinearLayout activeTargetAds;
    private LinearLayout activeEmpty;
    private ImageView rcVwMsgChainBtDown;

    Activity activity;

    public Render(Activity activity){
        this.activity = activity;
        new SubMenu().hide(activity);
        initViewElements();
        initOnClickListener();
    }
    public void showBtScrollDown(boolean state){
        if (state)rcVwMsgChainBtDown.setVisibility(View.VISIBLE);
        else      rcVwMsgChainBtDown.setVisibility(View.GONE);
    }
    public void screen(Integer screen_mode, Integer active_mode){
        new Kbrd().hide(activity);
        if ((screen_mode.equals(MyScreen.screenMode)) && (active_mode.equals(MyScreen.activeMode))) return;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TmpImg.imgSend = null;
                MyScreen.prevMode = MyScreen.screenMode;
                MyScreen.prevActive = MyScreen.activeMode;
                MyScreen.screenMode = screen_mode;
                MyScreen.activeMode = active_mode;

                clearScreen();

                switch (screen_mode) {
                    case C_.SCREEN_MODE_MAIN :
                        frmMap.setVisibility(View.VISIBLE);
                        frmAdsParam.setVisibility(View.VISIBLE);
                        frmActive.setVisibility(View.VISIBLE);
                        frmSearch.setVisibility(View.VISIBLE);
                        MyView.setHeightLl(activity, frmAdsCard, 90);

                        break;
                    case C_.SCREEN_MODE_MSG_CHAIN:
                        frmActive.setVisibility(View.VISIBLE);
                        frmSendMsg.setVisibility(View.VISIBLE);
                        frmAdsCard.setVisibility(View.VISIBLE);
                        break;
                    case C_.SCREEN_MODE_ADS_DETAIL:
                        frmActive.setVisibility(View.VISIBLE);
                        G_.expandAdsCard = true;
                        MyView.setHeightLl(activity, frmAdsCard, ViewGroup.LayoutParams.MATCH_PARENT);
                        frmAdsCard.setVisibility(View.VISIBLE);

                        break;
                    case C_.SCREEN_MODE_ADD_ADS :
                        frmMap.setVisibility(View.VISIBLE);
                        frmAdsParam.setVisibility(View.VISIBLE);
                        frmActive.setVisibility(View.VISIBLE);
                        frmSearch.setVisibility(View.VISIBLE);
                        searchTxt.setVisibility(View.GONE);
                        searchButton.setVisibility(View.GONE);

                        break;
                }
                if(active_mode != null)renderActive(active_mode);//, "Здесь нет объявлений с данными критериями :-("
            }
        });
    }
    public void header(){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(Usr.i().auth()){
                        frmUserMenu.setVisibility(View.VISIBLE);
                        frmAuthForm.setVisibility(View.GONE);
                        MyView.setBell(activity);
                    }else{
                        frmUserMenu.setVisibility(View.GONE);
                        frmAuthForm.setVisibility(View.VISIBLE);
                    }
                    new Kbrd().hide(activity);
                }
            });

    }
    public void hideBigImagesList(){
        ekran.setVisibility(View.GONE);
        frmLoadImgs.setVisibility(View.GONE);
        if (MyScreen.activeMode == C_.ACTIVE_SCREEN_MSG_CHAIN){
            frmSendMsg.setVisibility(View.VISIBLE);
        }
    }
    public void hideFrmSendMsg(){
        frmSendMsg.setVisibility(View.GONE);
    }
    public void showBigImagesList(){
        ekran.setVisibility(View.VISIBLE);
        frmLoadImgs.setVisibility(View.VISIBLE);
        frmSendMsg.setVisibility(View.GONE);
    }
    private void renderActive(Integer mode){
        hideActiveTabs();
        frmActiveTitle.setVisibility(View.VISIBLE);
        switch (mode){
            case C_.ACTIVE_SCREEN_MSG_GROUP  : activeMsgGroup .setVisibility(View.VISIBLE);break;
            case C_.ACTIVE_SCREEN_CATEGORY   : activeCategory .setVisibility(View.VISIBLE);
                frmActiveTitle.setText(R.string.categories);
                break;
            case C_.ACTIVE_SCREEN_USERS      : activeUsers    .setVisibility(View.VISIBLE);break;
            case C_.ACTIVE_SCREEN_ADD        : activeAdd      .setVisibility(View.VISIBLE);
                frmActiveTitle.setText(R.string.addAds);
                break;
            case C_.ACTIVE_SCREEN_LIFETIME   : activeLifetime .setVisibility(View.VISIBLE);
                frmActiveTitle.setText(R.string.lifetime);
                break;
            case C_.ACTIVE_SCREEN_MSG_CHAIN  : activeMsgChain .setVisibility(View.VISIBLE);
                frmActiveTitle.setVisibility(View.GONE);
                if (Usr.i().getUser()==null){
                    new PUWindow().show(R.string.needAuthForSenMsg);
                    frmSendMsg.setVisibility(View.GONE);
                }
                break;
            case C_.ACTIVE_SCREEN_TARGET_ADS : activeTargetAds.setVisibility(View.VISIBLE);break;
            default                          : activeEmpty    .setVisibility(View.VISIBLE);break;
        }
    }
    private void clearScreen(){
        frmMap.setVisibility(View.GONE);
        frmAdsParam.setVisibility(View.GONE);
        frmActive.setVisibility(View.GONE);
        frmSearch.setVisibility(View.GONE);
        frmSendMsg.setVisibility(View.GONE);
        frmAdsCard.setVisibility(View.GONE);
        ekran.setVisibility(View.GONE);
        frmLoadImgs.setVisibility(View.GONE);

        searchTxt.setVisibility(View.VISIBLE);
        searchButton.setVisibility(View.VISIBLE);
        frmAdsType.setVisibility(View.VISIBLE);

        new MyImg(activity).setWebView(null);
        showBtScrollDown(false);
    }
    private void hideActiveTabs(){
        activeMsgGroup.setVisibility(View.GONE);
        activeMsgChain.setVisibility(View.GONE);
        activeCategory.setVisibility(View.GONE);
        activeUsers.setVisibility(View.GONE);
        activeLifetime.setVisibility(View.GONE);
        activeTargetAds.setVisibility(View.GONE);
        activeEmpty.setVisibility(View.GONE);
    }
    private void initViewElements(){
        frmAuthForm         = (LinearLayout)activity.findViewById(R.id.frmAuthForm);
        frmUserMenu         = (LinearLayout)activity.findViewById(R.id.frmUserMenu);
        uMenuAvatar         = (ImageView   )activity.findViewById(R.id.uMenuAvatar);
        ekran               = (LinearLayout)activity.findViewById(R.id.ekran);
        frmAdsType          = (LinearLayout)activity.findViewById(R.id.frmAdsType);
        frmSendMsg          = (FrameLayout )activity.findViewById(R.id.frmSendMsg);
        frmSearch           = (FrameLayout )activity.findViewById(R.id.frmSearch);
        frmActive           = (LinearLayout)activity.findViewById(R.id.frmActive);
        frmAdsCard          = (FrameLayout )activity.findViewById(R.id.frmAdsCard);
        frmAdsParam         = (LinearLayout)activity.findViewById(R.id.frmAdsParam);
        frmMap              = (LinearLayout)activity.findViewById(R.id.frmMap);
        frmActive           = (LinearLayout)activity.findViewById(R.id.frmActive);
        activeMsgGroup      = (LinearLayout)activity.findViewById(R.id.activeMsgGroup);
        activeMsgChain      = (LinearLayout)activity.findViewById(R.id.activeMsgChain);
        activeCategory      = (LinearLayout)activity.findViewById(R.id.activeCategory);
        activeCategoryList  = (LinearLayout)activity.findViewById(R.id.activeCategoryList);
        activeLifetime      = (LinearLayout)activity.findViewById(R.id.activeLifetime);
        activeLifetimeList  = (LinearLayout)activity.findViewById(R.id.activeLifetimeList);
        activeUsers         = (LinearLayout)activity.findViewById(R.id.activeUsers);
        activeUsersGrid     = (GridLayout  )activity.findViewById(R.id.activeUsersGrid);
        activeAdd           = (FrameLayout )activity.findViewById(R.id.activeAdd);
        activeTargetAds     = (LinearLayout)activity.findViewById(R.id.activeTargetAds);
        activeEmpty         = (LinearLayout)activity.findViewById(R.id.activeEmpty);
        frmActiveTitle      = (TextView    )activity.findViewById(R.id.frmActiveTitle);
        searchTxt           = (EditText    )activity.findViewById(R.id.searchTxt);
        rcVwMsgChainBtDown  = (ImageView)   activity.findViewById(R.id.rcVwMsgChainBtDown);

        frmLoadImgs         = (FrameLayout) activity.findViewById(R.id.frmLoadImgs);

        try{
            searchButton        = (androidx.appcompat.widget.AppCompatButton)activity.findViewById(R.id.searchButton);
        }catch (NullPointerException ignored){}
    }
    private void initOnClickListener(){
        rcVwMsgChainBtDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgManager.i().scrollRcView(0);
                new Render(activity).showBtScrollDown(false);
            }
        });
    }
}
