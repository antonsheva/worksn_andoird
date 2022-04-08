package com.worksn.websocket.init_ssl;



import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadFile {
    @Multipart
    @POST("/")
    Call<NetResponse> uploadFile(
            @Part("act") RequestBody act,
            @Part("s_token") RequestBody s_token,
            @Part("MAX_FILE_SIZE") RequestBody MAX_FILE_SIZE,
            @Part("filename") RequestBody name,
            @Part MultipartBody.Part file
    );
}
