package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.worksn.R;
import com.worksn.objects.StructTxtData;
import com.worksn.objects.SysNotify;

public class FrameNotify {

    public FrameNotify(Context context, View parent, SysNotify data){
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View v = inflater.inflate(R.layout.frm_notify, (ViewGroup) parent, true);
                TextView frmNotifyDate    = v.findViewById(R.id.frmNotifyDate);
                TextView frmNotifyContent = v.findViewById(R.id.frmNotifyContent);
                String date    = data.getCreateDate();
                String content = data.getContent();
                frmNotifyDate.setText(date);
                frmNotifyContent.setText(content);
            }
        });
    }
}
