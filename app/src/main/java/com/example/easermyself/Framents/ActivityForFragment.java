package com.example.easermyself.Framents;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.easermyself.R;
import com.example.easermyself.base.BaseActivity;

public class ActivityForFragment extends BaseActivity implements MyFragment.OnButtonClickedListener {
    @Override
    public int getFragmentLayout() {
        return R.layout.activity_for_fragment;
    }
    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(),"Button clicked !");
        Intent intent = new Intent(ActivityForFragment.this, ActivityForFragmentDetails.class);
        startActivity(intent);
    }
}
