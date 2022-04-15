package com.worksn.classes;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.worksn.activity.MyApp;

public class Kbrd {
    public void hide(Activity context){
        InputMethodManager inputManager =
                (InputMethodManager) context.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        try{
            inputManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException e){
            Log.e("MyErr", "hideKeyboard");
        }
    }
}
