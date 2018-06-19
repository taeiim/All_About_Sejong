package com.god.parktaeim.all_about_sejong.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.god.parktaeim.all_about_sejong.Adapter.ClubPagerAdapter;
import com.god.parktaeim.all_about_sejong.InfoDialog;
import com.god.parktaeim.all_about_sejong.Model.ClubItem;
import com.god.parktaeim.all_about_sejong.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubActivity extends AppCompatActivity {
    ArrayList<ClubItem> sportsClubList = new ArrayList<>();
    ArrayList<ClubItem> musicClubList = new ArrayList<>();
    ArrayList<ClubItem> hobbyClubList = new ArrayList<>();
    ArrayList<ClubItem> etcClubList = new ArrayList<>();

    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_club);

        ViewPager viewPager = (ViewPager) findViewById(R.id.club_viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.club_tabLayout);
        ImageView backIcon = (ImageView) findViewById(R.id.club_backIcon);

        ClubPagerAdapter clubPagerAdapter = new ClubPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(clubPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        backIcon.setOnClickListener(v -> finish());

        getClubData();
        setUpInfoDialog();
    }

    public ArrayList<ClubItem> getClubArrayList(String category) {
        getClubData();

        ArrayList<ClubItem> returnClubList = new ArrayList<>();
        if (sportsClubList != null) {
            if (category.equals("운동")) {
                returnClubList.addAll(sportsClubList);
            } else if (category.equals("음악")) {
                returnClubList.addAll(musicClubList);
            } else if (category.equals("취미")) {
                returnClubList.addAll(hobbyClubList);
            } else if (category.equals("기타")) {
                returnClubList.addAll(etcClubList);
            }
        } else getClubData();

        return returnClubList;
    }

    private void getClubData() {
        String clubData_json = "";
        try {
            InputStream is = context.getAssets().open("ClubData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            clubData_json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
        }

        makeClubArrayList(clubData_json);
    }


    private void makeClubArrayList(String json) {
        try {
            JSONArray clubJsonArray = new JSONArray(json);
            for (int i = 0; i < clubJsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) clubJsonArray.get(i);
                String name = jsonObject.getString("TITLE");
                String tellNum = jsonObject.getString("DATA6");
                String regularMeeting = jsonObject.getString("DATA5");  //정기모임
                String clubType = jsonObject.getString("DATA4");
                String content = jsonObject.getString("CONTENT");
                String membershipFee = jsonObject.getString("DATA3");
                String memberCnt = jsonObject.getString("DATA2");
                String leader = jsonObject.getString("DATA1");
                String businessName = jsonObject.getString("COMPANY");  //상호명
                String category = jsonObject.getString("SECONDCATEGORY");
                String cafeUrl = jsonObject.getString("DATA14");

                if (category.equals("운동")) {
                    sportsClubList.add(new ClubItem(name, tellNum, regularMeeting, clubType, content, membershipFee, memberCnt, leader,businessName, category, cafeUrl));
                } else if (category.equals("음악")) {
                    musicClubList.add(new ClubItem(name, tellNum, regularMeeting, clubType, content, membershipFee, memberCnt, leader, businessName, category, cafeUrl));
                } else if (category.equals("취미")) {
                    hobbyClubList.add(new ClubItem(name, tellNum, regularMeeting, clubType, content, membershipFee, memberCnt, leader, businessName, category, cafeUrl));
                } else if (category.equals("기타")) {
                    etcClubList.add(new ClubItem(name, tellNum, regularMeeting, clubType, content, membershipFee, memberCnt, leader, businessName, category, cafeUrl));
                } else {
                    //Error
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setUpInfoDialog() {
        String infoStr = "동호회 정보는 2015년 12월 기준입니다.\n\n카드뷰를 클릭하시면 해당 동호회의 상세정보를 보실 수 있습니다.";
        String infoSmallStr = "데이터 출처 : 세종특별자치시 데이터 실록";

        ImageView infoIcon = (ImageView) findViewById(R.id.club_infoIcon);
        infoIcon.setOnClickListener(v->{
            FragmentManager fragmentManager = getSupportFragmentManager();
            InfoDialog infoDialog = new InfoDialog();

            Bundle args = new Bundle();
            args.putString("infoText",infoStr);
            args.putString("smallText",infoSmallStr);

            infoDialog.setArguments(args);
            infoDialog.show(fragmentManager,"infoText");
        });
    }
}
