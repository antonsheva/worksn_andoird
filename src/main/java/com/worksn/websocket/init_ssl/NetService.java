package com.worksn.websocket.init_ssl;


import android.content.Context;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetService {
    private static NetService mInstance;
    private static final String URL = "https://worksn.ru";
    private final Retrofit mRetrofit;

    private NetService(Context context) {
//        String url = "wss://worksn.ru:8000";
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder client = new OkHttpClient.Builder();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }



    public static NetService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetService(context);
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
