package com.god.parktaeim.all_about_sejong.Activity;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.god.parktaeim.all_about_sejong.GPSListener;
import com.god.parktaeim.all_about_sejong.Model.ToiletItem;
import com.god.parktaeim.all_about_sejong.R;
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
    public final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 0;

    private GoogleMap googleMap;
    ArrayList<ToiletItem> toiletItemArrayList;
    ImageView currentLocationIcon;
    LocationManager locationManager;
    LinearLayout parentLayout;

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


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (gpsOnOffCheck() == false) {
                        showSettingGpsPageWithSnackBar();
                    }
                    getCurrentLocation();
                    setUpCurrentLocationMarker();
                } else {
                    // 권한 거부
                }
                break;
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return;
        } else {

        }

        Log.d("GET CURRENT LOCATION","  PASS PERMISSION");
        if (currentLocation == null) {
            GPSListener gpsListener = new GPSListener();
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, gpsListener);
            currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationManager.removeUpdates(gpsListener);

            if (currentLocation != null) {
                hasCurrentLocation = true;
            }
        } else hasCurrentLocation = true;
    }

    private boolean gpsOnOffCheck() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) { //GPS OFF
            return false;
        } else {
            return true;
        }
    }

    private boolean permissionCheck() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private void showSettingGpsPageWithSnackBar() {
        Snackbar snackbar = Snackbar.make(parentLayout, "위치 사용을 동의하면 현위치를 알 수 있습니다.", 3000)
                .setAction("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        startActivity(intent);
                    }
                });

        TextView snackbarTv = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        TextView snackbarBtnTv = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_action);
        snackbarTv.setTextSize(11);
        snackbarBtnTv.setTextSize(12);
        snackbar.show();
    }

    public void setUpMapCurrentFocus() {
        getCurrentLocation();
        setUpCurrentLocationMarker();

        if (hasCurrentLocation) {
            Log.d("setUpMapCurrentFocus()","  hasCurrentLocation");
            LatLng dest = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        } else {
            Log.d("setUpMapCurrentFocus()","  else zzz");

            if (permissionCheck() == false) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

            } else if (gpsOnOffCheck() == false) {
                showSettingGpsPageWithSnackBar();
                getCurrentLocation();
                setUpCurrentLocationMarker();
            }
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
