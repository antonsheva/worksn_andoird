package com.worksn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.worksn.objects.PostSubData;
import com.worksn.static_class.Post;

public class CrashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyException", "try send data to server");
        Intent intent = getIntent();
        String sendData = intent.getStringExtra("crash_data");
        Log.i("MyException", "sen data -> " + sendData);
        PostSubData subData = new PostSubData();
        subData.setContent(sendData.toString());
        Post.sendRequest(getApplicationContext(),"send_exception", subData, (data, result, stringData)->{
            if (result == 1){
                Log.d("MyException", "exception was send to server");
            }
        });
        System.exit(0);
    }
}
