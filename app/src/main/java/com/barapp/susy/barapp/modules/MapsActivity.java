package com.barapp.susy.barapp.modules;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.barapp.susy.barapp.R;
import com.barapp.susy.barapp.common.BaseActivity;
import com.barapp.susy.barapp.common.GpsLocation;
import com.barapp.susy.barapp.model.BarObject;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.like.LikeButton;
import com.like.OnLikeListener;


public class MapsActivity extends BaseActivity implements OnMapReadyCallback,MapsView {

    private final int REQUEST_PERMISSIONS = 123;

    private GoogleMap mMap;
    Context context;
    MapsView mapsView;
    MapsPresenter mapsPresenter;

    RelativeLayout loadigRelativeLayout;
    Button buttonAddPlace;
    TextView loadingTitle;
    TextView loadingText;
    Button buttonLoading;
    ImageView imageView;
    public void setUI(){
        loadigRelativeLayout = (RelativeLayout) findViewById(R.id.loading_layout);
        buttonAddPlace = (Button) findViewById(R.id.main_add_place_button);
        buttonLoading = (Button) findViewById(R.id.main_button_loading);
        loadingText = (TextView) findViewById(R.id.loading_poema_tittle);
        loadingTitle = ( TextView) findViewById(R.id.loading_poema_text);
        imageView = (ImageView) findViewById(R.id.image_view_loading);

        setUpFonts();
    }

    public void setUpFonts(){
        Typeface font_goudy = Typeface.createFromAsset(getAssets(), "Goudy.ttf");
        Typeface font_regular = Typeface.createFromAsset(getAssets(), "Ubuntu.ttf");

        loadingText.setTypeface(font_goudy);
        loadingTitle.setTypeface(font_goudy);
        buttonLoading.setTypeface(font_regular);

        buttonAddPlace.setTypeface(font_regular);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        mapsView = this;
        mapsPresenter = new MapsPresenter(mapsView, context,mMap);
        setUI();

        // Load loading
        loading();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions();
        }

        mapsPresenter.loadPlaces();

        //ACTIONS BUTTONS
        buttonAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(GpsLocation.isRecivingData()){
                    if (GpsLocation.getLongitude() == 0.0 || GpsLocation.getLatitude() == 0.0) {
                        Toast.makeText(context, context.getString(R.string.gps_is_loading), Toast.LENGTH_SHORT).show();
                    }else{
                        dialogAddMarker();
                    }
                }else{
                    Toast.makeText(context,context.getString(R.string.turn_on_gps), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String nameID = marker.getTitle();
                mapsPresenter.getPlace(nameID);
                return true;
            }
        });

    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void localAdded(BarObject barObject) {
        LatLng latLng = new LatLng(barObject.getLatitude(), barObject.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(barObject.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void localSelected(BarObject barObject) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(barObject.getName());

        dialog.create();
        dialog.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                    Toast.makeText(context, "Todos los permisos aceptados", Toast.LENGTH_SHORT).show();

                } else {
                    //not granted
                    Toast.makeText(context, "Faltan permisos por aceptar", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void dialogAddMarker(){

        View view = getLayoutInflater().inflate(getResources().getLayout(R.layout.add_marker_dialog),null);

        Button buttonAddMarker = (Button) view.findViewById(R.id.dialog_button_add);
        TextView textViewUbication = (TextView) view.findViewById(R.id.dialog_ubication_bar);
        final EditText editTextName = (EditText) view.findViewById(R.id.dialog_edit_text);
        final EditText editTextDecription = (EditText) view.findViewById(R.id.dialog_edit_description);
        final TextView editTextCount = (TextView) view.findViewById(R.id.dialog_edit_count);

        /*
        LikeButton likeButton = (LikeButton) view.findViewById(R.id.lik) ;
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(MapsActivity.this, "GUSTA", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(MapsActivity.this, "NO GUSTA", Toast.LENGTH_SHORT).show();

            }
        });
        */


        textViewUbication.setText(GpsLocation.getDirection());

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(context.getString(R.string.dialog_title));
        dialog.setView(view);
        final AlertDialog viewDialog = dialog.create();
        viewDialog.show();

        buttonAddMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().toString().equals("")){
                    Toast.makeText(context,context.getString(R.string.dialog_empty_data), Toast.LENGTH_SHORT).show();
                }else{

                    LatLng latlong = new LatLng(GpsLocation.getLatitude(),GpsLocation.getLongitude());
                    BarObject barObject = new BarObject(latlong.latitude,latlong.longitude,GpsLocation.getDirection(),
                            editTextName.getText().toString().toLowerCase(),editTextDecription.getText().toString(),0,0);
                    //mapsPresenter.addMarker(mMap,barObject);

                    mapsPresenter.addPlace(barObject);
                    viewDialog.dismiss();
                }
            }
        });

        editTextDecription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextCount.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void loading(){
        Glide.with(context)
                //.load("https://media2.giphy.com/media/DGWAx8d3IkICs/giphy.gif")
                .load(R.drawable.beer3)
                .into(imageView);

        buttonLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadigRelativeLayout.setVisibility(View.GONE);
            }
        });



    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS);
    }



}
