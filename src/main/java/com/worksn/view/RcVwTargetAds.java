package com.worksn.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.worksn.R;
import com.worksn.adapters.TargetAdsAdapter;
import com.worksn.interfaces.AdapterListener;
import com.worksn.objects.Ads;
import com.worksn.objects.C_;
import com.worksn.singleton.Usr;

public class RcVwTargetAds {


    RecyclerView rcVwTargetAds;
    TargetAdsAdapter targetAdsAdapter;
    AdapterListener adapterListener;
    Activity activity;
    LinearLayoutManager layoutManager;
    private List<Ads> adsList = null;
    public RcVwTargetAds(Activity activ, AdapterListener adapterLstnr){
        activity = activ;
        adapterListener = adapterLstnr;
        rcVwTargetAds = (RecyclerView)activity.findViewById(R.id.rcVwTargetAds);
    }
    public void createTargetAdsRecyclerView(Context context, List<Ads> ads, Integer position) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adsList = ads;
                targetAdsAdapter = null;
                layoutManager = new LinearLayoutManager(activity);
                targetAdsAdapter = new TargetAdsAdapter(adsList.size(), adsList, R.layout.frm_ads_card,
                        (eCode, data1, data2) -> adapterListener.event(eCode, data1, data2));
                layoutManager.setReverseLayout(true);
                rcVwTargetAds.setLayoutManager(layoutManager);
                rcVwTargetAds.setAdapter(targetAdsAdapter);

                if (position != null){
                    rcVwTargetAds.scrollToPosition(position);
                }else{
                    if (adsList.size()>0)
                        rcVwTargetAds.scrollToPosition(adsList.size()-1);

                }

                requesOnlinetStatus(context);
            }
        });
    }

    private static boolean sRequestStatusTimeout = false;
    static Timer tm;
    private void requesOnlinetStatus(Context context){
        if (sRequestStatusTimeout)return;
        sRequestStatusTimeout = true;
        if (tm != null){
            tm.cancel();
            tm = null;
        }
        tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                sRequestStatusTimeout = false;
            }
        }, 3000);
        Usr.i().requestUsersStatus(context, false);
    }
    public void setOnlineStatus(){
        renderOnlineStatus(Usr.i().onlineList);
        for (Ads ads : adsList){
            if (Usr.i().onlineList.contains(ads.getUserId()))ads.setUserOnline(true);
        }
    }
    private void renderOnlineStatus(List<Integer> idList){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int fst, lst;
                View v = null;
                fst = layoutManager.findFirstVisibleItemPosition();
                lst = layoutManager.findLastVisibleItemPosition();
                try{
                    for (int i = fst; i <= lst; i++){
                        Ads ads = adsList.get(i);
                        v = layoutManager.findViewByPosition(i);
                        ImageView online =  (ImageView)v.findViewById(R.id.uProfileOnline);
                        if (idList.contains(ads.getUserId())){
                            online.setImageResource(R.drawable.online);
                        }else {
                            online.setImageResource(R.drawable.empty_100_100);
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void renderAdsStatus(Ads ads1){
        Log.i("MyAdsStatus", "id -> "+ads1.getId());
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int fst, lst;
                View v = null;
                fst = layoutManager.findFirstVisibleItemPosition();
                lst = layoutManager.findLastVisibleItemPosition();

                try{
                    for (int i = fst; i <= lst; i++){
                        Ads ads = adsList.get(i);
                        v = layoutManager.findViewByPosition(i);
                        if(v == null) return;

                        if (ads.getId().equals(ads1.getId())){
                            setVisibleMode(v, ads1.getVisibleMode(), ads1.getDescription());
//                            frmAdsCard.setBackgroundColor(activity.getResources().getColor(R.color.ads_visible_hidden_for_time, null));
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void setVisibleMode(View v, int mode, String outDescription){
        int len;
        if(outDescription!=null) {
            len = outDescription.length();
            outDescription = (len>120) ? outDescription.substring(0,116)+"..." : outDescription;
        }else outDescription = "Что-то очень полезное";

        LinearLayout frmAdsCard = (LinearLayout)v.findViewById(R.id.frmAdsCard);
        TextView     frmAdsCardDescription = (TextView)v.findViewById(R.id.frmAdsCardDescription);
        switch (mode){
            case C_.ADS_VISIBLE_NORMAL:
                frmAdsCard.setBackgroundColor(activity.getResources().getColor(R.color.main_background, null));
                break;
            case C_.ADS_VISIBLE_HIDDEN_FOR_TIME:
                frmAdsCard.setBackgroundColor(activity.getResources().getColor(R.color.ads_visible_hidden_for_time, null));
                outDescription = "Сообщение скрыто по времени";
                break;
            case C_.ADS_VISIBLE_HIDDEN_MANUAL:
                frmAdsCard.setBackgroundColor(activity.getResources().getColor(R.color.ads_visible_hidden_manual, null));
                outDescription = "Сообщение скрыто";
                break;
            case C_.ADS_VISIBLE_HIDDEN_REMOVE:
                frmAdsCard.setBackgroundColor(activity.getResources().getColor(R.color.ads_visible_hidden_remove, null));
                outDescription = "Сообщение удалено";
                break;
        }
        frmAdsCardDescription.setText(outDescription);
    }
}
