package com.worksn.classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.worksn.R;

public class MyHref {
    public MyHref(Activity activity, View parent, String title, String url){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = activity.getLayoutInflater();
                View v = inflater.inflate(R.layout.my_href, (ViewGroup) parent, true);
                TextView hrefTitle   = v.findViewById(R.id.hrefTitle);
                hrefTitle.setText(title);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new FollowLink(activity, url);
                    }
                });
            }
        });
    }
}
