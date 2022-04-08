package com.worksn.websocket.init_ssl;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonWsnApi {
    //    "Content-Type: application/x-www-form-urlencoded; charset=UTF-8",
    @POST("/")
    public Call<NetResponse> getData(@Body Object data);

}
