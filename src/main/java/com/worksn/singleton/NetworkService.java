package com.worksn.singleton;


import android.content.Context;
import android.util.Log;

//import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;

import com.worksn.cookie.AddCookiesInterceptor;
import com.worksn.cookie.ReceivedCookiesInterceptor;
import com.worksn.interfaces.ApiConfig;
import com.worksn.interfaces.JsonWsnApi;
import com.worksn.interfaces.UploadFile;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.connection.RealCall;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkService{
    private static NetworkService mInstance;
    private static final String URL = "https://worksn.ru";
    private final Retrofit mRetrofit;

    OkHttpClient client;
    private NetworkService(Context context) {
//        String url = "wss://worksn.ru:8000";
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation()
                                .create()
                ))
                .client(httpClient.build())
                .build();

        client = httpClient.build();
    }

    public void cancelRequest(){
        Log.i("MyPost", "cancelRequest");
        client.dispatcher().cancelAll();
    }

    public static NetworkService i(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkService(context);
        }
        return mInstance;
    }

    public UploadFile getUploadFile(){
        return mRetrofit.create(UploadFile.class);
    }

    public ApiConfig getResponse(){
        return mRetrofit.create(ApiConfig.class);
    }

    public JsonWsnApi getWsnApi(){
        return mRetrofit.create(JsonWsnApi.class);
    }


}
