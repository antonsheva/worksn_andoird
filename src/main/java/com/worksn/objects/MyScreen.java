package com.worksn.objects;

import android.app.Activity;
import android.view.Display;

public class MyScreen {
    public static int sizePxX;
    public static int sizePxY;
    public static int sizeDpX;
    public static int sizeDpY;


    public static int active_mode;
    public static int screen_mode;

    public static int prev_active;
    public static int prev_mode  ;
    public static void init(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        android.graphics.Point size = new android.graphics.Point();
        display.getSize(size);
        sizePxX = size.x;
        sizePxY = size.y;
        sizeDpX = (int)(sizePxX / activity.getResources().getDisplayMetrics().density);
        sizeDpY = (int)(sizePxY / activity.getResources().getDisplayMetrics().density);



         active_mode = C_.ACTIVE_SCREEN_MSG_GROUP;
         screen_mode = C_.SCREEN_MODE_MAIN;

         prev_active = -1;
         prev_mode   = -1;
    }
}
