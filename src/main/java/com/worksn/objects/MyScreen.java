package com.worksn.objects;

import android.app.Activity;
import android.view.Display;

public class MyScreen {
    public static int sizePxX;
    public static int sizePxY;
    public static int sizeDpX;
    public static int sizeDpY;


    public static int activeMode;
    public static int screenMode;

    public static int prevActive;
    public static int prevMode;
    public static void init(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        android.graphics.Point size = new android.graphics.Point();
        display.getSize(size);
        sizePxX = size.x;
        sizePxY = size.y;
        sizeDpX = (int)(sizePxX / activity.getResources().getDisplayMetrics().density);
        sizeDpY = (int)(sizePxY / activity.getResources().getDisplayMetrics().density);



         activeMode = C_.ACTIVE_SCREEN_MSG_GROUP;
         screenMode = C_.SCREEN_MODE_MAIN;

         prevActive = -1;
         prevMode = -1;
    }
}
