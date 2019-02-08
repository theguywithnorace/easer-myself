package com.example.easermyself.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.easermyself.R;
import com.example.easermyself.Base.BaseActivity;

public class ActivityForFragment extends BaseActivity implements MyFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment);

    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_for_fragment;
    }

    @Override
    public void onButtonClicked(View view) {


        Log.e(getClass().getSimpleName(),"Button clicked !");


        int buttonTag = Integer.parseInt(view.getTag().toString());

         Intent i = new Intent(this, MyFragmentDetails.class);
            i.putExtra(ActivityForFragmentDetails.EXTRA_BUTTON_TAG, buttonTag);
            startActivity(i);

    }
}
