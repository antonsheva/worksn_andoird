package com.worksn.classes;

import static android.content.Context.LOCATION_SERVICE;
import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class MyLocation {
    private static MyLocation i;

    public static MyLocation i() {
        if (i == null) i = new MyLocation();
        return i;
    }

    public Location getImHere() {
        if (imHere == null){
            imHere = new Location("");
            imHere.setLatitude(55.753605);
            imHere.setLongitude(37.621094);
        }
        return imHere;
    }

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location imHere; // здесь будет всегда доступна самая последняя информация о местоположении пользователя.
    private CB cb;
    public static boolean locationWasChange  = false;

    public void initLocationManager(Context context, long tm, CB cb) {
        this.cb = cb;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 910);
            return;
        }
        setUpLocationListener(context);
        locationManager.requestLocationUpdates(GPS_PROVIDER,
                1000 * tm, 10, locationListener);
        locationManager.requestLocationUpdates(NETWORK_PROVIDER,
                1000 * tm, 10, locationListener);
        imHere = locationManager.getLastKnownLocation(GPS_PROVIDER);
    }

    private void setUpLocationListener(Context context) {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                imHere = location;
                cb.callback(imHere);
                locationWasChange = true;
            }

            @Override @SuppressWarnings("deprecation")
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override @SuppressWarnings("deprecation")
            public void onProviderEnabled(String provider) {

            }

            @Override @SuppressWarnings("deprecation")
            public void onProviderDisabled(String provider) {

            }
        };
    }
    public void removeLocationUpdates(){
        if (locationManager != null)locationManager.removeUpdates(locationListener);
    }

    public void turnGPSOn(Context context){
        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            context.sendBroadcast(poke);
        }
    }
    public void turnGPSOff(Context context){
        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            context.sendBroadcast(poke);
        }
    }



    public interface CB{
        void callback(Location location);
    }
}
