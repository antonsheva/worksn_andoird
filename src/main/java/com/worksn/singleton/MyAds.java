package com.worksn.singleton;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;


import com.yandex.mapkit.geometry.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.worksn.R;
import com.worksn.classes.BlinkView;
import com.worksn.classes.ButtonsHighLight;
import com.worksn.classes.MyImg;
import com.worksn.interfaces.NetCallback;
import com.worksn.objects.Ads;
import com.worksn.objects.AdsCllct;
import com.worksn.objects.C_;
import com.worksn.objects.G_;
import com.worksn.objects.MapAreaCoords;
import com.worksn.objects.MyContext;
import com.worksn.objects.PostDataAddAds;
import com.worksn.objects.PostSubData;
import com.worksn.objects.SelAdsParam;
import com.worksn.objects.User;
import com.worksn.static_class.Post;

public class MyAds {
    public AdsCllct cllct = new AdsCllct();
    public Point   mapPoint = null;
    public Long lifetime = 2592000L;
    public long editAdsId = 0;
    public String imgsListInString = null;
    public List<String> images = new ArrayList<>();
    public List<String> imagesIcon = new ArrayList<>();
    public List<String> imgsNames = new ArrayList<>();
    public List<Ads> targetAdsList = new ArrayList<>();
    public Ads targetAds = new Ads();

    public static MyAds i;
    public static MyAds i(){
        if (i == null){
            i = new MyAds();
        }
        return i;
    }
    public void removeImg(int index){
        imagesIcon.remove (index);
        images.remove     (index);
        imgsNames.remove(index);
        refreshImgsListInString();
    }
    public void addImg(String img, String imgIcon){
        String[] pathArr = imgIcon.split("/");
        String imgName = pathArr[pathArr.length - 1];
        imagesIcon.add(imgIcon);
        images.add(img);
        imgsNames.add(imgName);
        refreshImgsListInString();
    }
    public void getAdsCollection(Context context, CB cb){
        MapAreaCoords mapAreaCoords = new MapAreaCoords(MyMap.i().getMapVisibleRegion());
        SelAdsParam selAdsParam = new SelAdsParam(MyAds.i().cllct, mapAreaCoords);
        new ButtonsHighLight((Activity) context).setAdsTypeBtColor(MyAds.i().cllct);
        Post.sendRequest(context,C_.ACT_GET_ADS_COLLECTION, selAdsParam,
                (MyContext data, Integer result, String stringData) -> {
                    MyMap.i().cleanMapObjectCollections();
                    if(result > 0) {
                        boolean err;
                        ArrayList <Ads> adsList = new ArrayList<>();
                        ArrayList <Integer>banList = new ArrayList<>(Usr.i().getBanList());
                        Usr.i().clearUsersList();
                        for (Ads ads : data.getAdsCollection()){
                            err = false;
                            User user = Usr.i().getUserDataFromObject(ads);
                            if (user != null){
                                if (ads.getId()     == null)       err = true;
                                if (user.getId()    == null)       err = true;
                                if (user.getLogin() == null)       err = true;
                                if (banList.contains(user.getId()))err = true;
                                if (err) continue;
                                Usr.i().addUserToTable(user);
                                adsList.add(ads);
                            }
                        }
                        targetAdsList = new ArrayList<>(adsList);
                    }
                    if((cb != null) && (result != -1)){
                        cb.callback(1, targetAdsList, result);
                    }
                });
    }
    private void refreshImgsListInString(){
        imgsListInString = "";
        for (String name : imgsNames) {
            imgsListInString = imgsListInString + name + ",";
        }
    }
    public void addAds(Context context, Ads sendData, CB cb){
        int type;
        if (editAdsId > 0){
            sendData.setId(editAdsId);
            sendData.setEdit(1);
        }
        if (mapPoint == null){
            PUWindow.i().show("Укажите точку на карте");
            return;
        }
        if (cllct.getAdsType() == C_.ADS_TYPE_ANY){
            LinearLayout frmAdsType = (LinearLayout)((Activity)context).findViewById(R.id.frmAdsType);
            PUWindow.i().show("Укажите тип объявления");
            BlinkView.i().blink((Activity)context, frmAdsType);
            return;
        } else {
            type = (cllct.getAdsType() == C_.ADS_TYPE_WORKER) ? C_.ADS_TYPE_EMPLOYER : C_.ADS_TYPE_WORKER;
        }

        if ((cllct.getCatNum() == null)||(cllct.getCatNum() == 0)){
            LinearLayout adsParamCategoryField = (LinearLayout)((Activity)context).findViewById(R.id.adsParamCategoryField);
            PUWindow.i().show("Выберите категорию");
            BlinkView.i().blink((Activity)context, adsParamCategoryField);
            return;
        }

        sendData.setActive(1);
        sendData.setAdsType(type);
        sendData.setCategory(cllct.getCatNum());
        sendData.setImg(imgsListInString);
        sendData.setLifetime(lifetime);
        sendData.setCoordX(mapPoint.getLatitude());
        sendData.setCoordY(mapPoint.getLongitude());
        sendData.setHourStart(targetAds.getHourStart());
        sendData.setHourStop (targetAds.getHourStop() );
        sendData.setMinStart (targetAds.getMinStart() );
        sendData.setMinStop  (targetAds.getMinStop()  );

        final Point tmpPoint =  MyAds.i().mapPoint;
        MyAds.i().mapPoint = null;
        G_.clickListenerEnable = false;
        MyMap.i().cleanMapObjectCollections(C_.MAP_PM_RED);
        editAdsId = 0;
        AppMode.i().setMode(null);
        Post.sendRequest(context,C_.ACT_ADS_ADD, sendData,
                (MyContext data, Integer result, String stringData) -> {
                    if (result == -1){
                        PUWindow.i().show(stringData);
                        return;
                    }
                    clearImgsBuffers();
                    MyMap.i().mapCollectionRed.clear();
                    Ads ads = data.getTargetAds();
                    if (ads != null){
                        MyMap.i().addPm(context, C_.MAP_PM_YELLOW, tmpPoint, ads);
                        cb.callback(1, data.getTargetAds(), result);
                    }else {
                        new PUWindow().show(context.getString(R.string.someThrowable));
                    }
                });
    }

    private void clearImgsBuffers(){
        images = new ArrayList<>();
        imagesIcon = new ArrayList<>();
        imgsNames = new ArrayList<>();
        imgsListInString = null;
    }
    public void modifyAdsState(Context context, Ads ads, String act, CB cb){
        PostSubData subData = new PostSubData();
        subData.setId(ads.getId());
        Post.sendRequest(context, act, subData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (result == -1){
                    PUWindow.i().show(stringData);
                    return;
                }
                cb.callback(result, null, result);
            }
        });
    }
    public void prepareAdsImgsForEdit(Context context, Ads ads){
        PostSubData subData = new PostSubData();
        subData.setAds_id(ads.getId());
        subData.setImg(ads.getImg());
        subData.setImgIcon(ads.getImgIcon());
        Post.sendRequest(context, C_.ACT_ADS_EDIT, subData, new NetCallback() {
            @Override
            public void callback(MyContext data, Integer result, String stringData) {
                if (result == -1){
                    PUWindow.i().show(stringData);
                    return;
                }
                String tmpImgList = data.getImgList();
                if (tmpImgList == null)return;
                if (tmpImgList.length() < 10)return;
                String[] imgList = tmpImgList.split(",");
                for (String img : imgList){
                    new MyImg((Activity) context).addImgToGroup(C_.URL_TMP_IMG+img,
                            C_.URL_TMP_IMG_ICON+img);
                }

            }
        });
    }

    public interface CB{
        public void callback(int code, Object o, int result);
    }
}
