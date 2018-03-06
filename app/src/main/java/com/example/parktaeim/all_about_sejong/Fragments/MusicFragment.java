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

public class MusicFragment extends android.support.v4.app.Fragment {

    public static MusicFragment newInstance(){
        Bundle args = new Bundle();

        MusicFragment musicFragment = new MusicFragment();
        musicFragment.setArguments(args);

        return musicFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
