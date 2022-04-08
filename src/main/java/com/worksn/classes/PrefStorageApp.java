package com.worksn.classes;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefStorageApp {
    public static final String STORAGE_NAME = "StorageApp";
    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;
    public static void init( Context cntxt ){
        context = cntxt;
    }

//    private static void initSocket(){
//        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
//        editor = settings.edit();
//    }
//
//    public static void addProperty( String name, String value ){
//        if( settings == null ){
//            initSocket();
//        }
//        editor.putString( name, value );
//        editor.commit();
//    }
//
//    public static String getProperty( String name ){
//        if( settings == null ){
//            initSocket();
//        }
//        return settings.getString( name, null );
//    }
//    public static Map<String, ?> getAllProperty(){
//        if( settings == null ){
//            initSocket();
//        }
//        return settings.getAll();
//    }
}