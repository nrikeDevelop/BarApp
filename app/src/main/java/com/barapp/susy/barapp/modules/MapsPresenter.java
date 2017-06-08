package com.barapp.susy.barapp.modules;

import android.content.Context;

import com.barapp.susy.barapp.model.BarObject;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by susy on 8/06/17.
 */

public class MapsPresenter {

    MapsView mapsView;
    Context context;

    public MapsPresenter(MapsView mapsView, Context context) {
        this.mapsView = mapsView;
        this.context = context;
    }


    public void addMarker(GoogleMap mMap, BarObject barObject){
        LatLng latLng = new LatLng(barObject.getLatLng().latitude, barObject.getLatLng().longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title(barObject.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

}
