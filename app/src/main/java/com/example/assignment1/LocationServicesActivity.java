package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;


import android.os.Bundle;

public class LocationServicesActivity extends AppCompatActivity {

    MyLocationPlaceMap myLocationPlaceMap;
    ArrayList<MyLocationPlace> myLocations = new ArrayList<>();
    MyLocationPlace myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        myLocationPlaceMap = new MyLocationPlaceMap(getApplicationContext(), LocationServicesActivity.this);
        myLocationPlaceMap.requestPermissions();
        myLocationPlaceMap.getLatLngAddress(myLocations);

    }



    public void showCurrentLocation() {
        myLocationPlaceMap.getLatLngAddress(myLocations);
        TextView tvlat = findViewById(R.id.textLatitude);
        TextView tvlng = findViewById(R.id.textLongitude);
        TextView tvaddr = findViewById(R.id.textAddress);

        if (myLocations.size() > 0) {

            myLocation = myLocations.get(0);
            myLocations.clear();
            tvlat.setText("Latitude: " + myLocation.getLatitude());
            tvlng.setText("Longitude: " + myLocation.getLongitude());
            tvaddr.setText("Address: " + myLocation.getAddress());
        }
    }

}