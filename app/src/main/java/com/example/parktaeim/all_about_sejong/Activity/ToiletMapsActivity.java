package com.example.parktaeim.all_about_sejong.Activity;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.parktaeim.all_about_sejong.GPSListener;
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

    ImageView currentLocationIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_maps);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.toilet_googleMap);
        mapFragment.getMapAsync(this);

        MapsInitializer.initialize(getApplicationContext());

        currentLocationIcon = (ImageView) findViewById(R.id.icon_current_location);
        ImageView backIcon = (ImageView) findViewById(R.id.toilet_maps_backIcon);
        backIcon.setOnClickListener(v -> finish());

        currentLocationIcon.setOnClickListener(v -> {
            setUpMapCurrentFocus();
        });
    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        GPSListener gpsListener = new GPSListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("Permission Return","ㅠㅠㅠ");
            return;
        }

        Log.d("requestLocationUpdates","!");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, gpsListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, gpsListener);

        Location returnCurrentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        if(returnCurrentLocation != null){
//            double latitude = returnCurrentLocation.getLatitude();
//            double longitude = returnCurrentLocation.getLongitude();
//            Log.d("latitude=="+latitude,"  Longitude=="+longitude);
//
//        }
//        locationManager.removeUpdates(gpsListener);


    }

    public void setUpMapCurrentFocus(){
        getCurrentLocation();

        double latitude = 36.5055638;
        double longitude = 127.2484251;

        LatLng dest = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dest);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

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
