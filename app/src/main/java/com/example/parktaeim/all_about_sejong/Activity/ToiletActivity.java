package com.example.parktaeim.all_about_sejong.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Adapter.AllCenterRecyclerViewAdapter;
import com.example.parktaeim.all_about_sejong.Adapter.ToiletRecyclerViewAdapter;
import com.example.parktaeim.all_about_sejong.Model.ToiletItem;
import com.example.parktaeim.all_about_sejong.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 9..
 */

public class ToiletActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ToiletRecyclerViewAdapter adapter;
    private ImageView backIcon;

    private String jsonString = null;
    ArrayList<ToiletItem> toiletItemArrayList;
    private TextView intentToiletMaps;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet);

        backIcon = (ImageView) findViewById(R.id.toilet_backIcon);
        intentToiletMaps = (TextView) findViewById(R.id.intentMapToilet_textView);
        backIcon.setOnClickListener(v -> finish());
        intentToiletMaps.setOnClickListener(v -> {
            Intent intent = new Intent(ToiletActivity.this, ToiletMapsActivity.class);
            intent.putExtra("ToiletArrayList", toiletItemArrayList);
            startActivity(intent);
//            startActivity(new Intent(ToiletActivity.this, ToiletMapsActivity.class));

        });

        getCurrentLocation();
        getToiletData();

    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);


    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.toilet_recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ToiletRecyclerViewAdapter(toiletItemArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void getToiletData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                BufferedInputStream inputStream = null;
                try {
                    URL url = new URL(
                            "http://data.sejong.go.kr/api/action/datastore_search.jsp?resource_id=8c31e7b8-bfee-47c8-bb7c-a746e6313ee7&serviceKey="
                                    + "nUVCZK/itjQn5Te5oRqRow=="
                    );

                    inputStream = new BufferedInputStream(url.openStream());
                    StringBuffer stringBuffer = new StringBuffer();

                    int i;
                    byte[] b = new byte[4096];
                    while ((i = inputStream.read(b)) != -1) {
                        stringBuffer.append(new String(b, 0, i));
                    }

                    jsonString = stringBuffer.toString();

                    Log.d("GET TOILET ====",jsonString);

                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONObject resultObject = (JSONObject) jsonObject.get("result");
                    JSONArray resultArray = resultObject.getJSONArray("records");
                    Log.d("RESULT JSON===", resultArray.toString());

                    toiletItemArrayList = getToiletList(resultArray);
                    Log.d("TOILET ARRAY LIST ==",toiletItemArrayList.toString());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setRecyclerView();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private ArrayList<ToiletItem> getToiletList(JSONArray resultArray) {
        ArrayList<ToiletItem> arrayList = new ArrayList<>();
        for (int i =0; i<resultArray.length(); i++){
            try {
                JSONObject jsonObject = (JSONObject) resultArray.get(i);
                String name = jsonObject.getString("화장실명");
                String tellNum = jsonObject.getString("전화번호");
                String address = jsonObject.getString("소재지지번주소");
                String openTime = jsonObject.getString("개방시간");
                double latitude = jsonObject.getDouble("위도");
                double longitude = jsonObject.getDouble("경도");

                Log.d("name length",String.valueOf(name));
                Log.d("tellnum length",String.valueOf(tellNum));
                Log.d("address length",String.valueOf(address));
                Log.d("opentime length",String.valueOf(openTime));
                Log.d("latitude length",String.valueOf(String.valueOf(latitude)));

                if(name.length() != 0 && tellNum.length()!=0 && address.length() != 0 && openTime.length() !=0 && String.valueOf(latitude).length() !=0 ){
                    arrayList.add(new ToiletItem(openTime,address,latitude,longitude,tellNum,name));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d("TOILET GET ARRAYLIST ==",arrayList.toString());
        return arrayList;
    }

}
