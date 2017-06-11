package com.barapp.susy.barapp.modules;

import android.content.Context;

import com.barapp.susy.barapp.model.BarObject;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by susy on 8/06/17.
 */

public class MapsPresenter {

    MapsView mapsView;
    Context context;
    GoogleMap mMap;

    public MapsPresenter(MapsView mapsView, Context context, GoogleMap mMap) {
        this.mapsView = mapsView;
        this.context = context;
        this.mMap = mMap;
    }

    public void addPlace(BarObject barObject){
        String query = barObject.getName();
        Firebase.addIfNotExist(context,mapsView,barObject,Firebase.UBICATION,query);
    }

    public void loadPlaces(){
        Firebase.getPlaces(mapsView,Firebase.UBICATION);
    }

    public void getPlace(String nameID){
        Firebase.getPlace(mapsView,Firebase.UBICATION,nameID);
    }



}
