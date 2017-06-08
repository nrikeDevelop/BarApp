package com.barapp.susy.barapp.common;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by susy on 8/06/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Pausa");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resumen");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Destruida");
    }
}
