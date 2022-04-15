package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.worksn.R;
import com.worksn.classes.MyImg;
import com.worksn.objects.C_;
import com.worksn.objects.User;

public class FrameUserProfile {
    ImageView avatarImg;
    ImageView onlineImg;
    LinearLayout uProfileBcgrnd;

    private final String userLogin;
    private final Integer userId;

    public String getUserLogin() {
        return userLogin;
    }
    public Integer getUserId() {
        return userId;
    }
    public FrameUserProfile(Context context, View parent, User user, CB cb){
        userId = user.getId();
        userLogin = user.getLogin();
        ((Activity)context).runOnUiThread(() -> {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            View v = inflater.inflate(R.layout.user_profile, (ViewGroup) parent, true);
            LinearLayout lSt = v.findViewById(R.id.starsLeft);
            LinearLayout rSt = v.findViewById(R.id.starsRight);
            TextView login = v.findViewById(R.id.uProfileLogin);
            avatarImg = v.findViewById(R.id.uProfileAvatarImg);
            onlineImg = v.findViewById(R.id.uProfileOnline);
            uProfileBcgrnd = v.findViewById(R.id.uProfileBcgrnd);
            LinearLayout.LayoutParams lpStarsLeft   = (LinearLayout.LayoutParams) lSt  .getLayoutParams();
            LinearLayout.LayoutParams lpStarsRight  = (LinearLayout.LayoutParams) rSt  .getLayoutParams();

            login.setText(user.getLogin());

            String src =  user.getImgIcon();
            if (src != null){
                src = C_.URL_BASE + src;
                new MyImg((Activity)context).loadImgNecessarily(avatarImg, src, 5);
            }else{
                avatarImg.setImageResource(R.drawable.no_avatar);
            }
            float rating = 0f;
            if (user.getRating() != null){
                rating = (float)user.getRating()/100;
            }
            lpStarsLeft .weight = rating;
            lpStarsRight.weight = 5-rating;

            if (user.getOnline()){
                onlineImg.setImageResource(R.drawable.online);
            } else {
                onlineImg.setImageResource(R.drawable.empty_100_100);
            }
            v.setOnClickListener(v1 -> cb.callback(user, 1));
        });

    }
    public void setOnline(Activity activity, boolean stt){
        activity.runOnUiThread(() -> {
            if (stt)onlineImg.setImageResource(R.drawable.online);
            else    onlineImg.setImageResource(R.drawable.empty_100_100);
        });

    }
    public void highLight(Activity activity, boolean stt){
        activity.runOnUiThread(() -> {
            try{
                if (stt)
                    uProfileBcgrnd.setVisibility(View.VISIBLE);
                else
                    uProfileBcgrnd.setVisibility(View.GONE);
            }catch (Exception ignored){}
        });
    }
    public interface CB{
        public void callback(User user, Integer result);
    }
}
