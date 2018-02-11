package com.example.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.parktaeim.all_about_sejong.DayCareCenterItem;
import com.example.parktaeim.all_about_sejong.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 1. 28..
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView daycarecenterImg;
    private CardView daycarecenterCardView;
    private CardView toiletCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daycarecenterCardView = (CardView) findViewById(R.id.dayCareCenter_cardView);
        toiletCardView = (CardView) findViewById(R.id.toiletCardView);

        daycarecenterCardView.setOnClickListener(this);
        toiletCardView.setOnClickListener(this);

        ImageView backImg = (ImageView) findViewById(R.id.mainBackImg);
//        Glide.with(this).load(R.drawable.img_beach).into(backImg);

        getAppKeyHash();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dayCareCenter_cardView:
                startActivity(new Intent(MainActivity.this, DayCareCenterActivity.class));
                break;

            case R.id.toiletCardView:
                startActivity(new Intent(MainActivity.this, ToiletActivity.class));
                break;

        }
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
// TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }

}
