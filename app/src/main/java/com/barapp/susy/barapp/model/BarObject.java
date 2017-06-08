package com.barapp.susy.barapp.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by susy on 8/06/17.
 */

public class BarObject {
    LatLng latLng;
    String direction;
    String name;
    int positiveValue;
    int negativeValue;

    public BarObject(LatLng latLng, String direction, String name, int positiveValue, int negativeValue) {
        this.latLng = latLng;
        this.direction = direction;
        this.name = name;
        this.positiveValue = positiveValue;
        this.negativeValue = negativeValue;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositiveValue() {
        return positiveValue;
    }

    public void setPositiveValue(int positiveValue) {
        this.positiveValue = positiveValue;
    }

    public int getNegativeValue() {
        return negativeValue;
    }

    public void setNegativeValue(int negativeValue) {
        this.negativeValue = negativeValue;
    }
}
