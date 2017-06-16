package com.barapp.susy.barapp.common;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.health.TimerStat;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.maps.LocationSource;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by susy on 3/04/17.
 */

public class GpsLocation implements LocationListener {

    static boolean isRecivingData = true;
    static double longitude;
    static double latitude;
    static String direction;

    static Context context;

    public GpsLocation(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        addressToString(location);
        //Toast.makeText(context, String.valueOf(latitude), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Toast.makeText(context, provider, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String provider) {
        isRecivingData = true;
    }

    @Override
    public void onProviderDisabled(String provider) {
        isRecivingData = false;
    }

    public void addressToString(Location location){

        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    direction = address.getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static double getLongitude() {
        return longitude;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static boolean isRecivingData() {
        return isRecivingData;
    }

    public static String getDirection(){
        return direction;
    }

}
