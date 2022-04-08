package com.worksn.objects;

import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;



public class G_ {

    public static long notifyDiscusId = 0;
    public static boolean clickListenerEnable = true;
    public static MyContext context = null;
    public static JsonResponse respData = null;
    public static Map<String, ?> cookie;
    public static String date_time = null;
    public static boolean ads_card_deploy = false;

    public static int viewRecyclerPos = 0;
    public static boolean expandAdsCard = false;
    public static boolean noClick = false;

    public static ArrayList<AssocData> catList = new ArrayList<>();
    public static Integer stopActivityType = 0;

    public static boolean isQuickResponse = false;

    public static int shiftLoadImg = 0;
    private static int permissionStorage;
    public static int getPermissionStorage() {
        return permissionStorage;
    }

    public static void setPermissionStorage(int permissionStorage) {
        G_.permissionStorage = permissionStorage;
    }
}
