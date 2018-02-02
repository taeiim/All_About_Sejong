package com.example.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.parktaeim.all_about_sejong.R;

/**
 * Created by parktaeim on 2018. 1. 28..
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView daycarecenterImg;
    private CardView daycarecenterCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daycarecenterCardView = (CardView) findViewById(R.id.dayCareCenter_cardView);

        daycarecenterCardView.setOnClickListener(this);

        CardView detailCardView = (CardView) findViewById(R.id.detailCardView);
        detailCardView.setOnClickListener(v-> startActivity(new Intent(MainActivity.this,DayCareCenterDetailActivity.class)));
  }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dayCareCenter_cardView:
                startActivity(new Intent(MainActivity.this,DayCareCenterActivity.class));
                break;

        }
    }
}
