package com.worksn.cookie; // Original written by tsuharesu
// Adapted to create a "drop it in and watch it work" approach by Nikhil Jha.
// Just add your package statement and drop it in the folder with all your other worksn.classes.

import android.content.Context;


import androidx.annotation.NonNull;

import java.io.IOException;

import com.worksn.classes.PrefStorageCookie;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    public ReceivedCookiesInterceptor() {
    } // AddCookiesInterceptor()
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            String tmp;
            for(String header : originalResponse.headers("Set-Cookie")) {
                tmp = header.split("=")[0];
                PrefStorageCookie.addProperty(tmp, header);
            }
        }

        return originalResponse;
    }
}