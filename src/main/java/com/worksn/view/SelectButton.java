package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.worksn.R;


public class SelectButton {
    public SelectButton(Context context, View parent, String name, Object val, CB cb) {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View v = inflater.inflate(R.layout.select_button, (ViewGroup) parent, true);
                TextView txt = v.findViewById(R.id.selectButtnoName);
                txt.setText(name);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cb.callback(name, val);
                    }
                });
            }
        });
    }
    public interface CB{
        void callback(String name, Object val);
    }

}
