package com.example.parktaeim.all_about_sejong.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parktaeim.all_about_sejong.Adapter.ClubRecyclerViewAdapter;
import com.example.parktaeim.all_about_sejong.Model.ClubItem;
import com.example.parktaeim.all_about_sejong.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class SportsFragment extends android.support.v4.app.Fragment{

    private RecyclerView recyclerView;
    private ClubRecyclerViewAdapter clubAdapter;
    private ArrayList<ClubItem> sportsClubArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<ClubItem> flipList = new ArrayList<>();


    public static SportsFragment newInstance(){
        Bundle args = new Bundle();

        SportsFragment sportsFragment = new SportsFragment();
        sportsFragment.setArguments(args);

        return sportsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_sports,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.club_sports_recyclerView);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if(sportsClubArrayList.size() == 0)   getClubData();

        clubAdapter = new ClubRecyclerViewAdapter(sportsClubArrayList, flipList);
        recyclerView.setAdapter(clubAdapter);



        return view;
    }


    private void getClubData() {
        String clubData_json="";
        try {
            InputStream is = getActivity().getAssets().open("ClubData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            clubData_json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        getClubArrayList(clubData_json);
    }


    private void getClubArrayList(String json) {
        try {
            JSONArray clubJsonArray = new JSONArray(json);
            for(int i =0; i<clubJsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) clubJsonArray.get(i);
                String name = jsonObject.getString("TITLE");
                String tellNum = jsonObject.getString("DATA6");
                String regularMeeting = jsonObject.getString("DATA5");  //정기모임
                String clubType = jsonObject.getString("DATA4");
                String content = jsonObject.getString("CONTENT");
                String membershipFee = jsonObject.getString("DATA3");
                String memberCnt = jsonObject.getString("DATA2");
                String businessName = jsonObject.getString("COMPANY");  //상호명
                String category = jsonObject.getString("SECONDCATEGORY");
                String cafeUrl = jsonObject.getString("DATA14");

                if(category.equals("운동")){
                    sportsClubArrayList.add(new ClubItem(name,tellNum,regularMeeting,clubType,content,membershipFee,memberCnt,businessName,category,cafeUrl));
                }

                ClubItem clubItem = new ClubItem();
                clubItem.isFlipped = false;
                flipList.add(clubItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
