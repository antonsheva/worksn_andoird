package com.worksn.singleton;

import android.content.Context;
import android.content.Intent;

import com.worksn.activity.SettingActivity;
import com.worksn.activity.ActivityUserPage;

public class UiClick {
    private static UiClick i;
    public static UiClick i(){
        if (i == null) i = new UiClick();
        return i;
    }
    public void showUserPage(Context context, Integer userId){
        if (userId == null)userId = 1;
        Intent intent = new Intent(context, ActivityUserPage.class);
        intent.putExtra("user_id", userId);
        context.startActivity(intent);
    }
    public void showSettingPage(Context context){
        if (Usr.i().getUser() == null || Usr.i().getUser().getId() == null)return;
        int userId = Usr.i().getUser().getId();
        Intent intent = new Intent(context, SettingActivity.class);
        intent.putExtra("user_id", userId);
        context.startActivity(intent);
    }
}
