package com.worksn.activity;

import android.app.Application;
import android.content.Context;

//import worksn.classes.TryMe;

public class MyApp extends Application {
    public static Context context;
    @Override
    public void onCreate() {

//        Thread.setDefaultUncaughtExceptionHandler(new TryMe());
        super.onCreate();
//        Context context = getApplicationContext();
        context = this;
    }

}
