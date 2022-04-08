package com.worksn.classes;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;
import com.worksn.R;
import com.worksn.objects.AdsCllct;
import com.worksn.objects.C_;
import com.worksn.singleton.MyAds;

import static com.yandex.runtime.Runtime.getApplicationContext;

public class ButtonsHighLight {
    Activity activity;

    androidx.appcompat.widget.AppCompatButton btAdsTypeWorker;
    androidx.appcompat.widget.AppCompatButton btAdsTypeEmployer;
    androidx.appcompat.widget.AppCompatButton btAdsParamCategory;
    androidx.appcompat.widget.AppCompatButton btAdsParamAdd;
    androidx.appcompat.widget.AppCompatButton btAdsParamUser;
    FrameLayout                               addAdsLoadImgsIcon;

    public ButtonsHighLight(Activity activity){
        this.activity = activity;
        initViewElements();
    }

    public void setButtonColor(androidx.appcompat.widget.AppCompatButton button, int style){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Drawable stl = ContextCompat.getDrawable(getApplicationContext(), style);
                button.setBackground(stl);
            }
        });
    }
    public void setAdsTypeBtColor(AdsCllct adsCllct){
        switch (adsCllct.getAdsType()){
            case C_.ADS_TYPE_EMPLOYER :
                setButtonColor(btAdsTypeWorker,  R.drawable.no_active_btn);
                setButtonColor(btAdsTypeEmployer,  R.drawable.active_btn);
                break;
            case C_.ADS_TYPE_WORKER   :
                setButtonColor(btAdsTypeWorker,  R.drawable.active_btn);
                setButtonColor(btAdsTypeEmployer,  R.drawable.no_active_btn);
                break;
            default: setButtonColor(btAdsTypeWorker,  R.drawable.no_active_btn);
                setButtonColor(btAdsTypeEmployer,  R.drawable.no_active_btn);
        }
        Log.i("MyCat", "catName -> "+adsCllct.getCatName());
        btAdsParamCategory.setText(adsCllct.getCatName());
    }
    public void setAdsParamBtColor(int type){
        clearAdsParamBtColor();
//        btAdsParamAdd.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        btAdsParamAdd.setText("Добавить");
        switch (type){
            case C_.ADS_PARAM_CATEGORY :
                setButtonColor(btAdsParamCategory, R.drawable.active_btn);
                break;
            case C_.ADS_PARAM_USER     :
                setButtonColor(btAdsParamUser    , R.drawable.active_btn);
                break;
            case C_.ADS_PARAM_ADD      :
                setButtonColor(btAdsParamAdd     , R.drawable.active_btn);
//                btAdsParamAdd.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                btAdsParamAdd.setText("Опубликовать");
                break;
        }
    }

    public void clearAdsParamBtColor() {
        setButtonColor(btAdsParamCategory, R.drawable.no_active_btn);
        setButtonColor(btAdsParamUser    , R.drawable.no_active_btn);
        setButtonColor(btAdsParamAdd     , R.drawable.no_active_btn);
        btAdsParamAdd.setText("Добавить");
    }

    private void initViewElements(){
        btAdsTypeWorker     = (androidx.appcompat.widget.AppCompatButton)activity.findViewById(R.id.btAdsTypeWorker   );
        btAdsTypeEmployer   = (androidx.appcompat.widget.AppCompatButton)activity.findViewById(R.id.btAdsTypeEmployer );
        btAdsParamCategory  = (androidx.appcompat.widget.AppCompatButton)activity.findViewById(R.id.btAdsParamCategory);
        btAdsParamAdd       = (androidx.appcompat.widget.AppCompatButton)activity.findViewById(R.id.btAdsParamAdd     );
        btAdsParamUser      = (androidx.appcompat.widget.AppCompatButton)activity.findViewById(R.id.btAdsParamUser    );
        addAdsLoadImgsIcon  = (FrameLayout)                              activity.findViewById(R.id.addAdsLoadImgsIcon);
    }
}
