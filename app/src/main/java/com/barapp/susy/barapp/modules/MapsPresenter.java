package com.barapp.susy.barapp.modules;

import android.content.Context;

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
}
