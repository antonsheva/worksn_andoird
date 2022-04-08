package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.worksn.R;
import com.worksn.objects.StructTxtData;

public class FrameSlideNote {
    private static boolean signState = false;
    public FrameSlideNote(Context context, View parent, StructTxtData data){

        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View v = inflater.inflate(R.layout.frm_slide_note, (ViewGroup) parent, true);
                LinearLayout noteContent   = v.findViewById(R.id.noteContent);
                ImageView  sign            = v.findViewById(R.id.sign);
                TextView   noteName        = v.findViewById(R.id.noteName);
                TextView noteContentTxt    = v.findViewById(R.id.noteContentTxt);
                String name        = data.getName().replace("&quot;", "\"");
                String description = data.getDescription().replace("&quot;", "\"");

                noteName.setText(name);
                noteContentTxt.setText(description);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (signState){
                                    sign.setImageResource(R.drawable.submenu);
                                    noteContent.setVisibility(View.GONE);
                                }else{
                                    sign.setImageResource(R.drawable.submenu_open);
                                    noteContent.setVisibility(View.VISIBLE);
                                }
                                signState = !signState;
                            }
                        });
                    }
                });
            }
        });

    }


}
