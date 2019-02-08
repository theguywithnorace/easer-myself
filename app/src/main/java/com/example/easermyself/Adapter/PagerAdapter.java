package com.example.easermyself.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.easermyself.MainFragments.ContactsFrag;
import com.example.easermyself.MainFragments.MapFrag;
import com.example.easermyself.MainFragments.ProfilesToMatchFrag;

public class PagerAdapter extends FragmentPagerAdapter {

    private int[] colors;

    public PagerAdapter(FragmentManager mgr){
        super(mgr);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) { //ici la variable position vient de la méthode getItem, de FragmentPagerAdapter itself

        switch (position) {
            case 0:
                return   MapFrag.newInstance(0);
            case 1:
                return   ProfilesToMatchFrag.newInstance(1);
            case 2:
                return   ContactsFrag.newInstance(2);
            default:
                return null;

        }
    }


}
