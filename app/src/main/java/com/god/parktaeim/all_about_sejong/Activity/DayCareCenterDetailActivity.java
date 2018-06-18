package com.god.parktaeim.all_about_sejong.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by parktaeim on 2018. 2. 2..
 */

public class DayCareCenterDetailActivity extends AppCompatActivity implements OnMapReadyCallback{

    private TextView nameTv;
    private TextView cctvCountTv;
    private TextView teacherCountTv;
    private TextView studentCountTv;
    private TextView roomCountTv;
    private TextView typeTv;
    private TextView isBusTv;
    private TextView tellNumTv;
    private TextView addressTv;
    private TextView playgroundCountTv;

    private ImageView backIcon;
    private ImageView isBusImgView;
    private ImageView centerTypeImgView;
    private LinearLayout callLayout;
    private CardView callCardView;

    private String tellNum;
    private String address;
    private String name;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_care_center_detail);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        nameTv = (TextView) findViewById(R.id.centerDetail_nameTv);
        cctvCountTv = (TextView) findViewById(R.id.cctvCountTv);
        teacherCountTv = (TextView) findViewById(R.id.teacherCountTv);
        studentCountTv = (TextView) findViewById(R.id.studentCountTv);
        roomCountTv = (TextView) findViewById(R.id.roomCountTv);
        typeTv = (TextView)  findViewById(R.id.centerDetail_typeTv);
        isBusTv = (TextView) findViewById(R.id.centerDetail_isBusTv);
        tellNumTv = (TextView) findViewById(R.id.centerDetail_tellNumTv);
        addressTv = (TextView) findViewById(R.id.centerDetail_addressTv);
        playgroundCountTv = (TextView) findViewById(R.id.playgroundCountTv);

        backIcon = (ImageView) findViewById(R.id.centerDetail_backIcon);
        isBusImgView = (ImageView) findViewById(R.id.centerDetail_isBus_ImageView);
        centerTypeImgView = (ImageView) findViewById(R.id.centerDetail_type_img);

        callCardView = (CardView) findViewById(R.id.centerDetail_callCardView);
        callLayout = (LinearLayout) findViewById(R.id.callLayout);

        backIcon.setOnClickListener(v -> finish());
        callLayout.setOnClickListener(v-> {
            if(callCardView.getVisibility() == View.GONE){
                callCardView.setVisibility(View.VISIBLE);
            } else if(callCardView.getVisibility() == View.VISIBLE){
                callCardView.setVisibility(View.GONE);
            }
        });
        callCardView.setOnClickListener(v -> {
            startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:"+tellNum)));
        });

        setData();
        getLatLon();
    }

    private void getLatLon() {
        String clientId = "eHm6juoXIARjUBibf0n1";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "h7O6vSoxwj";//애플리케이션 클라이언트 시크릿값";
        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    // Naver API (주소 -> 좌표)
                    String addr = URLEncoder.encode(address, "UTF-8");
                    String apiURL = "https://openapi.naver.com/v1/map/geocode?query=" + addr;
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    int responseCode = con.getResponseCode();
                    BufferedReader br;
                    if(responseCode==200) {
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }
                    br.close();

                    String jsonString = response.toString();

                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONObject resultObject = (JSONObject) jsonObject.get("result");
                    JSONArray resultArray = resultObject.getJSONArray("items");
                    JSONObject resultObject2 = (JSONObject) resultArray.get(0);
                    JSONObject pointObject = (JSONObject) resultObject2.getJSONObject("point");

                    double lon = pointObject.getDouble("x");
                    double lat = pointObject.getDouble("y");

                    setUpMap(lat,lon);

                } catch (Exception e) {
                    System.out.println("ERROR!!!! 주소변환 에러!!!");
                    System.out.println(e);
                }

            }
        }.start();

    }

    private void setUpMap(double lat, double lon){
        LatLng dest = new LatLng(lat, lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dest);
        markerOptions.title(name);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
            }
        });

    }

    private void setData() {
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        String type = intent.getExtras().getString("type");
        int cctvCount = intent.getExtras().getInt("cctvCount");
        int teacherCount = intent.getExtras().getInt("teacherCount");
        int playgroundCount = intent.getExtras().getInt("playgroundCount");
        int roomCount = intent.getExtras().getInt("roomCount");
        int studentCount = intent.getExtras().getInt("studentCount");
        address = intent.getExtras().getString("address");
        Boolean isBus = intent.getExtras().getBoolean("isCar");
        tellNum = intent.getExtras().getString("tellNum");

        nameTv.setText(name);
        typeTv.setText(type);
        cctvCountTv.setText(String.valueOf(cctvCount));
        teacherCountTv.setText(String.valueOf(teacherCount));
        roomCountTv.setText(String.valueOf(roomCount));
        studentCountTv.setText(String.valueOf(studentCount));
        addressTv.setText(address);
        tellNumTv.setText(tellNum);
        playgroundCountTv.setText(String.valueOf(playgroundCount));

        if(isBus){
            isBusTv.setText("통학버스 운영");
            isBusImgView.setImageResource(R.drawable.icon_school_bus);
        }else{
            isBusTv.setText("통학버스 운영안함");
            isBusImgView.setImageResource(R.drawable.icon_nobus);
        }

        if(type.equals("민간")){
            centerTypeImgView.setImageResource(R.drawable.icon_center_type_civilian);
        }else if(type.equals("가정")){
            centerTypeImgView.setImageResource(R.drawable.icon_center_type_home);
        }else if(type.equals("직장")){
            centerTypeImgView.setImageResource(R.drawable.icon_center_type_company);
        }
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        googleMap = map;
    }
}
