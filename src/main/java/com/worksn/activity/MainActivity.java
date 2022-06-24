package com.worksn.activity;

import static android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.worksn.R;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.worksn.classes.BroadCastMsg;
import com.worksn.classes.ButtonsHighLight;
import com.worksn.classes.Kbrd;
import com.worksn.classes.MyFile;
import com.worksn.classes.MyImg;
import com.worksn.classes.MyLocation;
import com.worksn.classes.NotifyChannel;
import com.worksn.classes.WsServiceControl;
import com.worksn.objects.AssocData;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.SaveImgData;
import com.worksn.objects.StructTxtData;
import com.worksn.objects.MyStorageConst;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.MainActivityTimers;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.PUWindow;
import com.worksn.classes.PrefStorageCookie;
import com.worksn.classes.SubMenu;
import com.worksn.interfaces.AdapterListener;
import com.worksn.interfaces.ComCallback;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.Ads;

import com.worksn.classes.MyLog;
import com.worksn.classes.MyNet;
import com.worksn.view.FrameProgressbar;
import com.worksn.view.FrameReplyToMsg;
import com.worksn.view.Render;
import com.worksn.websocket.WsBroadcastReceiver;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.MyAds;
import com.worksn.objects.MyContext;
import com.worksn.objects.MyScreen;
import com.worksn.objects.PostSubData;
import com.worksn.objects.StructMsg;
import com.worksn.objects.User;
import com.worksn.singleton.MyMap;
import com.worksn.classes.AdsCard;
import com.worksn.singleton.UiClick;
import com.worksn.singleton.Usr;
import com.worksn.classes.GetPermission;
import com.worksn.classes.ConfirmDeliverMsg;
import com.worksn.singleton.MsgManager;
import com.worksn.view.RcVwMsgGroup;
import com.worksn.view.RcVwTargetAds;
import com.worksn.view.SelectButton;
import com.worksn.view.FrameUserProfile;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;

import static com.worksn.objects.G_.context;
import static com.worksn.objects.G_.stopActivityType;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ////////////////////////////////////////////////////////////////////////////////////////////////////
//------------   VIEW ELEMENTS  --------------------------------------------------------------------

    //--- ekran ---------------------------
    private LinearLayout ekran;
    private LinearLayout ekranTop;
    private LinearLayout frmBigImg;
    private ImageView bigImg;
    private WebView   webView;
    private LinearLayout upLevelBcgrnd;
    private ImageView bigImgRmvSn;

    //--- user menu ---------------------------
    private LinearLayout frmUserMenu;
    private LinearLayout uMenuProfile;
    private ImageView uMenuAvatar;
    private ImageView uMenuBell;

    private LinearLayout btAllMsg;
    private LinearLayout btNewMsg;
    private LinearLayout btChooseUsers;
    private LinearLayout btBanUsers;
    private LinearLayout btExit;
    private FrameLayout  btSetting;

    //--- login form --------------------------
    private LinearLayout frmAuthForm;
    private EditText authFormLogin;
    private EditText authFormPassword;
    private TextView authFormEnter;
    private TextView authFormAnonym;
    private TextView authFormRegistration;
    private TextView authFormRecovery;

    //--- ads type ----------------------------
    private LinearLayout frmAdsType;
    private androidx.appcompat.widget.AppCompatButton btAdsTypeWorker;
    private androidx.appcompat.widget.AppCompatButton btAdsTypeEmployer;
    private ImageView btMyLocation;
    //--- ads parameter -----------------------
    private LinearLayout frmAdsParam;
    private androidx.appcompat.widget.AppCompatButton btAdsParamCategory;
    private androidx.appcompat.widget.AppCompatButton btAdsParamUser;
    private androidx.appcompat.widget.AppCompatButton btAdsParamAdd;

    private LinearLayout adsParamCategoryField;
    private LinearLayout adsParamUserField;
    private LinearLayout adsParamAddField;

    //--- ads card ----------------------------
    private FrameLayout frmAdsCard;

    //--- active frame ------------------------
    private LinearLayout frmActive;
    private LinearLayout activeMsgGroup;
    private LinearLayout activeMsgChain;

    private LinearLayout activeCategory;
    private LinearLayout activeCategoryList;

    private LinearLayout activeLifetime;
    private LinearLayout activeLifetimeList;
    private LinearLayout activeUsers;
    private GridLayout activeUsersGrid;
    private FrameLayout activeAdd;
    private LinearLayout activeTargetAds;
    private LinearLayout activeEmpty;
    private TextView frmActiveTitle;

    //--- add ads -------------------------------
    private EditText addAdsCost;
    private EditText addAdsDescription;
    private androidx.appcompat.widget.AppCompatButton btAddAdsTime;
    private ImageView btAddAdsGallery;
    private ImageView btAddAdsPhoto;
    private FrameLayout addAdsLoadImgsIcon;
    private TextView addAdsNoAuth;
    private TextView addAdsCancel;
    private LinearLayout addAdsAuth;

    private Spinner hourListStart;
    private Spinner hourListStop ;
    private Spinner minListStart ;
    private Spinner minListStop  ;

    LinearLayout addAdsActivePeriod;
    //--- search ------------------------------
    private FrameLayout frmSearch;
    private EditText searchTxt;
    private androidx.appcompat.widget.AppCompatButton searchButton;

    //--- send message -------------------------
    private FrameLayout  frmSendMsg;
    private EditText     sendMsgTxt;
    private ImageView    sendMsgGallery;
    private LinearLayout sendMsgButton;
    private LinearLayout sendCamera;


    //------ msg elements -----------------------------------
    AdapterListener adapterListener;
    RcVwMsgGroup rcVwMsgGroup;
    RcVwTargetAds rcVwTargetAds;


    //--- map ---------------------------------
    private LinearLayout frmMap;

    //---- common vars -----------------------------------------------------------------------------
    Activity activity = (Activity) this;
    MyFile mMyFile = null;
    Render mVw = null;

    public static boolean sActive = false;
    public static boolean sLockUi = false;
////////////////////////////////////////////////////////////////////////////////////////////////////

    //------------  Click funcs ------------------------------------------------------------------------
    private void bigImgRemoveClick() {
        new MyImg(this).setWebView(null);
        Log.i("MyPost", "removeTmpFile 1");
        if (TmpImg.imgSend != null) new MyFile(this).removeTmpFile(null);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (!G_.clickListenerEnable) {
            return;
        }
        switch (v.getId()) {
            case R.id.webViewImgRmvSn:bigImgRemoveClick();break;
            case R.id.ekran: {return;}
            case R.id.ekranTop: {return;}
            case R.id.authFormEnter:btLoginUser();break;
            case R.id.authFormAnonym:btAnonymousLogin();break;
            case R.id.authFormRegistration: showRegistrationForm(); break;
            case R.id.authFormRecovery:showRecoveryForm(); break;
            case R.id.uMenuProfile: showUpdateUserForm();break;
            case R.id.btChooseUsers:btShowChooseUsers();break;
            case R.id.btBanUsers:btShowBanUsers();break;
            case R.id.btAllMsg:getMsgGroup(C_.MSG_TYPE_ALL);break;
            case R.id.btNewMsg:getMsgGroup(C_.MSG_TYPE_NEW);break;
            case R.id.btExit:btExitUser();break;
            case R.id.btAdsTypeWorker:btShowWorkerAds();break;
            case R.id.btAdsTypeEmployer:btShowEmployerAds();break;
            case R.id.btMyLocation:btSetMyLocation();break;
            case R.id.btAdsParamCategory:selectCategoryAds();break;
            case R.id.btAdsParamUser:btShowTargetUsers();break;
            case R.id.btAdsParamAdd:btAddAdsClick();break;
            case R.id.addAdsCancel:btCancelAddAds();break;
            case R.id.btAddAdsTime:selectAdsLifetime();break;
            case R.id.btAddAdsGallery:chooseFile(C_.IMG_FOR_ADS);break;
            case R.id.btAddAdsPhoto:makePhoto(C_.IMG_FOR_ADS);break;
            case R.id.addAdsLoadImgsIcon:showLoadImgs();break;
            case R.id.searchButton:searchAds();break;
            case R.id.sendMsgButton:btSendMsg();break;
            case R.id.sendMsgGallery:chooseFile(C_.IMG_FOR_MSG);break;
            case R.id.sendCamera:makePhoto(C_.IMG_FOR_MSG);break;
            case R.id.btSetting:UiClick.i().showSettingPage(this);break;
        }
        rmvSubMenu();
    }
//--------------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        new Kbrd().hide(this);
        TmpImg.clear();
        if (MyScreen.prevMode == -1){
            new FrameProgressbar(this).hide();
            super.onBackPressed();
        }
        else restorePrevView();
    }

    //-----------  Life cycle --------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyFile = new MyFile();

        sLockUi = true;
        Log.i("MyAppState", "-------------onCreate-------");
        G_.isQuickResponse = false;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        MyMap.i().initMapKit(this);
        setContentView(R.layout.activity_main);
        initViewElements();

        mVw     = new Render(this);

        MyScreen.init(this);
        MyStorage.i().init(this);
        initPreferenceStorage();
        initPowerOptimization();
        initKbrdOpenListener();
        initSubMenuListener();
        initSettingData();
        GetPermission.storage(this);
        GetPermission.geolocation(this);
        initMap();
        initAdapterListener();
        getEnvironmentData();
        initHuaweiPowerManager();
        new NotifyChannel(getApplicationContext());
        MyMap.i().setCameraPosition(MyLocation.i().getImHere());
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (G_.isQuickResponse){
            G_.isQuickResponse = false;
            Log.i("MyAppState", "------------------- QuickResponse ---------------------");
            finish();
        }
        new MyNotify ().removeNotify(this);
        AppMode.i().setPage(C_.APP_PAGE_MAIN);
        sLockUi = true;
        MyStorage.i().putData(MyStorageConst.MAIN_ACTIVITY_IS_ACTIVE, true);
        Log.i("MyAppState", "------------------- onStart ---------------------");
        MyMap.i().mapView.onStart();
        MapKitFactory.getInstance().onStart();
        new Kbrd().hide(this);
        G_.stopActivityType = 0;
        new BroadCastMsg(this, C_.ACT_OPEN_ACTIVITY, WsBroadcastReceiver.BROADCAST_FILTER);
        if (Usr.i().auth())initAuthorizedUserMode();
        else Usr.i().initUser(this, (code, data) -> {
                if (code == 1)
                    initAuthorizedUserMode();
                else{
                    mVw.header();
                    new WsServiceControl(this).stop();
                }
            });
        MainActivityTimers.i().setOnlineStatusTimer(getApplicationContext(), true);
        initBroadcastReceiverListener();
        checkAppMode();
        initLocationManager();
        sLockUi = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyStorage.i().putData(MyStorageConst.MAIN_ACTIVITY_IS_ACTIVE, false);
        Log.i("MyAppState", "-------------onStop-------");
        MyMap.i().mapView.onStop();
        MapKitFactory.getInstance().onStop();
        MainActivityTimers.i().setOnlineStatusTimer(getApplicationContext(), false);
        if (stopActivityType == 0) {
            clearTempAppData();
        }
        new BroadCastMsg(this, C_.ACT_CLOSE_ACTIVITY, WsBroadcastReceiver.BROADCAST_FILTER);
        ActivityBroadcastReceiver.i().clear(this);
        MyLocation.i().removeLocationUpdates();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MyAppState", "-------------onPause-------");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MyAppState", "-------------onDestroy-------");
        MyLocation.i().removeLocationUpdates();
    }

//-------- common functions ------------------------------------------------------------------------
    private void checkAppMode(){
        switch (AppMode.i().getMode()){
            case C_.APP_MODE_SHOW_BW_LIST      : AppMode.i().setMode(C_.APP_MODE_SHOW_BW_LIST); break;
            case C_.APP_MODE_DISCUS_WITH_USER  : getDiscusWithUser(Usr.i().targetUserId);       break;
        }
    }
    private void checkDiscusId() {
        long mDiscusId = 0;
        mDiscusId = MyStorage.i().getLong(C_.STR_NOTIFY_DISCUS_ID);
        if (mDiscusId == 0)mDiscusId = G_.notifyDiscusId;
        if (mDiscusId != 0)
            getMsgChain(mDiscusId);

        MyStorage.i().putData(C_.STR_NOTIFY_DISCUS_ID, 0L);
        G_.notifyDiscusId = 0;
        Log.i("MyStart", "discusId -> " + mDiscusId);
    }
    private void clearTempAppData(){
        if ((TmpImg.imgSend != null)||(TmpImg.img != null))
            new MyFile(activity).removeTmpFile(null);
        TmpImg.img = null;
        TmpImg.imgSend = null;
        TmpImg.imgIconSend = null;
        MyAds.i().mapPoint = null;
    }
    private void checkReturnToAddAdsPanel(int result){
        int mMode = AppMode.i().getMode(1);
        if(mMode == C_.APP_MODE_ADD_ADS){
            showAddAdsPanel();
            AppMode.i().setMode(mMode);
            if (addAdsDescription.length()==0){
                if (MyAds.i().cllct.getCatNum() != null){
                    if(MyAds.i().cllct.getCatNum() > 0)
                        addAdsDescription.setText(MyAds.i().cllct.getCatName());
                }
            }
            return;
        }
        btAdsParamAdd.setText(R.string.add);
        if (MyScreen.activeMode == C_.ACTIVE_SCREEN_USERS)return;
        if(result > 0){
            frmActiveTitle.setText(R.string.ads);
            mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_TARGET_ADS);
        }else{
            frmActiveTitle.setText(R.string.emptyAdsList);
            mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_EMPTY);
        }
    }

//------------- init funcs -------------------------------------------------------------------------
    private void initHuaweiPowerManager(){
        String tag = "com.my_app:LOCK";

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M && Build.MANUFACTURER.equals("Huawei"))
            tag = "PowerManagerService";

        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock =
                ((PowerManager) getSystemService(Context.POWER_SERVICE))
                        .newWakeLock(1, tag); wakeLock.acquire();
    }
    private void initAuthorizedUserMode() {
        Usr.i().checkNewNotify(this);
        new WsServiceControl(this).start();
        checkDiscusId();
        mVw.header();
        checkNewMsg();
    }
    @SuppressLint({"NewApi", "BatteryLife"})
    private void initPowerOptimization() {
        if (MyStorage.i().getString("ask_pwr_permission").equals("no")) return;
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        boolean res = powerManager.isIgnoringBatteryOptimizations("com.worksn");
        if (!res) {
            Intent intent = new Intent();
            intent.setAction(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }
    private void initBroadcastReceiverListener(){
        ActivityBroadcastReceiver.i().init(this, new ActivityBroadcastReceiver.CB() {
            @Override
            public void cb(int code, Object o) {
                switch (code){
                    case C_.CODE_WS_NEW_AUTH_DATA :
                        receiveNewAuthData();
                        break;
                    case C_.CODE_WS_BAD_AUTH_DATA:
                        wsBadAuthData();
                        break;
                    case C_.CODE_WS_ONLINE_LIST:
                        try {
                            Log.i("MyStatus", "CODE_WS_ONLINE_LIST -> "+(String)o);
                            setOnlineStatus((String)o);
                        }catch (NullPointerException ignored){
                            Log.i("MyList", "empty");
                        };
                        break;
                }
            }
        });
    }
    private void initViewElements() {

        //---ekran ---------------------------------------------------
        ekran = (LinearLayout) findViewById(R.id.ekran);
        ekranTop = (LinearLayout) findViewById(R.id.ekranTop);
        upLevelBcgrnd = (LinearLayout) findViewById(R.id.upLevelBcgrnd);
        bigImgRmvSn = (ImageView) findViewById(R.id.webViewImgRmvSn);
        ekran.setOnClickListener(this);
        ekranTop.setOnClickListener(this);
        bigImgRmvSn.setOnClickListener(this);

//--- user menu ---------------------------
        frmUserMenu = (LinearLayout) findViewById(R.id.frmUserMenu);
        uMenuProfile = (LinearLayout) findViewById(R.id.uMenuProfile);
        uMenuAvatar = (ImageView) findViewById(R.id.uMenuAvatar);
        uMenuBell = (ImageView) findViewById(R.id.uMenuBell);

        btChooseUsers = (LinearLayout) findViewById(R.id.btChooseUsers);
        btBanUsers = (LinearLayout) findViewById(R.id.btBanUsers);
        btAllMsg = (LinearLayout) findViewById(R.id.btAllMsg);
        btNewMsg = (LinearLayout) findViewById(R.id.btNewMsg);
        btExit = (LinearLayout) findViewById(R.id.btExit);
        btSetting = (FrameLayout) findViewById(R.id.btSetting);

        uMenuProfile.setOnClickListener(this);
        btChooseUsers.setOnClickListener(this);
        btBanUsers.setOnClickListener(this);
        btAllMsg.setOnClickListener(this);
        btNewMsg.setOnClickListener(this);
        btExit.setOnClickListener(this);
        btSetting.setOnClickListener(this);

//--- login form --------------------------
        frmAuthForm = (LinearLayout) findViewById(R.id.frmAuthForm);
        authFormLogin = (EditText) findViewById(R.id.authFormLogin);
        authFormPassword = (EditText) findViewById(R.id.authFormPassword);
        authFormRegistration = (TextView) findViewById(R.id.authFormRegistration);
        authFormRecovery = (TextView) findViewById(R.id.authFormRecovery);
        authFormEnter = (TextView) findViewById(R.id.authFormEnter);
        authFormAnonym = (TextView) findViewById(R.id.authFormAnonym);

        authFormRegistration.setOnClickListener(this);
        authFormRecovery.setOnClickListener(this);
        authFormEnter.setOnClickListener(this);
        authFormAnonym.setOnClickListener(this);

//--- ads type ----------------------------
        frmAdsType = (LinearLayout) findViewById(R.id.frmAdsType);
        btAdsTypeWorker = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.btAdsTypeWorker);
        btAdsTypeEmployer = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.btAdsTypeEmployer);
        btMyLocation = (ImageView)  findViewById(R.id.btMyLocation);
        btMyLocation.setOnClickListener(this);
        btAdsTypeWorker.setOnClickListener(this);
        btAdsTypeEmployer.setOnClickListener(this);


//--- map ---------------------------------
        frmMap = (LinearLayout) findViewById(R.id.frmMap);

//--- ads parameter -----------------------
        frmAdsParam = (LinearLayout) findViewById(R.id.frmAdsParam);
        btAdsParamCategory = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.btAdsParamCategory);
        btAdsParamUser = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.btAdsParamUser);
        btAdsParamAdd = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.btAdsParamAdd);

        adsParamCategoryField = (LinearLayout)findViewById(R.id.adsParamCategoryField);
        adsParamAddField = (LinearLayout)findViewById(R.id.adsParamAddField);
        adsParamUserField = (LinearLayout)findViewById(R.id.adsParamUserField);

        btAdsParamCategory.setOnClickListener(this);
        btAdsParamUser.setOnClickListener(this);
        btAdsParamAdd.setOnClickListener(this);

//--- ads card ----------------------------
        frmAdsCard = (FrameLayout) findViewById(R.id.frmAdsCard);

//--- active frame ------------------------
        frmActive = (LinearLayout) findViewById(R.id.frmActive);
        activeMsgGroup = (LinearLayout) findViewById(R.id.activeMsgGroup);
        activeMsgChain = (LinearLayout) findViewById(R.id.activeMsgChain);
        activeCategory = (LinearLayout) findViewById(R.id.activeCategory);
        activeCategoryList = (LinearLayout) findViewById(R.id.activeCategoryList);
        activeLifetime = (LinearLayout) findViewById(R.id.activeLifetime);
        activeLifetimeList = (LinearLayout) findViewById(R.id.activeLifetimeList);
        activeUsers = (LinearLayout) findViewById(R.id.activeUsers);
        activeUsersGrid = (GridLayout) findViewById(R.id.activeUsersGrid);
        activeAdd = (FrameLayout) findViewById(R.id.activeAdd);
        activeTargetAds = (LinearLayout) findViewById(R.id.activeTargetAds);
        activeEmpty = (LinearLayout) findViewById(R.id.activeEmpty);
        frmActiveTitle = (TextView) findViewById(R.id.frmActiveTitle);

//--- add ads ------------------------------
        addAdsCost = (EditText) findViewById(R.id.addAdsCost);
        addAdsDescription = (EditText) findViewById(R.id.addAdsDescription);
        addAdsLoadImgsIcon = (FrameLayout) findViewById(R.id.addAdsLoadImgsIcon);
        addAdsNoAuth = (TextView) findViewById(R.id.addAdsNoAuth);
        addAdsAuth = (LinearLayout) findViewById(R.id.addAdsAuth);
        addAdsLoadImgsIcon.setOnClickListener(this);
        addAdsCancel = (TextView) findViewById(R.id.addAdsCancel);
        btAddAdsTime = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.btAddAdsTime);
        btAddAdsGallery = (ImageView) findViewById(R.id.btAddAdsGallery);
        btAddAdsPhoto = (ImageView) findViewById(R.id.btAddAdsPhoto);
        btAddAdsTime.setOnClickListener(this);
        btAddAdsGallery.setOnClickListener(this);
        btAddAdsPhoto.setOnClickListener(this);
        addAdsCancel.setOnClickListener(this);

        addAdsActivePeriod = (LinearLayout) findViewById(R.id.addAdsActivePeriod);
        hourListStart = (Spinner)findViewById(R.id.hourListStart);
        hourListStop  = (Spinner)findViewById(R.id.hourListStop );
        minListStart  = (Spinner)findViewById(R.id.minListStart );
        minListStop   = (Spinner)findViewById(R.id.minListStop  );
        hourListStart.setOnItemSelectedListener(this);
        hourListStop .setOnItemSelectedListener(this);
        minListStart .setOnItemSelectedListener(this);
        minListStop  .setOnItemSelectedListener(this);

        ArrayAdapter adapterHours   = ArrayAdapter.createFromResource(activity,R.array.hourList,R.layout.time_spinner);
        ArrayAdapter adapterMinutes = ArrayAdapter.createFromResource(activity,R.array.minList, R.layout.time_spinner);
        adapterHours.setDropDownViewResource  (R.layout.spinner_list);
        adapterMinutes.setDropDownViewResource(R.layout.spinner_list);
        hourListStart.setAdapter(adapterHours);
        hourListStop.setAdapter(adapterHours);
        minListStart.setAdapter(adapterMinutes);
        minListStop.setAdapter(adapterMinutes);

        hourListStart.setOnItemSelectedListener(this);
        hourListStop.setOnItemSelectedListener(this);
        minListStart.setOnItemSelectedListener(this);
        minListStop.setOnItemSelectedListener(this);

//--- search ------------------------------
        frmSearch = (FrameLayout) findViewById(R.id.frmSearch);
        searchTxt = (EditText) findViewById(R.id.searchTxt);
        searchButton = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

//--- send message ------------------------
        frmSendMsg      = (FrameLayout) findViewById(R.id.frmSendMsg);
        sendMsgTxt      = (EditText) findViewById(R.id.sendMsgTxt);
        sendMsgGallery  = (ImageView) findViewById(R.id.sendMsgGallery);
        sendMsgButton   = (LinearLayout) findViewById(R.id.sendMsgButton);
        sendCamera      = (LinearLayout) findViewById(R.id.sendCamera);

        sendMsgButton.setOnClickListener(this);
        sendMsgGallery.setOnClickListener(this);
        sendCamera.setOnClickListener(this);


        initTxtListener();

    }
    private void initTxtListener(){
        final boolean[] flg = {false};
        addAdsDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String a, a1, tmp;
                if (addAdsDescription.length()>1){
                    tmp = addAdsDescription.getText().toString();
                    a = tmp.substring(tmp.length()-2);
                    if (flg[0]){
                        int pos = addAdsDescription.length();
                        addAdsDescription.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_MULTI_LINE);
                        addAdsDescription.setSelection(pos);
                        flg[0] = false;
                    }

                    if (a.equals(". ") || a.equals("? ") || a.equals("! ")){
                        int pos = addAdsDescription.length();
                        addAdsDescription.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_CAP_CHARACTERS | TYPE_TEXT_FLAG_MULTI_LINE);
                        addAdsDescription.setSelection(pos);
                        flg[0] = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sendMsgTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String a,  tmp;
                if (sendMsgTxt.length()>2){
                    tmp = sendMsgTxt.getText().toString();
                    a = tmp.substring(tmp.length()-2);
                    if (flg[0]){
                        int pos = sendMsgTxt.length();
                        sendMsgTxt.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_MULTI_LINE | TYPE_TEXT_FLAG_AUTO_COMPLETE);
                        sendMsgTxt.setSelection(pos);
                        flg[0] = false;
                    }
                    if (a.equals(". ") || a.equals("? ") || a.equals("! ")){
                        int pos = sendMsgTxt.length();
                        sendMsgTxt.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_CAP_CHARACTERS | TYPE_TEXT_FLAG_MULTI_LINE | TYPE_TEXT_FLAG_AUTO_COMPLETE);
                        sendMsgTxt.setSelection(pos);
                        flg[0] = true;
                    }
                }
                char c = '0';
                try {
                    c = charSequence.charAt(i);
                }catch (Exception ignored){}
                byte  b = (byte) c;

                if (b == 10) {
                    btSendMsg();
                } else {
                    if (sendMsgTxt.length()>1)
                        MsgManager.i().wsSendPrintMsgProcess(activity);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void initPreferenceStorage() {
        PrefStorageCookie.init(getApplicationContext());
//        PrefStorageApp.init(getApplicationContext());
        G_.cookie = PrefStorageCookie.getAllProperty();

        if (MyStorage.i().getString("ask_pwr_permission") == null)
            MyStorage.i().putData("ask_pwr_permission", "yes");
    }

    private void initMap() {
        MyMap.i().initMap(this, new MyMap.CB() {
            @Override
            public void callback(int code, Object o) {
                switch (code) {
                    case C_.CODE_MAP_INIT:
                        showAllAds();
                        break;
                    case C_.CODE_MAP_MOVE:
                        if(MyScreen.screenMode == C_.SCREEN_MODE_ADD_ADS)return;
                        showVisibilityAds();
                        break;

                }
            }
            @Override
            public void setPointReturn(Point point) {
                setTapPoint(point);
            }

            @Override
            public void adsListReturn(List<Ads> adsList) {
                showTargetAds(adsList, true);
            }
        });
    }
    private void initKbrdOpenListener() {
        KeyboardVisibilityEvent.setEventListener((Activity) this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if ((MyScreen.screenMode == C_.SCREEN_MODE_ADD_ADS) && (MyScreen.activeMode == C_.ACTIVE_SCREEN_ADD)) {
                            if (isOpen) {
                                frmMap.setVisibility(View.GONE);
                                frmAdsType.setVisibility(View.GONE);

                            } else {
                                frmMap.setVisibility(View.VISIBLE);
                                frmAdsType.setVisibility(View.VISIBLE);

                            }
                        }

                    }
                });
    }
    private void initSubMenuListener() {
        SubMenu.i().hide(this);
        SubMenu.i().init(this, (code, object) -> {
            switch (code) {
                case C_.SUBMENU_CODE_REMOVE_MSG:
                    MsgManager.i().removeMsg(activity, (StructMsg) object);
                    break;
                case C_.SUBMENU_CODE_REMOVE_MSG_GROUP:
                    rcVwMsgGroup.removeMsgGroup((StructMsg) object);
                    break;
                case C_.SUBMENU_CODE_REPLY_TO_MSG:
                    sendMsgTxt.setHint(activity.getString(R.string.yourResponse));
                    new FrameReplyToMsg(activity).show((StructMsg) object);
                    break;
                case C_.SUBMENU_CODE_ADS_REMOVE:
                    modifyAdsState((Ads) object, C_.ACT_ADS_REMOVE);
                    break;
                case C_.SUBMENU_CODE_ADS_RECOVERY:
                    modifyAdsState((Ads) object, C_.ACT_ADS_RECOVERY);
                    break;
                case C_.SUBMENU_CODE_ADS_HIDDEN:
                    modifyAdsState((Ads) object, C_.ACT_ADS_HIDDEN);
                    break;
                case C_.SUBMENU_CODE_ADS_SHOW:
                    modifyAdsState((Ads) object, C_.ACT_ADS_SHOW);
                    break;
                case C_.SUBMENU_CODE_ADS_EDIT:
                    showEditAdsPanel((Ads) object);
                    break;


            }
        });
    }

    private void initAdapterListener() {
        adapterListener = (eCode, data1, data2) -> {
            switch (eCode) {
                case C_.CODE_SHOW_DISCUS:
                    StructMsg msg = (StructMsg) data1;
                    getMsgChain(msg.getDiscus_id());
                    break;
                case C_.CODE_SHOW_TARGET_ADS:
                    Ads ads = (Ads)data1;
                    showAdsDetails(ads);
                    break;
                default:
                    SubMenu.i().hide(activity);
            }
        };
        rcVwMsgGroup = new RcVwMsgGroup (this, adapterListener);
        rcVwTargetAds= new RcVwTargetAds(this, adapterListener);
    }
    private void initSettingData(){
        if (!MyStorage.i().getBoolen(C_.STR_SHOW_STATUS_CHANGE)){
            MyStorage.i().putData(C_.STR_SWITCH_SHOW_STATUS, true);
        }
        if (!MyStorage.i().getBoolen(C_.STR_CONFIRM_DELIVER_CHANGE)){
            MyStorage.i().putData(C_.STR_SWITCH_CONFIRM_DELIVER, true);
        }
        if (!MyStorage.i().getBoolen(C_.STR_CONFIRM_VIEWED_CHANGE)){
            MyStorage.i().putData(C_.STR_SWITCH_CONFIRM_VIEWED, true);
        }
        if (!MyStorage.i().getBoolen(C_.STR_SEND_PRINT_TEXT_CHANGE)){
            MyStorage.i().putData(C_.STR_SWITCH_SEND_PRINT_TEXT, true);
        }
    }

    private void createCategoryList(ArrayList<AssocData> categories){
        G_.catList.addAll(categories);
        for (AssocData data : categories){
            String name = data.getName();
            Integer val = Integer.parseInt((String) data.getVal());

            LayoutInflater inflater = (this.getLayoutInflater());
            View v = inflater.inflate(R.layout.shell_layout_mp_w, (ViewGroup) activeCategoryList, false);
            new SelectButton(this, v, name, val, new SelectButton.CB() {
                @Override
                public void callback(String name, Object val) {
                    MyAds.i().cllct.setCatNum((Integer) val);
                    MyAds.i().cllct.setCatName(name);
                    new ButtonsHighLight(activity).clearAdsParamBtColor();
                    getAdsCollection(new CbGetAdsCllct() {
                        @Override
                        public void callback(Object data, int result) {
                            btSelectCategory(result);

                        }
                    });
                }
            });
            activeCategoryList.addView(v);
        }
    }
    private void createLifetimeList(ArrayList<AssocData> lifetimeList){
        for (AssocData data : lifetimeList){
            String name = data.getName();
            String className = data.getVal().getClass().getSimpleName();
            long val = 0;
            if (className.equals("Double")){
                double d = (Double)data.getVal();
                val = (long)d;
            }else {
                val  = (long) data.getVal();
            }

            LayoutInflater inflater = (this.getLayoutInflater());
            View v = inflater.inflate(R.layout.shell_layout_mp_w, (ViewGroup) activeLifetimeList, false);
            new SelectButton(this, v, name, val, new SelectButton.CB() {
                @Override
                public void callback(String name, Object val) {
                    MyAds.i().lifetime = (Long) val;
                    btAddAdsTime.setText(name);
                    showAddAdsPanel();
                }
            });
            activeLifetimeList.addView(v);
        }
    }
    private void saveEnvironmentContentToStorage(ArrayList<StructTxtData> dataList){
        StringBuilder fieldsList = new StringBuilder();
        for (StructTxtData data : dataList){
            fieldsList.append(data.getName()).append("___");
            MyStorage.i().putData(data.getName(), data.getDescription());
        }
        if (fieldsList.length() < 10)return;
        fieldsList = new StringBuilder(fieldsList.substring(0, fieldsList.length() - 3));
        MyStorage.i().putData(MyStorageConst.ENVIRONMENT_CONTENT_LIST, fieldsList.toString());
    }

//---- LOCATION FUNCS ------------------------------------------------------------------------------
    private void initLocationManager() {
        MyLocation.i().turnGPSOn(this);
        MyLocation.i().initLocationManager(this, 60, new MyLocation.CB() {
            @Override
            public void callback(Location location) {
                if(!MyLocation.locationWasChange && (MyScreen.activeMode != C_.ACTIVE_SCREEN_MSG_CHAIN))
                    if (AppMode.i().getMode() != C_.APP_MODE_ADD_ADS)showMyLocation(location);

            }
        });
    }

//---- SEND FILE -----------------------------------------------------------------------------------
    private void chooseFile(Integer action){
        if (TmpImg.sendImgIsRun){
            PUWindow.i().show(R.string.whiteLoadPrevFile);
            return;
        }
        new MyImg(this).setWebView(null);
        mMyFile.chooseFile(this, action);
    }
    private void makePhoto(Integer action){
        if (TmpImg.sendImgIsRun){
            PUWindow.i().show(R.string.whiteLoadPrevFile);
            return;
        }
        new MyImg(this).setWebView(null);
        mMyFile.makePhoto(this, action);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == C_.PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            int imgDestination = data.getIntExtra(C_.STR_RESP_TYPE, 0);
            boolean showBtCancel = (imgDestination == C_.IMG_FOR_ADS);
            TmpImg.wasSendImg = true;
            if (imgDestination == C_.IMG_FOR_MSG){
                frmActiveTitle.setVisibility(View.GONE);
                mVw.screen(C_.SCREEN_MODE_MSG_CHAIN, C_.ACTIVE_SCREEN_MSG_CHAIN);
                new MyImg(this).setImageView(TmpImg.img.getAbsolutePath());
            }
            mMyFile.uploadFile(this, TmpImg.img, TmpImg.createId, showBtCancel, new ComCallback() {
                @Override
                public Object callback(Object object, Integer result) {
                    if (result == RESULT_OK){
                        MyContext response = (MyContext)object;
                        SaveImgData saveImgData = response.getSaveImgData();
                        if (saveImgData != null){
                            MsgManager.i().sendBindImgToMsg(activity, saveImgData);
                        }else {
                            if (!TmpImg.wasSendPostImg){
                                TmpImg.imgSend     = response.getTmpImg();
                                TmpImg.imgIconSend = response.getTmpImgIcon();
                                if (imgDestination == C_.IMG_FOR_ADS)
                                    new MyImg(activity).addImgToGroup(C_.URL_BASE+ TmpImg.imgSend, C_.URL_BASE+ TmpImg.imgIconSend);
                            }else {
                                TmpImg.imgSend        = null;
                                TmpImg.imgIconSend    = null;
                                TmpImg.wasSendPostImg = false;
                                TmpImg.postImgWasLoad = true;
                            }
                        }
                    }
                    return null;
                }
            });
        }else{
            PUWindow.i().show(R.string.errorSendFile);
        }
    }


//--------- UI buttons -----------------------------------------------------------------------------
    private void btShowWorkerAds(){
        clearSearchField();
        if(MyAds.i().cllct.getAdsType() == C_.ADS_TYPE_WORKER){
            MyAds.i().cllct.setAdsType(C_.ADS_TYPE_ANY);
        } else {
            MyAds.i().cllct.setAdsType(C_.ADS_TYPE_WORKER);
        }
        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_EMPTY);
        new ButtonsHighLight(this).clearAdsParamBtColor();
        getAdsCollection(new CbGetAdsCllct() {
            @Override
            public void callback(Object data, int result) {
            }
        });
    }
    private void btShowEmployerAds(){
        clearSearchField();
        if(MyAds.i().cllct.getAdsType() == C_.ADS_TYPE_EMPLOYER){
            MyAds.i().cllct.setAdsType(C_.ADS_TYPE_ANY);
        } else {
            MyAds.i().cllct.setAdsType(C_.ADS_TYPE_EMPLOYER);
        }
        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_EMPTY);
        new ButtonsHighLight(this).clearAdsParamBtColor();
        getAdsCollection(new CbGetAdsCllct() {
            @Override
            public void callback(Object data, int result) {
            }
        });
    }

    private void btSetMyLocation(){
        showMyLocation(MyLocation.i().getImHere());
    }
    private void btSelectCategory(int result){
        clearSearchField();
    }
    private void btShowTargetUsers(){
        AppMode.i().setMode(C_.APP_MODE_MAIN);
        MyAds.i().cllct.setUserId(C_.ADS_TYPE_ANY);
        Usr.i().clearUsersList();
        getAdsCollection(( data,  result)->{
            if(result > 0){
                frmActiveTitle.setText(R.string.ownersOfVisibleAds);
                mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_USERS);
                initUsersPanel(Usr.i().getTargetUsers());
            }
            else mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_EMPTY);
        });
        new ButtonsHighLight(this).setAdsParamBtColor(C_.ADS_PARAM_USER);
    }
    private void btCancelAddAds(){
        clearAddAdsData();
        AppMode.i().setMode(C_.APP_MODE_MAIN);
        new ButtonsHighLight(this).clearAdsParamBtColor();
        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_TARGET_ADS);
    }
    private void btAddAdsClick() {
        if (Usr.i().getUser() == null){
            new PUWindow().show(R.string.needAuthForAddAds);
            return;
        }
        if (MyScreen.screenMode == C_.SCREEN_MODE_ADD_ADS) {
            addAds();
        } else {
            showAddAdsPanel();
            AppMode.i().setMode(C_.APP_MODE_ADD_ADS);
        }
    }
    private void btShowChooseUsers(){
        frmActiveTitle.setText(R.string.chooseUsers);
        showBwList(C_.ACT_GET_LIKE_USERS);
    }
    private void btShowBanUsers(){
        frmActiveTitle.setText(R.string.banUsers);
        showBwList(C_.ACT_GET_BAN_USERS);
    }
    private void btSendMsg(){
        MsgManager.i().sendMsg(this);
    }
    private void btAnonymousLogin(){
        Usr.i().anonymousLogin(this, (code, data) -> {
            mVw.header();
            initAuthorizedUserMode();
        });
    }
    private void btLoginUser(){
        String login    = authFormLogin.getText().toString();
        String password = authFormPassword.getText().toString();
        if((login.equals(""))&&(password.equals(""))){PUWindow.i().show( R.string.enterLoginAndPassword);return;}
        new Kbrd().hide(this);

        Usr.i().loginUser(this, login, password, new Usr.CB() {
            @Override
            public void callback(int code, Object data) {
                mVw.header();
                initAuthorizedUserMode();
            }
        });
    }
    private void btExitUser(){
        new BroadCastMsg(this, C_.ACT_EXIT, WsBroadcastReceiver.BROADCAST_FILTER);
        exitUser();
    }

//--------- client-server funcs --------------------------------------------------------------------

    private void getMsgChain(Long discus_id){
        if (discus_id == null || rmvSubMenu())return;
        PostSubData subData = new PostSubData();
        subData.setDiscusId(discus_id);
        MyNet.sendRequest(this,C_.ACT_GET_CHAIN_MSG, subData, (data, result, stringData) ->{
            if (result != -1){
                showDiscus(data, result);
                checkNewMsg();
            }
        });
    }
    private void getMsgChainForAds(Ads ads, int consumerId){
        if (Usr.i().getUser() == null)return;
        PostSubData subData = new PostSubData();
        subData.setAds_id(ads.getId());
        subData.setConsumerId(consumerId == 0 ? ads.getUserId() : consumerId);
        subData.setSenderId(Usr.i().getUser().getId());

        MyNet.sendRequest(this,C_.ACT_GET_DISCUS_FOR_ADS, subData, (data, result, stringData) -> {
            if (result != -1)
                showDiscus(data, result);

        });
    }

    private void getMsgGroup(int msgGrpType){
        AppMode.i().setMode(C_.APP_MODE_MAIN);
        String act = "";
        switch (msgGrpType) {
            case C_.MSG_TYPE_ALL : act =  C_.ACT_GET_ALL_MSG; break;
            case C_.MSG_TYPE_NEW : act =  C_.ACT_GET_NEW_MSG; break;
        }
        MsgManager.i().getMsgGroup(this, act, (msgs, result, stringData) -> {
            if (result == 0){
                PUWindow.i().show(stringData);
                if(msgGrpType == C_.MSG_TYPE_NEW){
                    MyStorage.i().putData(MyStorageConst.NEW_MSG_SIGN, false);
                    mVw.setBell();
                }
            }
            if (result > 0){
                showMsgGroup(msgs);
                if(msgGrpType == C_.MSG_TYPE_NEW){
                    MyStorage.i().putData(MyStorageConst.NEW_MSG_SIGN, true);
                    mVw.setBell();
                }
            }
        });
    }
    private void getDiscusWithUser(int userId){
        PostSubData subData = new PostSubData();
        subData.setSpeakerId(userId);
        MyNet.sendRequest(this, C_.ACT_GET_USER_MSG, subData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                boolean dataError;
                List<StructMsg> msgs = new ArrayList<>();
                if (result == 0){
                    Ads ads = new Ads();
                    ads.setId(C_.ADS_ID_FOR_DIRECT_DISCUS);
                    getMsgChainForAds(ads, userId);
                }else {
                    MsgManager.i().setMsgContext(data);
                    if (data.getMessages() == null) return;
                    for (StructMsg msg : data.getMessages()){
                        dataError = msg.getAds_id() == null;
                        if (!dataError)msgs.add(msg);
                    }
                    try{
                        if (msgs.size()>0){
                            User user = Usr.i().getUserDataFromObject(msgs.get(0));
                            if (user != null)
                                Usr.i().addUserToTable(user);
                            else
                                return;

                            String txt = activity.getString(R.string.discusWith_)+msgs.get(0).getSpeakerLogin();
                            frmActiveTitle.setText(txt);
                            showMsgGroup(msgs);
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void getAdsCollection(CbGetAdsCllct cb){
        MyAds.i().getAdsCollection(this, new MyAds.CB() {
            @Override
            public void callback(int code, Object o, int result) {
                if (result > 0){
                    MyMap.i().addPmGroup(activity, MyAds.i().targetAdsList);
                    if (AppMode.i().getMode() != C_.APP_MODE_ADD_ADS)
                        showAdsList(MyAds.i().targetAdsList, null);

                }
                checkReturnToAddAdsPanel(result);
                if ((cb != null)&&(result != -1))
                    cb.callback(o, result);

            }
        });
    }
    private void getAllVisibleAds(){
        MyAds.i().cllct.clear(this);
        getAdsCollection(new CbGetAdsCllct() {
            @Override
            public void callback(Object data, int result) {
                new ButtonsHighLight(activity).setAdsParamBtColor(C_.ADS_PARAM_ANY);
                mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_TARGET_ADS);
            }
        });
    }
    private void selectCategoryAds(){
        new ButtonsHighLight(this).setAdsParamBtColor(C_.ADS_PARAM_CATEGORY);
        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_CATEGORY);
    }
    private void selectAdsLifetime(){
        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_LIFETIME);
    }
    private void searchAds(){
        String searchPhrase = (searchTxt.getText() != null) ? searchTxt.getText().toString() : null;
        if (searchPhrase != null) {
            MyAds.i().cllct.setSearchPhrase(searchPhrase);
            getAdsCollection(null);
            new Kbrd().hide(this);
        } else {
            PUWindow.i().show(R.string.enterSearchTxt);
        }
    }
    private void checkNewMsg(){
        PostSubData subData = new PostSubData();
        MyNet.sendRequest(this,C_.ACT_CHECK_NEW_MSG, subData, (MyContext data, Integer result, String stringData) -> {
            MyStorage.i().putData(MyStorageConst.NEW_MSG_SIGN, result == 1);
            mVw.setBell();
        });
    }
    private void modifyAdsState(Ads ads, String act){
        MyAds.i().modifyAdsState(this, ads, act, new MyAds.CB() {
            @Override
            public void callback(int code, Object o, int result) {
                if (code == 1){
                    Ads ads1 = new Ads();
                    ads1 = ads;
                    int index = MyAds.i().targetAdsList.indexOf(ads);
                    MyAds.i().targetAdsList.remove(ads);
                    switch (act){
                        case C_.ACT_ADS_RECOVERY : ads1.setVisibleMode(C_.ADS_VISIBLE_NORMAL);break;
                        case C_.ACT_ADS_REMOVE   : ads1.setVisibleMode(C_.ADS_VISIBLE_HIDDEN_REMOVE);break;
                        case C_.ACT_ADS_HIDDEN   : ads1.setVisibleMode(C_.ADS_VISIBLE_HIDDEN_MANUAL);break;
                        case C_.ACT_ADS_SHOW     : ads1.setVisibleMode(C_.ADS_VISIBLE_NORMAL);break;
                    }

                    MyAds.i().targetAdsList.add(index, ads1);
                    rcVwTargetAds.renderAdsStatus(ads);
                }
            }
        });
    }
    private void receiveNewAuthData(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PUWindow.i().show(R.string.signInFromOtherDevice);
            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                exitUser();
            }
        }, 500);
    }
    private void wsBadAuthData(){
        btExitUser();
    }

//--- map funcs  -----------------------------------------------------------------------------------
    private void setTapPoint(Point point){
        MyMap.i().cleanMapObjectCollections(C_.MAP_PM_RED);
        MyMap.i().cleanMapObjectCollections(C_.MAP_PM_PURPLE);
        MyAds.i().mapPoint = null;
        if(MyScreen.screenMode == C_.SCREEN_MODE_ADD_ADS) {
            MyMap.i().setTapPm(this, point);
        }
    }

//--- net funcs ------------------------------------------------------------------------------------
    private void addAds() {
        String description = null;
        if ((addAdsDescription.getText()==null) || (addAdsDescription.getText().length() == 0)){
            PUWindow.i().show(R.string.enterAdsTxt);
            return;
        }else {
            description = addAdsDescription.getText().toString();
        }

        int adsCost = 0;
        try {
            adsCost = Integer.parseInt(addAdsCost.getText().toString());
        }catch (NumberFormatException ignored) {}

        Ads data = new Ads();
        data.setCost(adsCost);
        data.setDescription(description);
        MyAds.i().addAds(this, data, new MyAds.CB() {
            @Override
            public void callback(int code, Object o, int result) {
                clearAddAdsData();
                PUWindow.i().show(R.string.adsWasAdd);
                getAllVisibleAds();
            }
        });
    }
    private void clearAddAdsData(){
        addAdsDescription.setText("");
        addAdsCost.setText("");
        addAdsLoadImgsIcon.removeAllViews();
        clearActivePeriod();
        new MyImg(activity).clearLoadImgsScroll();
        new MyFile(this).removeTmpFile(null);
        TmpImg.img  = null;
        TmpImg.icon = null;
    }
    private void getEnvironmentData(){
        MyNet.sendRequest(this, C_.ACT_GET_ENVIRONMENT_DATA, null, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (result == 1){
                    if (data.getUser() != null){
                        Usr.i().setUser(data.getUser());
                    }else{
                        Usr.i().clearUser();
                    }
                    if (data.getCategories() != null)
                        createCategoryList(data.getCategories());

                    if (data.getLifetime() != null)
                        createLifetimeList(data.getLifetime());

                    if (data.getSettingPageContent() != null)
                        saveEnvironmentContentToStorage(data.getSettingPageContent());

                    mVw.header();
                }
            }
        });
    }
    private void setOnlineStatus(List<Integer> idList){
        for (int id : idList)
        if (MyScreen.activeMode == C_.ACTIVE_SCREEN_USERS){
            for (FrameUserProfile userProfile : Usr.i().getUserProfiles()){
                Log.i("MyStatus", "id -> "+ userProfile.getUserId()+"; login -> "+userProfile.getUserLogin());
                userProfile.setOnline(this, idList.contains(userProfile.getUserId()));
            }
        }
    }
    private void exitUser(){
        Usr.i().exit(this, new Usr.CB() {
            @Override
            public void callback(int code, Object data) {
                Usr.i().clearUser();
                mVw.header();
                mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_CATEGORY);
                clearTempAppData();
                rcVwMsgGroup.clearRcVw();
                MsgManager.i().clearRcVw(activity);
                new WsServiceControl(activity).stop();
            }
        });

    }

    //--- Sub activity funcs ---------------------------------------------------------------------------
    private void showRegistrationForm(){
    Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
    startActivity(intent);
}
    private void showRecoveryForm(){
        Intent intent = new Intent(MainActivity.this, RecoveryPasswordActivity.class);
        startActivity(intent);
    }
    private void showUpdateUserForm(){
        if(Usr.i().getUser().getAutoAuth() == 1){
            Intent intent = new Intent(MainActivity.this, UpdateAnonymUserDataActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(MainActivity.this, UpdateUserDataActivity.class);
            startActivity(intent);
        }
    }

    //---  render funcs ----------------------------------------------------------------------------
    private void restorePrevView(){
        mVw.screen(MyScreen.prevMode, MyScreen.prevActive);
        MyScreen.prevMode = -1;
        MyScreen.prevActive = -1;
    }
    private boolean rmvSubMenu(){
        return SubMenu.i().hide(this);
    }
    private void initUsersPanel(ArrayList<User> users){
        ArrayList<Integer> arrId = new ArrayList<>();
        activeUsersGrid.removeAllViews();
        for (User user : users) {
            if (!arrId.contains(user.getId())) {
                addUserProfileToUsersGrid(user);
            }
            arrId.add(user.getId());
        }
    }
    private void setOnlineStatus(String idListStr){
        Usr.i().onlineList = new ArrayList<>();
        if (idListStr.length()>0){
            try{
                String[] idListArr = idListStr.split("_");
                for (String id : idListArr){
                    Usr.i().addIdToOnlineList(Integer.parseInt(id));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        switch (MyScreen.activeMode){
            case C_.ACTIVE_SCREEN_MSG_GROUP :
                rcVwMsgGroup.setOnlineStatus();
                break;
            case C_.ACTIVE_SCREEN_TARGET_ADS:
                rcVwTargetAds.setOnlineStatus();
                break;
            case C_.ACTIVE_SCREEN_MSG_CHAIN :
                new AdsCard(this).setStatus(Usr.i().onlineList);
                break;
            case C_.ACTIVE_SCREEN_USERS     :
                setOnlineStatus(Usr.i().onlineList);
                break;
        }
    }

    //--- VIEWS funcs ------------------------------------------------------------------------------
    private void showBwList(String act){
        MyNet.sendRequest(this, act, null, (data, result, stringData)->{
            if(result > 0){
                AppMode.i().setMode(C_.APP_MODE_SHOW_BW_LIST);
                ArrayList<User> users = new ArrayList<>(data.getUsersList());
                Usr.i().addUsersList(users);
                mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_USERS);
                initUsersPanel(Usr.i().getTargetUsers());
                Usr.i().requestUsersStatus(this);
            }
            else mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_EMPTY);
        });
    }
    private void clearSearchField(){
        MyAds.i().cllct.setSearchPhrase(null);
        searchTxt.setText("");
    }
    private void showDiscus(MyContext data, int msgQt){
        Ads ads = new Ads();
        MsgManager.i().setMsgContext(data);
        boolean err = false;
        try{
            if (data.getSpeaker().getId() == null){
                err = true;
                Log.i("MyEx", "showDiscus: error getSpeaker");
                new MyLog().sendLogData(this, "showDiscus: error getSpeaker");
            }
            if (data.getTargetAds() == null){
                err = true;
                Log.i("MyEx", "showDiscus: error getTargetAds");
                new MyLog().sendLogData(this, "showDiscus: error getTargetAds");
            }
            if (data.getDiscus().getId() == null){
                err = true;
                Log.i("MyEx", "showDiscus: error getDiscus");
                new MyLog().sendLogData(this, "showDiscus: error getDiscus");
            }
            if(err){
                new PUWindow().show(R.string.someThrowable);
                return;
            }
        }catch (NullPointerException e){
            new MyLog().sendException(e);
            return;
        }
        sendMsgTxt.setHint(this.getString(R.string.msgTxt));
        ads = data.getTargetAds();
        ads.setUserId(data.getSpeaker().getId());
        ads.setUserLogin(data.getSpeaker().getLogin());

        ads.setUserImgIcon(data.getSpeaker().getImgIcon());
        ads.setUserRating(data.getSpeaker().getRating());
        ads.setUserVoteQt(data.getSpeaker().getVoteQt());
    /////////////////////////////////////////////////////////
        mVw.screen(C_.SCREEN_MODE_MSG_CHAIN, C_.ACTIVE_SCREEN_MSG_CHAIN);
        new AdsCard(activity).init(ads, true, false, this::restorePrevView);
        MsgManager.i().getMsgContext().setDiscus(data.getDiscus());
        MsgManager.i().createMsgChainRecyclerView(this, new ArrayList<StructMsg>(), 0);

        if (data.getMessages() != null){
            if(data.getMessages().size() > 0) showMsgChain(data.getMessages());
        }
        new ConfirmDeliverMsg(this, ads.getUserId(), data.getDiscus().getId(), C_.CODE_CONFIRM_VIEWED);
    }
    private void showTargetAds(List<Ads>adsList, boolean openSingleAds){
        ArrayList<Ads> tmpAds = new ArrayList<>(adsList);
        if (rmvSubMenu())return;
        if (MyScreen.screenMode == C_.SCREEN_MODE_ADD_ADS) return;
        if (tmpAds.size() == 0){
            mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_EMPTY);
            return;
        }
        if (openSingleAds){
            if (tmpAds.size() == 1){
                showAdsDetails(tmpAds.get(0));
                return;
            }
        }
        showAdsList(adsList, null);
        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_TARGET_ADS);
    }
    private void showVisibilityAds(){
        getAdsCollection(new CbGetAdsCllct() {
            @Override
            public void callback(Object data, int result) {
                if(result > 0)
                    mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_TARGET_ADS);
                else
                    mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_EMPTY);

            }
        });
    }
    private void showAdsList(List<Ads> list, Integer pos){
        List<Ads> adsList = new ArrayList<>(list);
        rcVwTargetAds.createTargetAdsRecyclerView(this, adsList,  pos);
    }
    private void showAdsDetails(Ads ads){
        if (rmvSubMenu())return;
        if(Usr.i().auth()) getMsgChainForAds(ads, 0);
        else {
            mVw.screen(C_.SCREEN_MODE_MSG_CHAIN, C_.ACTIVE_SCREEN_MSG_CHAIN);
            new AdsCard(activity).init(ads, false, true, this::restorePrevView);
        }
}
    private void showAllAds(){
        MyAds.i().cllct.setAdsType(C_.ADS_TYPE_ANY);
        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_TARGET_ADS);
        new ButtonsHighLight(this).clearAdsParamBtColor();
        getAdsCollection(null);
    }
    private void showAddAdsPanel(){
        if(Usr.i().auth()){
            addAdsAuth.setVisibility(View.VISIBLE);
            addAdsNoAuth.setVisibility(View.GONE);
        }else {
            addAdsAuth.setVisibility(View.GONE);
            addAdsNoAuth.setVisibility(View.VISIBLE);
        }
        new ButtonsHighLight(this).setAdsParamBtColor(C_.ADS_PARAM_ADD);
        mVw.screen(C_.SCREEN_MODE_ADD_ADS, C_.ACTIVE_SCREEN_ADD);
    }
    private void showMsgChain(List<StructMsg>msgs){
        G_.adsCardDeploy = false;
        mVw.screen(C_.SCREEN_MODE_MSG_CHAIN, C_.ACTIVE_SCREEN_MSG_CHAIN);
        new FrameReplyToMsg(this).hide();
        MsgManager.ReplyData.clear();
        MsgManager.i().createMsgChainRecyclerView(this, msgs, 0);
        checkNewMsg();
    }
    private void showMsgGroup(List<StructMsg> messages){
        new ButtonsHighLight(this).clearAdsParamBtColor();
        if (messages == null) return;

        mVw.screen(C_.SCREEN_MODE_MAIN, C_.ACTIVE_SCREEN_MSG_GROUP);
        rcVwMsgGroup.createMsgGroupRecyclerView(messages, 0);
    }
    private void showLoadImgs(){
        if(MyAds.i().images.size() == 0)return;
        MyImg myImg = new MyImg(this);
        List<Integer> finalIdList = myImg.showImgsArray(MyAds.i().images,true);
        mVw.bigImagesList(C_.VAL_SHOW);
        myImg.initCb((code, object) -> {
            Integer id = (Integer)object;
            int index = finalIdList.indexOf(id);
            try {
                index = finalIdList.indexOf(id);
                new MyFile(activity).removeTmpFile(MyAds.i().imgsNames.get(index));
                MyAds.i().removeImg(index);
            } catch (IndexOutOfBoundsException ignored) {}
            addAdsLoadImgsIcon.removeAllViews();
            myImg.addImgIconsToView(MyAds.i().imagesIcon, addAdsLoadImgsIcon, 45,45);
        });
    }
    private void showMyLocation(Location location){
        Point point = new Point(location.getLatitude(), location.getLongitude());
        MyMap.i().setCameraPosition(location);
        MyMap.i().setTapPm(this, point);
        MyAds.i().cllct.setAdsType(C_.ADS_TYPE_ANY);
        new ButtonsHighLight(this).clearAdsParamBtColor();
        getAdsCollection(null);
    }
    private void showTargetUserAds(Integer userId){
        MyAds.i().cllct.setUserId(userId);
        getAdsCollection(null);
        for (FrameUserProfile profile : Usr.i().getUserProfiles()){
            profile.highLight(this, userId.equals(profile.getUserId()));//user.getId()
        }
    }
    private void addUserProfileToUsersGrid(User user){
        user.setOnline(Usr.i().onlineList.contains(user.getId()));
        LayoutInflater inflater = (this.getLayoutInflater());
        View v = inflater.inflate(R.layout.shell_layout_wc, (ViewGroup) activeUsersGrid, false);
        GridLayout.LayoutParams vLp = (GridLayout.LayoutParams)v.getLayoutParams();
        int h = MyScreen.dpToPx(80);
        int w = MyScreen.dpToPx(55);
        vLp.height = h;
        vLp.width  = w;
        v.setLayoutParams(vLp);
        FrameUserProfile userProfile = new FrameUserProfile(this, v, user, (user1, result) -> {
            switch (AppMode.i().getMode(1)){
                case C_.APP_MODE_MAIN         : showTargetUserAds(user1.getId());                  break;
                case C_.APP_MODE_SHOW_BW_LIST :
                    AppMode.i().setMode(C_.APP_MODE_SHOW_BW_LIST);
                    UiClick.i().showUserPage(activity, user1.getId());
                    break;
            }
        });
        Usr.i().addViewUserToList(userProfile);
        activeUsersGrid.addView(v);
    }
    private void showEditAdsPanel(Ads ads){
        AppMode.i().setMode(C_.APP_MODE_ADD_ADS);
        MyAds.i().editAdsId = ads.getId();
        MyAds.i().targetAds = ads;
        MyAds.i().targetAds.setEdit(1);
        String cost = ads.getCost().toString();
        AssocData category = G_.catList.get(ads.getCategory());
        MyAds.i().cllct.setCatNum((Integer.parseInt((String) category.getVal())));
        MyAds.i().cllct.setCatName(category.getName());
        showAddAdsPanel();
        if (ads.getAdsType() == C_.ADS_TYPE_EMPLOYER)
            MyAds.i().cllct.setAdsType(C_.ADS_TYPE_WORKER);
        else
            MyAds.i().cllct.setAdsType(C_.ADS_TYPE_EMPLOYER);
        new ButtonsHighLight(this).setAdsTypeBtColor(MyAds.i().cllct);

        if ((ads.getHourStart() != null)&&
            (ads.getHourStop()  != null)&&
            (ads.getMinStart()  != null)&&
            (ads.getMinStop()   != null)){
            setActivePeriod(ads.getHourStart(),
                            ads.getHourStop() ,
                            ads.getMinStart() ,
                            ads.getMinStop());
        }

        MyAds.i().mapPoint = new Point(ads.getCoordX(), ads.getCoordY());
        MyMap.i().setCameraPosition(MyAds.i().mapPoint);
        MyMap.i().addPm(this, C_.MAP_PM_RED, MyAds.i().mapPoint, null);

        addAdsDescription.setText(ads.getDescription());
        addAdsCost.setText(cost);
        btAdsParamCategory.setText(MyAds.i().cllct.getCatName());
        MyAds.i().prepareAdsImgsForEdit(this, ads);
    }
    private void setActivePeriod(int hStart, int hStop, int mStart, int mStop){
        int[] hArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
        int[] mArr = {0,5,10,15,20,25,30,35,40,45,50,55};
        for (int i = 0; i < hArr.length; i++){
            if (hStart == hArr[i])hourListStart.setSelection(i);
            if (hStop == hArr[i])hourListStop.setSelection(i);
        }
        for (int i = 0; i < mArr.length; i++){
            if (mStart == mArr[i])minListStart.setSelection(i);
            if (mStop == mArr[i])minListStop.setSelection(i);
        }
    }
    private void clearActivePeriod(){
        hourListStart.setSelection(0);
        hourListStop.setSelection (0);
        minListStart.setSelection (0);
        minListStop.setSelection  (0);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int hourStart = 0;
        int hourStop  = 0;
        int minStart  = 0;
        int minStop   = 0;
        boolean error = false;

        try {
            hourStart = Integer.parseInt(hourListStart.getSelectedItem().toString());
            hourStop  = Integer.parseInt(hourListStop .getSelectedItem().toString());
            minStart  = Integer.parseInt(minListStart .getSelectedItem().toString());
            minStop   = Integer.parseInt(minListStop  .getSelectedItem().toString());
        }catch (NumberFormatException e){
            Log.i("MyActiv", "NumberFormatException");
            addAdsActivePeriod.setBackgroundResource(0);
            hourStart = 0;
            hourStop  = 0;
            minStart  = 0;
            minStop   = 0;
        }

        MyAds.i().targetAds.setHourStart(hourStart);
        MyAds.i().targetAds.setHourStop(hourStop);
        MyAds.i().targetAds.setMinStart(minStart);
        MyAds.i().targetAds.setMinStop(minStop);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    interface CbGetAdsCllct{
        void callback(Object data, int result);
    }
}