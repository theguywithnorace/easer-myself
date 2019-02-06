package com.example.easermyself.Framents;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easermyself.R;

public class MyFragmentDetails extends Fragment implements  View.OnClickListener{

    public MyFragmentDetails() {
        // Required empty public constructor
    }
    private MyFragment.OnButtonClickedListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_fragment_details, container, false);

        view.findViewById(R.id.button2).setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    private void createCallbackToParentActivity() {
        try {
            //Parent activity will automatically subscribe to callback
            mListener = (MyFragment.OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onClick(View v) {
        mListener.onButtonClicked(v);
    }
}
