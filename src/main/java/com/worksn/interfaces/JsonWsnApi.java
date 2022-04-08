package com.worksn.interfaces;


import com.worksn.objects.JsonResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonWsnApi {
    //    "Content-Type: application/x-www-form-urlencoded; charset=UTF-8",
    @POST("/")
    public Call<JsonResponse> getData(@Body Object data);

}
