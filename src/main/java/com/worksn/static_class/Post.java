package com.worksn.static_class;

import android.content.Context;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.G_;
import com.worksn.objects.JsonResponse;
import com.worksn.objects.PostData;
import com.worksn.singleton.NetworkService;
import com.worksn.singleton.PUWindow;

import static com.worksn.objects.G_.respData;


public class Post {
    public static void sendRequest(Context context, String act, Object sendData, NetCallback cb) {//PostUserData
        PostData postData = new PostData(act, sendData);
        sendData(context, new MyCallBack() {
            @Override
            public void callback(int code, JsonResponse data) {
                if (code == 1) {
                    Integer error  = data.getError();
                    if(error == 0){
                        G_.context = data.getContext();
                        cb.callback(data.getContext(), data.getResult(), data.getData());
                    }else {
                        PUWindow.i().show(data.getResponse());
                    }
                }
                if (code == -1){
                    cb.callback(null, -1, "Проверьте подключение к сети");
                }
            }
            @Override
            public PostData getObject() {
                return postData;
            }
        });
    }

    private static void sendData(Context context, MyCallBack myCallBack){
        G_.clickListenerEnable = false;
        NetworkService.i(context)
                .getWsnApi()
                .getData(myCallBack.getObject())
                .enqueue(new Callback<JsonResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonResponse> call, @NonNull Response<JsonResponse> response) {
                        respData = response.body();
                        G_.clickListenerEnable = true;
                        if(respData != null){
                            myCallBack.callback(1, respData);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<JsonResponse> call, @NonNull Throwable t) {
                        G_.clickListenerEnable = true;
                        myCallBack.callback(-1, null);
                        t.printStackTrace();
                    }

                });
    }

    public interface MyCallBack {
        public void callback(int code, JsonResponse data);
        public PostData getObject();
    }

}







//package com.worksn.static_class;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.squareup.moshi.JsonAdapter;
//import com.squareup.moshi.Moshi;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import com.worksn.objects.C_;
//import com.worksn.interfaces.NetCallback;
//import com.worksn.objects.JsonResponse;
//import com.worksn.objects.PostData;
//
//
//
//public class Post {
//    public static void sendRequest(Context context, String act, Object sendData, NetCallback cb) {//PostUserData
//        PostData postData = new PostData(act, sendData);
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(postData);
//
//        Log.i("MyOkHttp", "send -> "+jsonString);
//
//        OkHttpClient client = new OkHttpClient();
//        Moshi moshi = new Moshi.Builder().build();
//        JsonAdapter<JsonResponse> jsonAdapter = moshi.adapter(JsonResponse.class);
//        RequestBody formBody = RequestBody.create(
//                MediaType.parse("application/json"), jsonString);
//
//        Request request = new Request.Builder()
//                .url(C_.URL_BASE)
//                .post(formBody)
//                .build();
//
//        Call call = client.newCall(request);
//
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//
//                super.run();
//                Response response = null;
//                try {
//                    response = call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    cb.callback(null, -1, "Проверьте подключение к сети");
//                }
//                if (response.isSuccessful()){
//                    Log.i("MyOkHttp", "receive -> "+response.body().source());
//                    try {
//                        JsonResponse r = jsonAdapter.fromJson(response.body().source());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        cb.callback(null, -1, "Проверьте подключение к сети");
//                    }
//                }else {
//                    Log.i("MyOkHttp", "ERROR Unexpected code " + response);
//                    cb.callback(null, -1, "Проверьте подключение к сети");
//                }
//            }
//        };
//        thread.start();
//    }
//}