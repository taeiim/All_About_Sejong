package com.example.parktaeim.all_about_sejong.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.parktaeim.all_about_sejong.Fragments.EtcFragment;
import com.example.parktaeim.all_about_sejong.Fragments.HobbyFragment;
import com.example.parktaeim.all_about_sejong.Fragments.MusicFragment;
import com.example.parktaeim.all_about_sejong.Fragments.SportsFragment;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubPagerAdapter extends FragmentPagerAdapter {

    private static int PAGER_COUNT = 4;

    public ClubPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SportsFragment.newInstance();
            case 1:
                return MusicFragment.newInstance();
            case 2:
                return HobbyFragment.newInstance();
            case 3:
                return EtcFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "스포츠";
            case 1:
                return "음악/악기";
            case 2:
                return "취미";
            case 3:
                return "기타";
            default:
                return null;
        }
    }
}
