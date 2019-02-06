package com.example.easermyself;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.easermyself.Adapter.PagerAdapter;
import com.example.easermyself.Base.BaseActivity;

public class BaseOfViewPager extends BaseActivity {

    @Override
    public int getFragmentLayout() { return R.layout.base_of_viewpager;}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.configureViewPager();
    }

    private void configureViewPager(){
        ViewPager pager = findViewById(R.id.viewpager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)){
        });
    }
}
