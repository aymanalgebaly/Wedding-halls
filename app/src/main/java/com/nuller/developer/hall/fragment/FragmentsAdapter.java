package com.nuller.developer.hall.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nuller.developer.hall.fragment.InviteFragment;
import com.nuller.developer.hall.fragment.MainFragment;
import com.nuller.developer.hall.fragment.OffrersFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {

    Fragment fragments []= {new MainFragment(),new InviteFragment(),new OffrersFragment()};




    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
