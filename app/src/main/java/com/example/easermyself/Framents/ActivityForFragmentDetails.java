package com.example.easermyself.Framents;

import android.util.Log;
import android.view.View;

import com.example.easermyself.R;
import com.example.easermyself.base.BaseActivity;

public class ActivityForFragmentDetails extends BaseActivity implements MyFragment.OnButtonClickedListener {
    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(),"Button2 clicked !");
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_for_fragment_details;
    }
}
