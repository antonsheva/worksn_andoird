package com.worksn.singleton;

public class AppEnvironment {
    private static AppEnvironment i;
    public static AppEnvironment i(){
        if (i == null) i = new AppEnvironment();
        return i;
    }

    public void getData(){

    }



}
