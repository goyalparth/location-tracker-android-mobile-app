package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class StreetView extends AppCompatActivity {
    Double lati;
    Double longi;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);
        Bundle bundle = getIntent().getExtras();
        lati = bundle.getDouble("lati");
        longi = bundle.getDouble("longi");
        address = bundle.getString("address");
        TextView tvlat = findViewById(R.id.textLatitude);
        TextView tvlng = findViewById(R.id.textLongitude);
        TextView tvaddr = findViewById(R.id.textAddress);
        tvlat.setText("Latitude: " + lati);
        tvlng.setText("Longitude: " + longi);
        tvaddr.setText("Address: " + address);

        Button button2 = findViewById(R.id.buttonShowMap);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment)
                        getSupportFragmentManager().findFragmentById(R.id.streetView);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {

                        LatLng uc = new LatLng(lati, longi);
                        panorama.setPosition(uc);
                    }
                });



    }

    private void openMap() {
        Intent intent = new Intent(this, MapsActivity.class);
//        intent.putExtra("latid", lati);
//        intent.putExtra("longid" , longi);
//        intent.putExtra("addressd" , address);
        startActivity(intent);
    }

}