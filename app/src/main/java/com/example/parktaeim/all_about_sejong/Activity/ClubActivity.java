package com.example.parktaeim.all_about_sejong.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.parktaeim.all_about_sejong.Adapter.ClubPagerAdapter;
import com.example.parktaeim.all_about_sejong.R;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        ClubPagerAdapter clubPagerAdapter = new ClubPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.club_viewPager);
        viewPager.setAdapter(clubPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.club_tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }


}
