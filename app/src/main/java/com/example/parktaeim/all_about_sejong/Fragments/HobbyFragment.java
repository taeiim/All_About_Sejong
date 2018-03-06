package com.example.parktaeim.all_about_sejong.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class HobbyFragment extends android.support.v4.app.Fragment{

    public static HobbyFragment newInstance(){
        Bundle args = new Bundle();

        HobbyFragment hobbyFragment = new HobbyFragment();
        hobbyFragment.setArguments(args);

        return hobbyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
