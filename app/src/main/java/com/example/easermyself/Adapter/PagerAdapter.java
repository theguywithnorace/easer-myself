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
    public Fragment getItem(int position) { //ici la variable position vient de la méthode getItem, de FragmentPagerAdapter itself
        // si on a plusieurs Fragments, on peut choisir de les appeler ici en fonction de "position"
         return ViewPagerFragment.newInstance(position , this.colors[position]);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
