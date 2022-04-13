package com.worksn.classes;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
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
        try (FileInputStream in = new FileInputStream(mFile)) {
            long uploaded = 0;
            int read;

            FrameProgressbar progressbar = new FrameProgressbar((Activity)context);
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer)) != -1) {
                int val = (int)(100 * uploaded / fileLength);
                handler.postDelayed(() -> progressbar.setPos(val), 10);
                uploaded += read;
                sink.write(buffer, 0, read);
            }
        }
    }
}
