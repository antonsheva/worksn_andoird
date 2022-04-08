package com.worksn.classes;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;


import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import com.worksn.R;
import com.worksn.activity.MyApp;
import com.worksn.objects.TmpImg;
import com.worksn.singleton.PUWindow;
import com.worksn.view.FrameProgressbar;

public class ProgressRequestBody extends RequestBody {
    private final File mFile;
    public final Context context;


    private static final int DEFAULT_BUFFER_SIZE = 5*1024;

    public ProgressRequestBody(Context context, final File file) {
        this.context = context;
        mFile = file;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }

    @Override
    public void writeTo(@NotNull BufferedSink sink) throws IOException {
        long fileLength = mFile.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        final int[] cnt = {0};
        try (FileInputStream in = new FileInputStream(mFile)) {
            long uploaded = 0;
            int read;

            FrameProgressbar progressbar = new FrameProgressbar((Activity)context);
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer)) != -1) {
                int val = (int)(100 * uploaded / fileLength);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressbar.setPos(val);
                    }
                }, 10);
                uploaded += read;
                sink.write(buffer, 0, read);
            }
        }
    }
}
