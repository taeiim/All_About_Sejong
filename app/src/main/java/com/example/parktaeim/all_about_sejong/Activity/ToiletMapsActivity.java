package com.example.parktaeim.all_about_sejong.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    LocationManager locationManager;
    LinearLayout parentLayout;

    private boolean isLocation = false;
    Location currentLocation;
    private boolean hasCurrentLocation = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_maps);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.toilet_googleMap);
        mapFragment.getMapAsync(this);

        MapsInitializer.initialize(getApplicationContext());

        parentLayout = (LinearLayout) findViewById(R.id.content_layout);
        currentLocationIcon = (ImageView) findViewById(R.id.icon_current_location);
        ImageView backIcon = (ImageView) findViewById(R.id.toilet_maps_backIcon);
        backIcon.setOnClickListener(v -> finish());

        currentLocationIcon.setOnClickListener(v -> {
            setUpMapCurrentFocus();
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (currentLocation == null) {
            GPSListener gpsListener = new GPSListener();
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, gpsListener);
            currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationManager.removeUpdates(gpsListener);

            if(currentLocation != null){
                hasCurrentLocation = true;
            }
        } else hasCurrentLocation = true;
    }

    private void showGpsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("위치 서비스 설정");
        builder.setMessage("위치 서비스 기능(GPS)을 설정해주세요.");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        isLocation = false;
                    }
                });
        builder.show();
    }

    private boolean gpsOnOffCheck() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) { //GPS OFF
            isLocation = false;
            return false;

        } else {
            isLocation = true;
            return true;
        }
    }

    public void setUpMapCurrentFocus() {
        if (hasCurrentLocation) {
            LatLng dest = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        } else {
            Snackbar.make(parentLayout, "위치 사용을 동의하면 현위치를 알 수 있습니다.", 3000)
                    .setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            startActivity(intent);
                        }
                    }).show();

        }
    }

    private void setUpDataOnMap() {
        Intent intent = getIntent();
        toiletItemArrayList = (ArrayList<ToiletItem>) intent.getSerializableExtra("ToiletArrayList");

        for (int i = 0; i < toiletItemArrayList.size(); i++) {
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

    private void setUpCurrentLocationMarker() {
        if (hasCurrentLocation) {
            LatLng dest = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(dest);
            markerOptions.title("현위치");

            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        } else {
            double latitude = 36.5055638;
            double longitude = 127.2484251;
            LatLng dest = new LatLng(latitude, longitude);

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        setUpDataOnMap();
        getCurrentLocation();
        setUpCurrentLocationMarker();
    }
}
