package com.worksn.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.worksn.R;
import com.worksn.activity.FileChooser;
import com.worksn.activity.MakePhoto;
import com.worksn.interfaces.ComCallback;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.JsonResponse;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostSubData;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.NetworkService;
import com.worksn.singleton.PUWindow;
import com.worksn.singleton.Usr;
import com.worksn.static_class.GetPermission;
import com.worksn.static_class.Post;
import com.worksn.view.FrameProgressbar;
import static com.worksn.objects.G_.stopActivityType;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class MyFile{
    private static final String FILE_SIZE = "5242880";
    Context context;
    public MyFile(){}
    public MyFile(Context context){
        this.context = context;
    }
    public void chooseFile(Context context, Integer action){
        GetPermission.storage(context);
        if(G_.getPermissionStorage() == 0){
            PUWindow.i().show(R.string.grantAccessToStorage);
            return;
        }

        stopActivityType = 1;
        Intent intent = new Intent(context, FileChooser.class);
        intent.putExtra(C_.STR_ACTION_TYPE, action);
        ((Activity)context).startActivityForResult(intent, C_.PICK_IMAGE_REQUEST);
    }
    public void makePhoto(Context context, Integer action){
        GetPermission.storage(context);
        if(G_.getPermissionStorage() == 0){
            PUWindow.i().show(R.string.grantAccessToStorage);
            return;
        }
        stopActivityType = 1;
        Intent intent = new Intent(context, MakePhoto.class);
        intent.putExtra(C_.STR_ACTION_TYPE, action);
        ((Activity)context).startActivityForResult(intent, C_.PICK_IMAGE_REQUEST);
    }
    public void uploadNewAvatar(Context context, ImageView imageView, int requestCode, int resultCode, @Nullable Intent data, CB cb){
        MyImg myImg = new MyImg((Activity) context);
        if ((resultCode != RESULT_OK) || (data == null)){
            PUWindow.i().show(R.string.errorSendFile);
            return;
        }
        if (requestCode == C_.PICK_IMAGE_REQUEST) {
            myImg.setTmpAvatar(TmpImg.icon);
            uploadFile(context, TmpImg.img, TmpImg.createId, true, new ComCallback() {
                @Override
                public Object callback(Object object, Integer result) {
                    if (result == RESULT_OK){
                        MyContext response = (MyContext)object;
                        TmpImg.imgSend     = response.getTmpImg();
                        TmpImg.imgIconSend = response.getTmpImgIcon();
                        myImg.setNewAvatar(C_.URL_BASE + TmpImg.imgIconSend);
                    }else {
                        TmpImg.imgSend     = null;
                        TmpImg.imgIconSend = null;
                        PUWindow.i().show(R.string.errorSendFile);
                    }
                    cb.callback(result);
                    return null;
                }
            });
        }else{
            PUWindow.i().show(R.string.errorSendFile);
        }
    }
    public void uploadFile(Context context, File file, String createId, boolean showBtCancel, ComCallback comCallback){
        FrameProgressbar progressbar = new FrameProgressbar((Activity) context);
        TmpImg.sendImgIsRun = true;
        final Timer tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                progressbar.hide();
                new PUWindow().show(R.string.errorSendFile);
                comCallback.callback(null, RESULT_CANCELED);

            }
        }, 30000);


        if (showBtCancel)progressbar.showFull();
        else             progressbar.show();

        String mToken = "XXXXXXXXXX";
        if (Usr.i().getUser() != null)mToken = Usr.i().getUser().getWsToken();

        ProgressRequestBody fileBody    = new ProgressRequestBody(context, file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("userfile", file.getName(), fileBody);
        RequestBody filename            = RequestBody.create(file.getName(), MediaType.parse("text/plain"));
        RequestBody act                 = RequestBody.create( C_.ACT_ADD_IMG,MediaType.parse("text/plain"));
        RequestBody create_id           = RequestBody.create(createId, MediaType.parse("text/plain"));
        RequestBody s_token             = RequestBody.create(mToken, MediaType.parse("text/plain"));
        RequestBody MAX_FILE_SIZE       = RequestBody.create(FILE_SIZE, MediaType.parse("text/plain"));

        NetworkService.i()
                .getUploadFile()
                .uploadFile(act, create_id, s_token, MAX_FILE_SIZE, filename, fileToUpload)
                .enqueue(new Callback<JsonResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonResponse> call, @NonNull Response<JsonResponse> response) {
                        tm.cancel();
                        G_.respData = response.body();
                        if (G_.respData != null){
                            if (G_.respData.getError() == 0){
                                File deleteFile = TmpImg.img;
                                if(deleteFile != null && deleteFile.exists()){
                                    if(deleteFile.delete()){
                                        Log.d("Deleted",  TmpImg.img.getAbsolutePath() +" -> ok");
                                    }
                                }
                                deleteFile= TmpImg.icon;
                                if(deleteFile != null && deleteFile.exists()){
                                    if(deleteFile.delete()){
                                        Log.d("Deleted",  TmpImg.icon.getAbsolutePath()+" -> ok");
                                    }
                                }
                                comCallback.callback(G_.respData.getContext(), RESULT_OK);
                            }
                        } else {
                            PUWindow.i().show(G_.respData.getData());
                            comCallback.callback(null, RESULT_CANCELED);
                        }
                        progressbar.hide();
                    }
                    @Override
                    public void onFailure(@NonNull Call<JsonResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
    public void removeTmpFile(String imgPath){
        PostSubData data = null;
        if (imgPath != null) {
            data = new PostSubData();
            data.setFileName(imgPath);
        }
        Post.sendRequest(context, C_.ACT_REMOVE_TMP_FILE, data, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                TmpImg.clear();
            }
        });
    }
    public interface CB{
        void callback(int result);
    }
}
