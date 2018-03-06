package com.example.parktaeim.all_about_sejong.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.parktaeim.all_about_sejong.R;

/**
 * Created by parktaeim on 2018. 2. 13..
 */

public class GasStationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station);

        recyclerView = (RecyclerView) findViewById(R.id.gas_recyclerview);

    }
}
