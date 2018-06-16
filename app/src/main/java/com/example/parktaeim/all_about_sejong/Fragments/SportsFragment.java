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

import com.example.parktaeim.all_about_sejong.Activity.ClubActivity;
import com.example.parktaeim.all_about_sejong.Adapter.ClubRecyclerViewAdapter;
import com.example.parktaeim.all_about_sejong.Model.ClubItem;
import com.example.parktaeim.all_about_sejong.R;

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

        ClubActivity clubActivity = new ClubActivity();

        if(sportsClubArrayList.size() == 0)  {
            sportsClubArrayList = clubActivity.getClubArrayList("운동");
        }

        clubAdapter = new ClubRecyclerViewAdapter(sportsClubArrayList,getContext());
        recyclerView.setAdapter(clubAdapter);

        return view;
    }
}
