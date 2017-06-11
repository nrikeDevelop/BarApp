package com.barapp.susy.barapp.modules;

import com.barapp.susy.barapp.model.BarObject;

/**
 * Created by susy on 8/06/17.
 */

public interface MapsView {

    void showToast(String string);

    void localAdded(BarObject barObject);

    void localSelected(BarObject barObject);

}
