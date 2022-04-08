package com.worksn.singleton;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.Date;

public class MyStorage {
    private static MyStorage i;
    public static MyStorage i(){
        if (i == null){
            i = new MyStorage();
        }
        return i;
    }
    SharedPreferences preferences = null;
    SharedPreferences.Editor editor;
    public String getApplicationId(){
        Long time;
        Double rand;
        String applicationId = preferences.getString("app_id",null);
//        Log.i("MyStorage1", "start app_id -> "+applicationId);
        if (applicationId == null){
            time = new Date().getTime();
            rand = Math.random();
            applicationId = time.toString().substring(10)+rand.toString().substring(10);
            put("app_id", applicationId);
            editor.apply();
        }
//        Log.i("MyStorage1", "final app_id -> "+applicationId);
        return applicationId;
    }
    public void init(Context context){
//        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences = context.getSharedPreferences("worksn.preference", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.apply();
    }
    public void clearField(String key){
        preferences.edit().remove(key).apply();
    }

    public void putData(String key, Object data){
        if (data instanceof String)
            put(key, (String)data);
        if (data instanceof Integer)
            put(key, (Integer) data);
        if (data instanceof Boolean)
            put(key, (Boolean) data);
        if (data instanceof Long)
            put(key, (Long) data);
        editor.apply();
    }

    public String getString(String key){
        String data = preferences.getString(key, null);
//        Log.i("MyStorage", "get "+key+" -> "+data);
        return data;
    }
    public Integer getInt(String key){
        Integer data = preferences.getInt(key, 0);
//        Log.i("MyStorage", "get "+key+" -> "+data.toString());
        return data;
    }
    public Boolean getBoolen(String key){
        Boolean data = preferences.getBoolean(key, false);
//        Log.i("MyStorage", "get "+key+" -> "+data.toString());
        return data;
    }
    public Float getFloat(String key){
        Float data = preferences.getFloat(key, 0);
//        Log.i("MyStorage", "get "+key+" -> "+data.toString());
        return data;
    }
    public Long getLong(String key){
        Long data = preferences.getLong(key, 0);
//        Log.i("MyStorage", "get "+key+" -> "+data.toString());
        return data;
    }

    private void put(String key, String data){
        editor.putString(key, data);
    }
    private void put(String key, Integer data){
        editor.putInt(key, data);
    }
    private void put(String key, Boolean data){
        editor.putBoolean(key, data);
    }
    private void put(String key, Long data){
        editor.putLong(key, data);
    }
}
