package com.worksn.objects;

import java.util.ArrayList;
import java.util.Map;

public class G_ {
    public static long notifyDiscusId = 0;
    public static boolean clickListenerEnable = true;
    public static MyContext context = null;

    public static Map<String, ?> cookie;
    public static boolean adsCardDeploy = false;
    public static boolean expandAdsCard = false;
    public static boolean noClick = false;
    public static ArrayList<AssocData> catList = new ArrayList<>();
    public static Integer stopActivityType = 0;
    public static boolean isQuickResponse = false;
    private static int permissionStorage;
    public static int getPermissionStorage() {
        return permissionStorage;
    }
    public static void setPermissionStorage(int permissionStorage) {
        G_.permissionStorage = permissionStorage;
    }
}
