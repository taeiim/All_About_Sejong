package com.example.parktaeim.all_about_sejong.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parktaeim.all_about_sejong.Activity.ClubActivity;
import com.example.parktaeim.all_about_sejong.Adapter.ClubRecyclerViewAdapter;
import com.example.parktaeim.all_about_sejong.Model.ClubItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class EtcFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private ClubRecyclerViewAdapter clubAdapter;
    private ArrayList<ClubItem> etcClubArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    public static EtcFragment newInstance() {
        Bundle args = new Bundle();

        EtcFragment etcFragment = new EtcFragment();
        etcFragment.setArguments(args);

        return etcFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_etc, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.club_etc_recyclerview);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ClubActivity clubActivity = new ClubActivity();
        if(etcClubArrayList.size() == 0)  {
            etcClubArrayList = clubActivity.getClubArrayList("기타");
        }

        clubAdapter = new ClubRecyclerViewAdapter(etcClubArrayList,getContext());
        recyclerView.setAdapter(clubAdapter);

        return view;

    }
}
