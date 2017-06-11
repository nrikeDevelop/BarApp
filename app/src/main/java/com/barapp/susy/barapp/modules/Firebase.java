package com.barapp.susy.barapp.modules;

import android.content.Context;
import android.util.Log;

import com.barapp.susy.barapp.R;
import com.barapp.susy.barapp.common.BaseActivity;
import com.barapp.susy.barapp.model.BarObject;
import com.barapp.susy.barapp.modules.MapsView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ListIterator;

/**
 * Created by susy on 10/06/17.
 */

public class Firebase extends BaseActivity {

    public static String UBICATION= "UBICATION" ;

    static FirebaseDatabase database;
    static DatabaseReference databaseRef;

    public static void referenceDatabase(String ref){
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference(ref);
    }

    public static void getPlace(final MapsView mapsView, String ref, final String queryStringID){
        referenceDatabase(ref);
        Query findQuery = databaseRef.equalTo(queryStringID).orderByKey();
        findQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot snapshot = dataSnapshot.child(queryStringID);
                BarObject barObject = snapshot.getValue(BarObject.class);

                mapsView.localSelected(barObject);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void getPlaces(final MapsView mapsView, String ref){
        referenceDatabase(ref);
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    System.out.println("cargado de mapa "+snapshot.getValue());

                    BarObject barObject = snapshot.getValue(BarObject.class);
                    mapsView.localAdded(barObject);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void addIfNotExist(final Context context, final MapsView mapsView, final BarObject barObject, final String ref, String query){

        referenceDatabase(ref);

        Query findQuery = databaseRef.equalTo(query).orderByKey();
        findQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    //EXIST
                    mapsView.showToast(context.getString(R.string.main_local_exist));

                }else{
                    //NOT EXIST

                    String childString = barObject.getId();
                    databaseRef.child(childString).setValue(barObject);
                    mapsView.showToast(context.getString(R.string.main_local_added));
                    mapsView.localAdded(barObject);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //ERROR
                System.out.println("Error");
            }
        });

    }




}
