package com.worksn.websocket.init_ssl;

import com.worksn.objects.C_;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetService {
    private static NetService mInstance;
    private final Retrofit mRetrofit;

    private NetService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder client = new OkHttpClient.Builder();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(C_.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }
    public static NetService getInstance() {
        if (mInstance == null) {
            mInstance = new NetService();
        }
        return mInstance;
    }
    public ApiConfig getResponse(){
        return mRetrofit.create(ApiConfig.class);
    }
    public JsonWsnApi getWsnApi(){
        return mRetrofit.create(JsonWsnApi.class);
    }
}
