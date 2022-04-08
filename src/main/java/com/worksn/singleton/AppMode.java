package com.worksn.singleton;

import android.util.Log;

import com.worksn.objects.C_;

public class AppMode {
    private static AppMode i;
    public static AppMode i(){
        if (i == null){
            i = new AppMode();
        }
        return i;
    }
    private Integer mode = null;
    private Integer page = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMode(int qt) {
        modeCnt++;
        if (modeCnt > qt) {
            modeCnt = 0;
            mode = C_.APP_MODE_MAIN;
        }
        if (mode == null) mode = 0;
        Log.i("MyAppMode", "cnt  -> "+modeCnt);
        Log.i("MyAppMode", "getMode - mode -> "+mode);

        return mode;
    }
    public Integer getMode(){
        if (mode == null) mode = 0;
        return mode;
    }
    public void setMode(Integer mode) {
        this.mode = mode;
        if (this.mode == null)this.mode = C_.APP_MODE_MAIN;
        modeCnt = 0;
        Log.i("MyAppMode", "setMode - mode -> "+mode);
    }

    private Integer modeCnt = 0;
}
