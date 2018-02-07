package com.example.parktaeim.all_about_sejong.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
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

    private ImageView backIcon;
    private LinearLayout callLayout;
    private CardView callCardView;

    private String tellNum;

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

        backIcon = (ImageView) findViewById(R.id.centerDetail_backIcon);
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
    }

    private void setData() {
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String type = intent.getExtras().getString("type");
        int cctvCount = intent.getExtras().getInt("cctvCount");
        int teacherCount = intent.getExtras().getInt("teacherCount");
        int playgroundCount = intent.getExtras().getInt("playgroundCount");
        int roomCount = intent.getExtras().getInt("roomCount");
        int studentCount = intent.getExtras().getInt("studentCount");
        String address = intent.getExtras().getString("address");
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

        if(isBus){
            isBusTv.setText("통학버스 운영");
        }else{
            isBusTv.setText("통학버스 운영안함");
        }


    }


    @Override

    public void onMapReady(final GoogleMap map) {
        LatLng SEOUL = new LatLng(37.56, 126.97);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
