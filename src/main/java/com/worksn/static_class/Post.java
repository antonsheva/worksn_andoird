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
        NetworkService.i()
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
