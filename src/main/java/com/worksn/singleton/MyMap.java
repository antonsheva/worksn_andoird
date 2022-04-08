package com.worksn.singleton;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.InertiaMoveListener;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.VisibleRegion;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;

import com.worksn.R;
import com.worksn.objects.Ads;
import com.worksn.objects.C_;

public class MyMap {
    public MapView mapView;

    Location location = new Location("gps");
    boolean userLocationIsDefined = false;
    boolean isRunning = false;
    boolean stopTapEvent = false;
    static float zoom = 11.0f;
    Timer timer;
    TimerTask timerTask;
    List<Ads> targetAdsList = new ArrayList<>();
    ArrayList<Ads> visibleAdsList = new ArrayList<>();

    CB cb;
    MapObjectCollection mapCollectionRed   ;
    MapObjectCollection mapCollectionGreen ;
    MapObjectCollection mapCollectionBlue  ;
    MapObjectCollection mapCollectionYellow;
    MapObjectCollection mapCollectionPurple;

    InputListener inputListener;
    InertiaMoveListener inertiaMoveListener;
    MapObjectTapListener mapObjectTapListener;
    VisibleRegion mapVisibleRegion;


    private static MyMap i;
    public static MyMap i(){
        if (i == null) i = new MyMap();
        return i;
    }

    public void initMapKit(Context context){
        if(isRunning)return;
        MapKitFactory.setApiKey("a3c7187b-0029-4189-b28a-3a77ad3507ff");
        MapKitFactory.initialize(context);
    }
    public void initMap(Context context, CB cb) {
        isRunning = true;
        this.cb = cb;
        mapView = (MapView)((Activity)context).findViewById(R.id.mapview);

        mapView.getMap().setRotateGesturesEnabled(false);

        mapCollectionRed    = mapView.getMap().getMapObjects().addCollection();
        mapCollectionGreen  = mapView.getMap().getMapObjects().addCollection();
        mapCollectionBlue   = mapView.getMap().getMapObjects().addCollection();
        mapCollectionYellow = mapView.getMap().getMapObjects().addCollection();

        mapCollectionPurple = mapView.getMap().getMapObjects().addCollection();
        mapCollectionPurple.setZIndex(5);
        mapCollectionRed.setZIndex(6);

        initInertiaMoveListener();
        initMapObjectListener();
        initInputListener();

        mapCollectionBlue.addTapListener(mapObjectTapListener);
        mapCollectionRed.addTapListener(mapObjectTapListener);
        mapCollectionGreen.addTapListener(mapObjectTapListener);
        mapCollectionYellow.addTapListener(mapObjectTapListener);


        mapView.getMap().addInputListener(inputListener);
        mapVisibleRegion = new VisibleRegion();
        mapView.getMap().addInertiaMoveListener(inertiaMoveListener);

        location.setLatitude(55.751574);
        location.setLongitude(37.573856);
        setCameraPosition(location);
        cb.callback(C_.CODE_MAP_INIT, mapVisibleRegion);
    }
    public void setCameraPosition(Location location){
        mapView.getMap().move(
                new CameraPosition(new Point(location.getLatitude(), location.getLongitude()), zoom, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0.3f),
                null);
    }
    public void setCameraPosition(Point point){
        mapView.getMap().move(
                new CameraPosition(point, zoom, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0.3f),
                null);
    }
    public VisibleRegion getMapVisibleRegion(){
        return mapView.getFocusRegion();
    }
    public void addPmGroup(Context context, List<Ads> adsArr){
        visibleAdsList.addAll(adsArr);
        int mapCllct = 0;
        for(Ads ads : adsArr){
            Point point = new Point(ads.getCoordX(), ads.getCoordY());
            mapCllct = getCllctType(ads);
            addPm(context, mapCllct, point, ads);
        }
    }
    public void cleanMapObjectCollections(){
        mapCollectionBlue.clear();
        mapCollectionGreen.clear();
        mapCollectionYellow.clear();
        mapCollectionPurple.clear();

    }
    public void cleanMapObjectCollections(int mapCollection){
        switch (mapCollection){
            case C_.MAP_PM_BLUE  : mapCollectionBlue.clear();break;
            case C_.MAP_PM_GREEN : mapCollectionGreen.clear();break;
            case C_.MAP_PM_YELLOW: mapCollectionYellow.clear();break;
            case C_.MAP_PM_RED   : mapCollectionRed.clear();break;
            case C_.MAP_PM_PURPLE: mapCollectionPurple.clear();break;
        }
    }
    private int getCllctType(Ads ads) {
        int cllctType = C_.MAP_PM_YELLOW;
        Integer adsType = ads.getAdsType();
        Integer adsUserId = ads.getUserId();
        if (Usr.i().auth())
            if(adsUserId.equals(Usr.i().getUser().getId()))return cllctType;
        if(adsType.equals(C_.ADS_TYPE_WORKER))return C_.MAP_PM_GREEN;
        return C_.MAP_PM_BLUE;
    }
    public void addPm(Context context, int mapCllct, Point point, Ads ads){
        MapObjectCollection mc;
        int imgId;
        switch (mapCllct){
            case C_.MAP_PM_BLUE   : mc    = mapCollectionBlue;
                imgId = R.drawable.pm_blue;
                break;
            case C_.MAP_PM_GREEN  : mc    = mapCollectionGreen;
                imgId = R.drawable.pm_green;
                break;
            case C_.MAP_PM_RED    : mc    = mapCollectionRed;
                imgId = R.drawable.pm_red;
                break;
            case C_.MAP_PM_PURPLE : mc    = mapCollectionPurple;
                imgId = R.drawable.pm_purple;
                break;
            default:                mc    = mapCollectionYellow;
                imgId = R.drawable.pm_orange;
        }

        IconStyle iconStyle = new IconStyle();
        iconStyle.setScale(0.2f);
        PlacemarkMapObject pm = mc.addPlacemark(point, ImageProvider.fromResource(context, imgId));
        pm.setIconStyle(iconStyle);
        pm.setUserData(ads);
    }
    private void initMapObjectListener(){
        mapObjectTapListener = new MapObjectTapListener() {
            @Override
            public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
                stopTapEvent = true;
                Ads ads;
                try{
                    ads = (Ads)mapObject.getUserData();
                }catch (Exception e){
                    Log.i("MyMapEx", "Error conver object to Ads ");
                    return false;
                }
                if(ads == null){
                    Log.i("MyMapEx", "Object is null");
                    return false;
                }

                targetAdsList.add(ads);
                if(timer != null) timer.cancel();
                timer = null;
                timerTask = null;
                timer  = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        int len = targetAdsList.size();
                        Log.i("MyMap", "ads qt -> "+len);
//                        if(len>0)cb.callback(C_.CODE_MAP_ADS_DETAILS, targetAdsList);
                        if(len>0)cb.adsListReturn(targetAdsList);
                        targetAdsList.clear();
                        timer.cancel();
                        timer = null;
                    }
                };
                timer.schedule(timerTask, 100,100);
                return false;
            }
        };
    }
    private void initInertiaMoveListener(){
        inertiaMoveListener = new InertiaMoveListener() {
            @Override
            public void onStart(@NonNull com.yandex.mapkit.map.Map map, @NonNull CameraPosition cameraPosition) {}
            @Override
            public void onCancel(@NonNull com.yandex.mapkit.map.Map map, @NonNull CameraPosition cameraPosition) {}

            @Override
            public void onFinish(@NonNull com.yandex.mapkit.map.Map map, @NonNull CameraPosition cameraPosition) {
                Point pn = cameraPosition.getTarget();
                Log.i("MyCameraPositionChanged", "onFinish  -> " + pn.getLatitude() + " - " + pn.getLongitude());

                mapVisibleRegion = mapView.getFocusRegion();
                Point pnTopLeft = mapVisibleRegion.getTopLeft();
                Point pnBottomRight = mapVisibleRegion.getBottomRight();
                zoom = mapView.getMap().getCameraPosition().getZoom();
                cb.callback(C_.CODE_MAP_MOVE, mapVisibleRegion);
            }
        };
    }
    private void initInputListener(){
        inputListener = new InputListener() {
            @Override
            public void onMapTap(@NonNull Map map, @NonNull Point point) {
                cb.setPointReturn(point);
            }
            @Override
            public void onMapLongTap(@NonNull Map map, @NonNull Point point) {
                Log.i("MyMap", "33333333");
            }
        };
    }
    public void setTapPm(Context context, Point tapPoint) {
        if(stopTapEvent) {
            stopTapEvent = false;
            return;
        }
        MyAds.i().mapPoint = new Point();
        mapCollectionRed.clear();
        MyAds.i().mapPoint = tapPoint;
        addPm(context, C_.MAP_PM_RED, tapPoint, null);
    }
    public interface CB{
        void callback(int code, Object o);
        void setPointReturn(Point point);
        void adsListReturn(List<Ads> adsList);
    }
}
