package com.example.admin.fragmentspract;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.fragmentspract.BasicOperatnFrag;
import com.example.admin.fragmentspract.BasicOperatnFrag2;

/**
 * Created by Admin on 8/28/2016.
 */
public class SwipeAdapter extends FragmentPagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BasicOperatnFrag();
        } else {
            return new BasicOperatnFrag2();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
