package com.example.parktaeim.all_about_sejong.Activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.parktaeim.all_about_sejong.DayCareCenterItem;
import com.example.parktaeim.all_about_sejong.R;
import com.example.parktaeim.all_about_sejong.ToiletItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 2. 11..
 */

public class ToiletActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private String jsonString = null;
    ArrayList<ToiletItem> toiletItemArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.toilet_googleMap);
        mapFragment.getMapAsync(this);

        getToiletData();
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

                Log.d("name length",String.valueOf(name.length()));
                Log.d("tellnum length",String.valueOf(tellNum.length()));
                Log.d("address length",String.valueOf(address.length()));
                Log.d("opentime length",String.valueOf(openTime.length()));
                Log.d("latitude length",String.valueOf(String.valueOf(latitude).length()));

                if(name.length() != 0 && tellNum.length()!=0 && address.length() != 0 && openTime.length() !=0 && String.valueOf(latitude).length() !=0 ){
                    arrayList.add(new ToiletItem(openTime,address,latitude,longitude,tellNum,name));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(new LatLng(latitude,longitude));
                        markerOptions.title(name);
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bathroom_onmap));
                        googleMap.addMarker(markerOptions);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        Log.d("TOILET GET ARRAYLIST ==",arrayList.toString());
        return arrayList;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng dest = new LatLng(36.5055638, 127.2484251);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dest);
        markerOptions.title("아몰랑");

        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }
}
