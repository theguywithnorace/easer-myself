package com.example.easermyself.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.easermyself.ViewPagerFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int[] colors;

    public PagerAdapter(FragmentManager mgr, int[] colors){
        super(mgr);
        this.colors = colors;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(position , this.colors[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
