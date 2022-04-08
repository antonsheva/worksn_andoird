package com.worksn.cookie; // Original written by tsuharesu
// Adapted to create a "drop it in and watch it work" approach by Nikhil Jha.
// Just add your package statement and drop it in the folder with all your other worksn.classes.

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.worksn.classes.PrefStorageCookie;
import com.worksn.objects.G_;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This interceptor put all the Cookies in Preferences in the Request.
 * Your implementation on how to get the Preferences may ary, but this will work 99% of the time.
 */
public class AddCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";
    // We're storing our stuff in a database made just for cookies called PREF_COOKIES.
    // I reccomend you do this, and don't change this default value.
    private Context context;

    public AddCookiesInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
//        Log.i("MyCookie","------------------------------------");
        G_.cookie = PrefStorageCookie.getAllProperty();
        StringBuilder val= new StringBuilder();
        String tmp = "";
        for(Map.Entry<String, ?>entry : G_.cookie.entrySet()){
            tmp =  (String)entry.getValue();
            Log.i("MyCookei", tmp);
            if (!tmp.contains("deleted")){
                val.append((String) entry.getValue()).append("; ");
            }
        }
        Log.i("MyCookei", "++++++++++++++++++++++++++++");
        Log.i("MyCookei", val.toString());
        Log.i("MyCookei", "============================");
        builder.addHeader("Cookie", val.toString());
//        Log.i("MyCookie",val);
//        Log.i("MyCookie","------------------------------------");

        return chain.proceed(builder.build());
    }
}