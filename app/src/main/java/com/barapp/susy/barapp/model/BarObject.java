package com.barapp.susy.barapp.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by susy on 8/06/17.
 */

public class BarObject {
    String id;
    double latitude;
    double longitude;
    String direction;
    String name;
    String description;
    int positiveValue;
    int negativeValue;

    public BarObject() {

    }

    public BarObject( double latitude, double longitude, String direction, String name,String description, int positiveValue, int negativeValue) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
        this.name = name;
        this.description = description;
        this.positiveValue = positiveValue;
        this.negativeValue = negativeValue;

        setID();


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setID(){
        this.id = this.name;
    }

}
