package com.example.parktaeim.all_about_sejong.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.parktaeim.all_about_sejong.R;
import com.example.parktaeim.all_about_sejong.Model.ToiletItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 2. 11..
 */

public class ToiletMapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    ArrayList<ToiletItem> toiletItemArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_maps);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.toilet_googleMap);
        mapFragment.getMapAsync(this);

        MapsInitializer.initialize(getApplicationContext());
        ImageView backIcon = (ImageView) findViewById(R.id.toilet_maps_backIcon);
        backIcon.setOnClickListener(v -> finish());



    }

    private void setUpDataOnMap() {
        Intent intent = getIntent();
        toiletItemArrayList = (ArrayList<ToiletItem>) intent.getSerializableExtra("ToiletArrayList");

        for(int i=0; i<toiletItemArrayList.size(); i++){
            double latitude = toiletItemArrayList.get(i).getToilet_latitude();
            double longitude = toiletItemArrayList.get(i).getToilet_longitude();
            String name = toiletItemArrayList.get(i).getToilet_name();

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(latitude, longitude));
            markerOptions.title(name);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bathroom_onmap));
            googleMap.addMarker(markerOptions);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng dest = new LatLng(36.5055638, 127.2484251);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dest);
        markerOptions.title("현위치");

        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        setUpDataOnMap();

    }
}
