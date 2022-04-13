package com.worksn.classes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.worksn.R;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class FollowLink {
    public FollowLink(Activity activity, String url){
        Uri uri;
        try{
            new URL(url);
            uri = Uri.parse(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(uri);
        activity.startActivity(i);
    }
}
