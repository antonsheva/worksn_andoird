package com.worksn.interfaces;

import com.worksn.objects.C_;
import com.worksn.objects.JsonResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadFile {
    @Multipart
    @POST("/")
    Call<JsonResponse> uploadFile(
            @Part(C_.STR_ACT) RequestBody act,
            @Part(C_.STR_CREATE_ID) RequestBody create_id,
            @Part(C_.STR_WS_TOKEN) RequestBody s_token,
            @Part(C_.STR_MAX_FILE_SIZE) RequestBody MAX_FILE_SIZE,
            @Part(C_.STR_FILE_NAME) RequestBody name,
            @Part MultipartBody.Part file
    );
}
