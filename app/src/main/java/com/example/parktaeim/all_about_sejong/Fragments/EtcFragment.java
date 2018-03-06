package com.example.parktaeim.all_about_sejong.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private ArrayList<ClubItem> clubItemArrayList = new ArrayList<>();
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


        clubItemArrayList.add(new ClubItem("기타치는 동호회", 20));
        clubItemArrayList.add(new ClubItem("zzzzzzzzzz", 100));

        clubAdapter = new ClubRecyclerViewAdapter(clubItemArrayList);
        recyclerView.setAdapter(clubAdapter);

        return view;

    }
}
