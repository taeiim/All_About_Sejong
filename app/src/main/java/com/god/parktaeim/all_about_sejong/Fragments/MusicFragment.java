package com.god.parktaeim.all_about_sejong.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.god.parktaeim.all_about_sejong.Activity.ClubActivity;
import com.god.parktaeim.all_about_sejong.Adapter.ClubRecyclerViewAdapter;
import com.god.parktaeim.all_about_sejong.Model.ClubItem;
import com.god.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class MusicFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private ClubRecyclerViewAdapter clubAdapter;
    private ArrayList<ClubItem> musicClubArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    public static MusicFragment newInstance(){
        Bundle args = new Bundle();

        MusicFragment musicFragment = new MusicFragment();
        musicFragment.setArguments(args);

        return musicFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_music,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.club_music_recyclerview);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ClubActivity clubActivity = new ClubActivity();
        if(musicClubArrayList.size() == 0)  {
            musicClubArrayList = clubActivity.getClubArrayList("음악");
        }

        clubAdapter = new ClubRecyclerViewAdapter(musicClubArrayList,getContext());
        recyclerView.setAdapter(clubAdapter);

        return view;
    }
}
