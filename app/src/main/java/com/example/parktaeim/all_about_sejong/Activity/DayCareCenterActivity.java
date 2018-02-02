package com.example.parktaeim.all_about_sejong.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.AllCenterItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 2. 2..
 */

public class DayCareCenterActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView backIcon;
    private TextView nearTextView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AllCenterRecyclerViewAdapter adapter;
    private ArrayList<AllCenterItem> allCenterItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_care_center);

        backIcon = (ImageView) findViewById(R.id.center_backIcon);
        backIcon.setOnClickListener(v->finish());

        setRecycerView();
    }

    private void setRecycerView() {
        recyclerView = (RecyclerView) findViewById(R.id.allCenter_recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        adapter = new AllCenterRecyclerViewAdapter(allCenterItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));
        allCenterItems.add(new AllCenterItem("예쁜별어린이집","세종특별자치시 도움1로 105 가재마을 5단지 512-102",true,1));

    }

    @Override
    public void onClick(View v) {

    }
}
