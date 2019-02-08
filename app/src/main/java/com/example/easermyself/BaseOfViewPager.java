package com.example.easermyself;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.easermyself.Adapter.PagerAdapter;
import com.example.easermyself.Base.BaseActivity;
import com.example.easermyself.MainFragments.ContactsFrag;

public class BaseOfViewPager extends BaseActivity  implements ContactsFrag.OnButtonClickedListener {


    @Override
    public int getFragmentLayout() { return R.layout.base_of_viewpager;}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.configureViewPager();
    }

    // ----
    private void configureViewPager(){
        ViewPager pager = findViewById(R.id.viewpager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()){  });
        pager.setCurrentItem(1);
    }

    @Override
    public void onButtonClicked(View view) {
        Intent intent = new Intent(BaseOfViewPager.this, UserProfile.class);
        startActivity(intent);
    }
}
