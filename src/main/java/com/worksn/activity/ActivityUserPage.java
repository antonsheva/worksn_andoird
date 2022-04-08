package com.worksn.activity;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import com.worksn.R;
import com.worksn.adapters.UserReviewsAdapter;
import com.worksn.classes.Kbrd;
import com.worksn.singleton.AppMode;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.PUWindow;
import com.worksn.interfaces.AdapterListener;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.C_;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostSubData;
import com.worksn.objects.User;
import com.worksn.objects.UserReview;
import com.worksn.singleton.Usr;
import com.worksn.static_class.Funcs;
import com.worksn.static_class.Post;
import com.worksn.view.RatingStars;

public class ActivityUserPage extends AppCompatActivity implements View.OnClickListener {
    ImageView    userPageAvatar;
    ImageView    userPageOnline;
    TextView     userPageLogin;
    TextView     userPageVoteQt;
    TextView     userPageRegistration;
    TextView     userPageName;
    TextView     userPageSName;
    TextView     userPageLastTime;
    TextView     userPageAbout;
    ImageView    userPageBigImg;

    ImageView    userPageBan;
    ImageView    userPageChoose;
    ImageView    userPageKonvert;

    EditText     userPageReview;

    LinearLayout userPageStars;
    ImageView userPageStar1;
    ImageView userPageStar2;
    ImageView userPageStar3;
    ImageView userPageStar4;
    ImageView userPageStar5;

    FrameLayout userPageScreen;

    RatingStars ratingStars;
    androidx.appcompat.widget.AppCompatButton userPageBtSend;
    Integer userId = null;
    int bwStatus = 0;
    boolean online = false;
    String lastTime;

    UserReviewsAdapter  userReviewsAdapter;
    LinearLayoutManager layoutManagerReviews;
    AdapterListener adapterListener;
    RecyclerView rcVwUserPage;

    List<UserReview>userReviews = new ArrayList<>();

    User targetUser = null;

    boolean expand = false;
    boolean noClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_user_page);
        initViewElements();
        initUser();
        initScreenClickListeners();
        initBroadcastReceiverListener();
        new Kbrd().hide(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppMode.i().setPage(C_.APP_PAGE_USER_PAGE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityBroadcastReceiver.i().clear(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityBroadcastReceiver.i().clear(this);
    }

    private void initViewElements(){
        userPageAvatar       = (ImageView)findViewById(R.id.userPageAvatar);
        userPageOnline       = (ImageView)findViewById(R.id.userPageOnline);
        userPageLogin        = (TextView)findViewById(R.id.userPageLogin);
        userPageVoteQt       = (TextView)findViewById(R.id.userPageVoteQt);
        userPageRegistration = (TextView)findViewById(R.id.userPageRegistration);
        userPageName         = (TextView)findViewById(R.id.userPageName);
        userPageSName        = (TextView)findViewById(R.id.userPageSName);
        userPageLastTime     = (TextView)findViewById(R.id.userPageLastTime);
        userPageAbout        = (TextView)findViewById(R.id.userPageAbout);

        userPageBan     = (ImageView)findViewById(R.id.userPageBan);
        userPageChoose  = (ImageView)findViewById(R.id.userPageChoose);
        userPageKonvert = (ImageView)findViewById(R.id.userPageKonvert);

        userPageReview = (EditText)findViewById(R.id.userPageReview);
        userPageStars = (LinearLayout)findViewById(R.id.userPageStars);
        userPageStar1 = (ImageView)findViewById(R.id.userPageStar1);
        userPageStar2 = (ImageView)findViewById(R.id.userPageStar2);
        userPageStar3 = (ImageView)findViewById(R.id.userPageStar3);
        userPageStar4 = (ImageView)findViewById(R.id.userPageStar4);
        userPageStar5 = (ImageView)findViewById(R.id.userPageStar5);
        userPageBtSend = (androidx.appcompat.widget.AppCompatButton)findViewById(R.id.userPageBtSend);

        rcVwUserPage = (RecyclerView) findViewById(R.id.rcVwUserPage);
        userPageScreen = (FrameLayout) findViewById(R.id.userPageScreen);
        userPageBigImg = (ImageView) findViewById(R.id.userPageBigImg);

        userPageAbout  .setOnClickListener(this);
        userPageBtSend .setOnClickListener(this);
        userPageStar1  .setOnClickListener(this);
        userPageStar2  .setOnClickListener(this);
        userPageStar3  .setOnClickListener(this);
        userPageStar4  .setOnClickListener(this);
        userPageStar5  .setOnClickListener(this);
        userPageScreen .setOnClickListener(this);
        userPageAvatar .setOnClickListener(this);
        userPageBan    .setOnClickListener(this);
        userPageChoose .setOnClickListener(this);
        userPageKonvert.setOnClickListener(this);

        if (Usr.i().getUser() == null){
            userPageReview.setHint(R.string.needAuthForUserReview);
            userPageReview.setKeyListener(null);
        }else {
            initTxtListener();
        }

    }
    boolean flg = false;
    private void initTxtListener(){

        userPageReview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String a, a1, tmp;
                if (userPageReview.length()>2){
                    tmp = userPageReview.getText().toString();
                    a = tmp.substring(tmp.length()-2, tmp.length()-1);
                    a1 = tmp.substring(tmp.length()-1);
                    Log.i("MyKey", "a -> "+a+"; a1 -> "+a1);


                    if (flg){
                        int pos = userPageReview.length();
                        userPageReview.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_MULTI_LINE | TYPE_TEXT_FLAG_AUTO_COMPLETE);
                        userPageReview.setSelection(pos);
                        flg = false;
                    }

                    if (a1.equals(".")){
                        int pos = userPageReview.length();
                        userPageReview.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_FLAG_CAP_CHARACTERS | TYPE_TEXT_FLAG_MULTI_LINE | TYPE_TEXT_FLAG_AUTO_COMPLETE);
                        userPageReview.setSelection(pos);
                        flg = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (Usr.i().getUser() == null)return;
        switch (v.getId()){
            case R.id.userPageScreen : return;
            case R.id.userPageBtSend : addReview()        ;break;
            case R.id.userPageStar1  : sendVote(1)  ;break;
            case R.id.userPageStar2  : sendVote(2)  ;break;
            case R.id.userPageStar3  : sendVote(3)  ;break;
            case R.id.userPageStar4  : sendVote(4)  ;break;
            case R.id.userPageStar5  : sendVote(5)  ;break;
            case R.id.userPageAbout  : clickAboutText()   ;break;
            case R.id.userPageBan    : switchBan()         ;break;
            case R.id.userPageChoose : switchChoose()      ;break;
            case R.id.userPageKonvert: getDiscusWithUser();break;
        }
    }
    private void switchBan(){
        int status = bwStatus == C_.BW_STATUS_BAN ? C_.BW_STATUS_EMPTY : C_.BW_STATUS_BAN;
        initBwList(status);
    }
    private void switchChoose(){
        int status = bwStatus == C_.BW_STATUS_LIKE ? C_.BW_STATUS_EMPTY : C_.BW_STATUS_LIKE;
        initBwList(status);
    }
    private void initBwList(int status){
        PostSubData subData = new PostSubData();
        subData.setStatus(status);
        subData.setSubjectId(userId);
        Post.sendRequest(this, C_.ACT_SET_BW_STATUS, subData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (data.getBanList() != null)
                    MyStorage.i().putData(C_.VAR_BAN_LIST, data.getBanList());
                else
                    MyStorage.i().putData(C_.VAR_BAN_LIST, "");

                if (data.getLikeList() != null)
                    MyStorage.i().putData(C_.VAR_LIKE_LIST, data.getLikeList());
                else
                    MyStorage.i().putData(C_.VAR_LIKE_LIST, "");
                initBwStatus();
            }
        });
    }
    private void getDiscusWithUser(){
        Usr.i().targetUserId = userId;
        AppMode.i().setMode(C_.APP_MODE_DISCUS_WITH_USER);
        finish();
    }
    void showBigAvatar(String src){
        Funcs.loadImgNecessarily(this, userPageBigImg, src, 20);
        userPageScreen.setVisibility(View.VISIBLE);
    }
    void clickAboutText(){
        if (noClick){
            noClick = false;
            return;
        }
        if(!expand){
            userPageAbout.setMaxLines(500);
            expand = true;
        }
        else {
            userPageAbout.setMaxLines(5);
            expand = false;
        }
    }
    void initUser() {
        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", 0);
        if (userId == 0){
            PUWindow.i().show("Что-то пошло не так");
            finish();
        }else {
            this.userId = userId;
            initUserPage(userId);
        }

    }
    void initUserPage(int userId){
        PostSubData subData = new PostSubData();
        subData.setOwnerId(userId);
        Usr.i().requestUserStatus(this, userId);
        Post.sendRequest(this,C_.ACT_GET_USER_REVIEWS, subData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (result != -1){

                    targetUser = data.getOwner();
                    initUserData(targetUser);
                    if (data.getReview() != null)
                        initSelfReview(data.getReview());

                    if(data.getUserReviews() != null){
                        for (UserReview userReview : data.getUserReviews()){
                            if (userReview.getComment() != null)
                               if (userReview.getComment().length()>0)
                                    userReviews.add(userReview);
                        }    
                        createReviewsRecyclerView(userReviews);
                    }
                }
            }
        });
    }
    private void initBroadcastReceiverListener(){
        ActivityBroadcastReceiver.i().init(this, new ActivityBroadcastReceiver.CB() {
            @Override
            public void cb(int code, Object o) {
                int id = 0;
                if (code == C_.CODE_WS_ONLINE_LIST) {
                    String res = (String) o;
                    try {
                       id = Integer.parseInt(res);
                       setStatus (id == userId);
                    }catch (Exception ignored){

                    }
                }
            }
        });
    }
    void initScreenClickListeners(){
        userPageScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noClick) return;
                userPageScreen.setVisibility(View.GONE);
            }
        });
    }
    void initUserData(User user){
        String voteQt = null;
        String userName = null;


        if(user.getImgIcon() != null)
            Funcs.loadImgNecessarily(this, userPageAvatar, C_.URL_BASE+user.getImgIcon(), 5);

        if (user.getLogin() != null)
            userPageLogin.setText(user.getLogin());

        if (user.getVoteQt() != null){
            voteQt = user.getVoteQt().toString();
            userPageVoteQt.setText(voteQt);
        }

        if (user.getCreateDate() != null)
            userPageRegistration.setText(user.getCreateDate().substring(0, user.getCreateDate().length() - 9));
        else
            userPageRegistration.setText("--:--");

        if(user.getAboutUser() != null)
            userPageAbout.setText(user.getAboutUser());

        if(user.getName() != null){
            userName = user.getName()+" ";
            userPageName.setText(userName);
        }

        if (user.getSName() != null)
            userPageSName.setText(user.getSName());




        if (user.getLastTime() != null){
            lastTime = "заходил  "+user.getLastTime().substring(0, user.getLastTime().length() - 3);
        }

        if (online)
            userPageLastTime.setText("сейчас в сети");
        else
            userPageLastTime.setText(lastTime);

        float mRating = 0.0f;
        if(user.getRating() != null)
            mRating = (float)user.getRating()/100;

        ratingStars = new RatingStars(this , userPageStars, mRating);

        userPageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getImg() != null){
                    showBigAvatar(C_.URL_BASE+user.getImg());
                }
            }
        });
        new Kbrd().hide(this);
        initBwStatus();
    }
    private void initBwStatus(){
        ArrayList<Integer>banList = new ArrayList<>();
        ArrayList<Integer>likeList = new ArrayList<>();
        banList  = Usr.i().getBanList();
        likeList = Usr.i().getLikeList();

        ArrayList<Integer> finalBanList = banList;
        ArrayList<Integer> finalLikeList = likeList;
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userPageBan.setImageResource(R.drawable.no_ban);
                userPageChoose.setImageResource(R.drawable.no_choose);
                bwStatus = C_.BW_STATUS_EMPTY;
                if (finalBanList.contains(userId)){
                    bwStatus = C_.BW_STATUS_BAN;
                    userPageBan.setImageResource(R.drawable.ban);
                }

                if (finalLikeList.contains(userId)){
                    bwStatus = C_.BW_STATUS_LIKE;
                    userPageChoose.setImageResource(R.drawable.choose);
                }
            }
        });
    }
    void initSelfReview(UserReview review){
        if (review.getComment() != null)
            userPageReview.setText(review.getComment());
        if (review.getStar_qt() != null)
            highLightStars(review.getStar_qt());
    }
    void setStatus(boolean status){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (status){
                    userPageOnline.setVisibility(View.VISIBLE);
                    userPageLastTime.setText("сейчас в сети");
                    online = true;
                } else {
                    userPageOnline.setVisibility(View.GONE);
                    online = false;
                    userPageLastTime.setText(lastTime);
                }
            }
        });
    }
    void addReview(){
        if(userPageReview.getText().toString().length() < 1) {
            PUWindow.i().show("Введите текст отзыва");
            return;
        }
        PostSubData data = new PostSubData();
        data.setOwnerId(userId);
        data.setSenderId(Usr.i().getUser().getId());
        data.setTxtReview(userPageReview.getText().toString());
        Post.sendRequest(this,"user_review", data, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (result == -1)return;
                UserReview tmp = null;
                for (UserReview review : userReviews){
                    if (review.getSender_id() != null){
                        tmp = review;
                    }
                }
                if(tmp != null)
                     userReviews.remove(tmp);
                userReviews.add(0, data.getReview());
                createReviewsRecyclerView(userReviews);
                Log.i("MyReview", data.getReview().getComment());
            }
        });
        new Kbrd().hide(this);
    }
    void sendVote(int num){
        Activity activity = this;
        highLightStars(num);
        PostSubData data = new PostSubData();
        data.setOwnerId(userId);
        data.setSenderId(Usr.i().getUser().getId());
        data.setStarQt(num);
        Post.sendRequest(this,"user_review", data, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (result == -1)return;
                if ((data.getIntegerData()) == null || (data.getFloatData() == null))return;
                String voteQt = data.getIntegerData().toString();
                ratingStars.setRating(activity,data.getFloatData()/100f);
                userPageVoteQt.setText(voteQt);
            }
        });
    }
    void highLightStars(int num){
        extinguishStars();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (num){
                    case 1 : {
                        userPageStar1.setImageResource(R.drawable.choose);
                    } break;
                    case 2 : {
                        userPageStar1.setImageResource(R.drawable.choose);
                        userPageStar2.setImageResource(R.drawable.choose);
                    } break;
                    case 3 : {
                        userPageStar1.setImageResource(R.drawable.choose);
                        userPageStar2.setImageResource(R.drawable.choose);
                        userPageStar3.setImageResource(R.drawable.choose);
                    } break;
                    case 4 : {
                        userPageStar1.setImageResource(R.drawable.choose);
                        userPageStar2.setImageResource(R.drawable.choose);
                        userPageStar3.setImageResource(R.drawable.choose);
                        userPageStar4.setImageResource(R.drawable.choose);
                    } break;
                    case 5 : {
                        userPageStar1.setImageResource(R.drawable.choose);
                        userPageStar2.setImageResource(R.drawable.choose);
                        userPageStar3.setImageResource(R.drawable.choose);
                        userPageStar4.setImageResource(R.drawable.choose);
                        userPageStar5.setImageResource(R.drawable.choose);
                    } break;
                }
            }
        });
    }
    void extinguishStars(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userPageStar1.setImageResource(R.drawable.no_choose);
                userPageStar2.setImageResource(R.drawable.no_choose);
                userPageStar3.setImageResource(R.drawable.no_choose);
                userPageStar4.setImageResource(R.drawable.no_choose);
                userPageStar5.setImageResource(R.drawable.no_choose);
            }
        });
    }
    private void createReviewsRecyclerView(@NotNull List<UserReview> reviews){
        int cnt = 0;
        for(UserReview review: reviews){
           String data = "null";
           if(review.getSender_id() != null)data = review.getSender_id().toString();
           Log.i("MyRev", "review №"+cnt+" id -> "+data);
        }
        userReviewsAdapter = null;
        layoutManagerReviews = new LinearLayoutManager(this);
        userReviewsAdapter = new UserReviewsAdapter(reviews, adapterListener, R.layout.frm_user_review);
//        layoutManagerReviews.setReverseLayout(true);
//        layoutManagerReviews.setStackFromEnd(true);
        rcVwUserPage.setLayoutManager(layoutManagerReviews);
        rcVwUserPage.setHasFixedSize(true);
        rcVwUserPage.setAdapter(userReviewsAdapter);
        rcVwUserPage.scrollToPosition(0);
    }
}
