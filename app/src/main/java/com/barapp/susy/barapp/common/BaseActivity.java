package com.barapp.susy.barapp.common;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by susy on 8/06/17.
 */

public class BaseActivity extends AppCompatActivity {

    GpsLocation gpsLocation;
    LocationManager locationManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpsLocation = new GpsLocation(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onPause() {
        try {
            super.onPause();
            stopGps();
            System.out.println("PAUSE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            System.out.println("Resumen");
            startGps();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        try {
            super.onDestroy();
            stopGps();
            System.out.println("Destruida");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startGps() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        //Toast.makeText(this, "GPS iniciado", Toast.LENGTH_SHORT).show();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, gpsLocation);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, gpsLocation);
    }

    public void stopGps(){
        //Toast.makeText(this, "GPS parado", Toast.LENGTH_SHORT).show();
        locationManager.removeUpdates(gpsLocation);
    }

}
