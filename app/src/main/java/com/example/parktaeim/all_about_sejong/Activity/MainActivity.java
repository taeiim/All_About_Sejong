package com.example.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.parktaeim.all_about_sejong.Adapter.MainRecyclerAdapter;
import com.example.parktaeim.all_about_sejong.Model.MainItem;
import com.example.parktaeim.all_about_sejong.R;
import com.example.parktaeim.all_about_sejong.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by parktaeim on 2018. 1. 28..
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView daycarecenterImg;
    private CardView daycarecenterCardView;
    private CardView toiletCardView;
    private CardView clubCardView;
    private BannerSlider bannerSlider;
    private RecyclerView recyclerView;
    private MainRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MainItem> mainItemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();

        bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);

        List<Banner> banners = new ArrayList<>();
        banners.add(new RemoteBanner("http://cfile1.uf.tistory.com/image/275A934355E46C592A0445"));
        banners.add(new RemoteBanner("https://c-lj.gnst.jp/public/article/detail/a/00/00/a0000449/img/basic/a0000449_main.jpg?20170412195903"));
        banners.add(new RemoteBanner("http://i43.tinypic.com/2598wb7.jpg"));
//        banners.add(new DrawableBanner(R.drawable.drawable));

        bannerSlider.setBanners(banners);

    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);

        setUpRecyclerData();

        adapter = new MainRecyclerAdapter(mainItemArrayList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("=============",String.valueOf(position)+"=========");
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, DayCareCenterActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ToiletActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, GasStationActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ClubActivity.class));
                        break;

                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void setUpRecyclerData() {
        mainItemArrayList.add(new MainItem(R.drawable.icon_kindergarten, "어린이집"));
        mainItemArrayList.add(new MainItem(R.drawable.icon_toilet, "공중화장실"));
        mainItemArrayList.add(new MainItem(R.drawable.icon_gas_station, "주유소"));
        mainItemArrayList.add(new MainItem(R.drawable.icon_club, "동호회"));
        mainItemArrayList.add(new MainItem(R.drawable.icon_kindergarten, "어린이집"));
        mainItemArrayList.add(new MainItem(R.drawable.icon_kindergarten, "어린이집"));
        mainItemArrayList.add(new MainItem(R.drawable.icon_kindergarten, "어린이집"));
        mainItemArrayList.add(new MainItem(R.drawable.icon_kindergarten, "어린이집"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


}
