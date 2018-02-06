package com.example.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ToolbarWidgetWrapper;
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
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 1. 28..
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView daycarecenterImg;
    private CardView daycarecenterCardView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daycarecenterCardView = (CardView) findViewById(R.id.dayCareCenter_cardView);

        daycarecenterCardView.setOnClickListener(this);

        CardView detailCardView = (CardView) findViewById(R.id.detailCardView);
        detailCardView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DayCareCenterDetailActivity.class)));

        ImageView backImg = (ImageView) findViewById(R.id.mainBackImg);
//        Glide.with(this).load(R.drawable.img_beach).into(backImg);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dayCareCenter_cardView:
                startActivity(new Intent(MainActivity.this, DayCareCenterActivity.class));
                break;

        }
    }
}
