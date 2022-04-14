package com.worksn.singleton;

import android.content.Context;
import android.content.SharedPreferences;
import com.worksn.objects.C_;
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
        String applicationId = preferences.getString(C_.STR_APP_ID,null);
        if (applicationId == null){
            time = new Date().getTime();
            rand = Math.random();
            applicationId = time.toString().substring(10)+rand.toString().substring(10);
            put(C_.STR_APP_ID, applicationId);
            editor.apply();
        }
        return applicationId;
    }
    public void init(Context context){
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
        return preferences.getString(key, null);
    }
    public Integer getInt(String key){
        return preferences.getInt(key, 0);
    }
    public Boolean getBoolen(String key){
        return preferences.getBoolean(key, false);
    }
    public Float getFloat(String key){
        return preferences.getFloat(key, 0);
    }
    public Long getLong(String key){
        return preferences.getLong(key, 0);
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
