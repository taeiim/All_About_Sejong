package com.example.parktaeim.all_about_sejong.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.parktaeim.all_about_sejong.Adapter.ClubPagerAdapter;
import com.example.parktaeim.all_about_sejong.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_club);

        ViewPager viewPager = (ViewPager) findViewById(R.id.club_viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.club_tabLayout);
        ImageView backIcon = (ImageView) findViewById(R.id.club_backIcon);


        ClubPagerAdapter clubPagerAdapter = new ClubPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(clubPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        backIcon.setOnClickListener(v -> finish());
    }

}
