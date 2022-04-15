package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.worksn.R;

public class RatingStars {
    LinearLayout.LayoutParams lpStarsLeft;
    LinearLayout.LayoutParams lpStarsRight;
    View v;
    LinearLayout lSt;
    LinearLayout rSt;
    public RatingStars(Context context, View parent, float rating){

                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                v = inflater.inflate(R.layout.stars, (ViewGroup) parent, true);
                lSt = v.findViewById(R.id.starsLeft);
                rSt = v.findViewById(R.id.starsRight);

                lpStarsLeft   = (LinearLayout.LayoutParams) lSt .getLayoutParams();
                lpStarsRight  = (LinearLayout.LayoutParams) rSt .getLayoutParams();
                setRating((Activity)context, rating);
    }
    public void setRating(Activity activity, Float rating){
        activity.runOnUiThread(() -> {
            if (rating == null) return;
            lpStarsLeft .weight = rating;
            lpStarsRight.weight = 5-rating;
            lSt.setLayoutParams(lpStarsLeft);
            rSt.setLayoutParams(lpStarsRight);
        });
    }
}
