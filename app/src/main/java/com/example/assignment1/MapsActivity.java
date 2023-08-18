package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.example.assignment1.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    MyLocationPlaceMap myLocationplaces;

    GoogleMap mMap;

//    private ActivityMapsBinding binding;

    MyLocationPlaceMap myLocationPlaceMap;
    Double lati;
    Double lati1;
    Double longi;
    Double longi1;
    String address;

//    String marker;
    ArrayList<MyLocationPlace> myLocations = new ArrayList<>();
    MyLocationPlace myLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_maps);
        try {
            Bundle bundle = getIntent().getExtras();

            lati = bundle.getDouble("latitude");
            longi = bundle.getDouble("longitude");
            lati1 = lati;
            longi1 = longi;
            address = bundle.getString("address");
            TextView tvlat = findViewById(R.id.textLatitude);
            TextView tvlng = findViewById(R.id.textLongitude);
            TextView tvaddr = findViewById(R.id.textAddress);

//            TextView tvaddr = findViewById(R.id.textAddress);
            tvlat.setText("Latitude: " + lati);
            tvlng.setText("Longitude: " + longi);
            tvaddr.setText("Address: " + address);
//            tvaddr.setText("Address: " + address);
//            final double LATI1= lati;
//            //            public enum LATI1 = lati;
//            Toast.makeText((Context) MapsActivity.this, (int) LATI1, Toast.LENGTH_SHORT).show();
        }

        catch (Exception e){
            Toast.makeText(this, "catch error", Toast.LENGTH_SHORT).show();
        }
//


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myLocationplaces = new MyLocationPlaceMap(getApplicationContext(), MapsActivity.this);



        Button button1 = findViewById(R.id.buttonNearbyPlaces);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLocationplaces.getNearbyPlaces(mMap,"AIzaSyB5udKJBzL5Kb6XDOIOEZT5VpYvafqdK3s");
            }
        });
//
        Button button2 = findViewById(R.id.buttonShowStreetView);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStreet();
            }
        });


//        LatLng latLng = new LatLng(MainActivity.class.getDeclaredMethod(void buttonMyLocation1()))


    }

    private void openStreet() {

        setContentView(R.layout.activity_street_view);
        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment)
                        getSupportFragmentManager().findFragmentById(R.id.streetView);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {

                        TextView tvlat = findViewById(R.id.textLatitude);
                        TextView tvlng = findViewById(R.id.textLongitude);
                        TextView tvaddr = findViewById(R.id.textAddress);
                        tvlat.setText("Latitude: " + lati);
                        tvlng.setText("Longitude: " + longi);
                        tvaddr.setText("Address: " + address);

                        LatLng uc = new LatLng(lati, longi);
                        panorama.setPosition(uc);
                        Button button = findViewById(R.id.buttonShowMap);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openMap();
                            }
                        });
                    }
                });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lati,longi);
        mMap.addMarker(new MarkerOptions().position(sydney).title(address));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
            public boolean onMarkerClick(Marker marker) {
                // on marker click we are getting the title of our marker
                // which is clicked and displaying it in a toast message.
//
                String markerName = marker.getTitle();
                lati = marker.getPosition().latitude;
                longi = marker.getPosition().longitude;

//                Toast.makeText(MapsActivity.this, "Clicked location is " + LATI1, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MapsActivity.this, latitudee, Toast.LENGTH_SHORT).show();
                openStreet();
                return false;
//                marker = arg0.ger
            }
        });

//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//
//            @Override
//            public void onInfoWindowClick(Marker marker) {
////                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
////                String reference = mMarkerPlaceLink.get(marker.getId());
////                intent.putExtra("reference", reference);
////
////                // Starting the  Activity
////                startActivity(intent);
////                Log.d("mGoogleMap1", "Activity_Calling");
//
//                lati = marker.getPosition().latitude;
//                longi = marker.getPosition().longitude;
//                openStreet();
//            }
//        });



    }
    private void openMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("latitude",  lati1 );
        intent.putExtra("longitude",  longi1 );
        intent.putExtra("address",  address );
        startActivity(intent);
    }


}




