package com.god.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.god.parktaeim.all_about_sejong.Adapter.MainRecyclerAdapter;
import com.god.parktaeim.all_about_sejong.InfoDialog;
import com.god.parktaeim.all_about_sejong.Model.MainItem;
import com.god.parktaeim.all_about_sejong.R;
import com.god.parktaeim.all_about_sejong.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by parktaeim on 2018. 1. 28..
 */

public class MainActivity extends AppCompatActivity {
    private BannerSlider bannerSlider;
    private RecyclerView recyclerView;
    private MainRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MainItem> mainItemArrayList = new ArrayList<>();
    private LinearLayout parentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setUpRecyclerView();
        setUpBanner();
        setUpInfoDialog();

    }

    private void init() {
        parentLayout = (LinearLayout) findViewById(R.id.main_parentLayout);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
    }

    private void setUpBanner() {
        bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);

        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.img_banner1));
        banners.add(new DrawableBanner(R.drawable.img_banner2));
        banners.add(new DrawableBanner(R.drawable.img_banner3));
//        banners.add(new DrawableBanner(R.drawable.drawable));

        bannerSlider.setBanners(banners);
    }

    private void setUpRecyclerView() {
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);

        setUpRecyclerData();

        adapter = new MainRecyclerAdapter(mainItemArrayList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
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
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        Snackbar snackbar = Snackbar.make(parentLayout, "업데이트 예정입니다. 조금만 기다려주세요.", 2000)
                                .setAction("확인", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                        TextView snackbarTv = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                        TextView snackbarBtnTv = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_action);
                        snackbarTv.setTextSize(11);
                        snackbarBtnTv.setTextSize(12);
                        snackbar.show();
                        break;
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void setUpRecyclerData() {
        mainItemArrayList.add(new MainItem(R.drawable.icon_kindergarten, "어린이집",true));
        mainItemArrayList.add(new MainItem(R.drawable.icon_toilet, "공중화장실",true));
        mainItemArrayList.add(new MainItem(R.drawable.icon_gas_station, "주유소",true));
        mainItemArrayList.add(new MainItem(R.drawable.icon_club, "동호회",true));
        mainItemArrayList.add(new MainItem(R.drawable.icon_bicycle, "자전거 어울링",false));
        mainItemArrayList.add(new MainItem(R.drawable.icon_hospital, "병원",false));
        mainItemArrayList.add(new MainItem(R.drawable.icon_medicine, "약국",false));
        mainItemArrayList.add(new MainItem(R.drawable.icon_weather, "날씨",false));

    }

    private void setUpInfoDialog() {
        String infoStr = "어울링, 병원, 약국, 날씨 등\n여러 기능 추가 업데이트 예정!!" + "\n\n"+
                "문의사항, 버그신고,\n추가되었으면 하는 기능 등이 있으시면\n"+"taeiim.god @ gmail.com"+"\n으로 이메일 보내주세요!!" + "\n\n"+
                "어린이집, 화장실, 동호회 데이터 출처 :\n세종특별자치시 데이터 실록";

        ImageView infoIcon = (ImageView) findViewById(R.id.main_infoIcon);
        infoIcon.setOnClickListener(v->{
            FragmentManager fragmentManager = getSupportFragmentManager();
            InfoDialog infoDialog = new InfoDialog();

            Bundle args = new Bundle();
            args.putString("infoText",infoStr);
            args.putInt("defaultTextSize",14);

            infoDialog.setArguments(args);
            infoDialog.show(fragmentManager,"infoText");
        });
    }
}
