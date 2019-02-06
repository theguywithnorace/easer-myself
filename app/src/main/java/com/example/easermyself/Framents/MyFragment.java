package com.example.easermyself.Framents;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easermyself.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener {


    public MyFragment() {
        // Required empty public constructor
    }

    //2 - Declare callback
    private OnButtonClickedListener mCallback;

    // 1 - Declare our interface that will be implemented by any container activity
    public interface OnButtonClickedListener {
        public void onButtonClicked(View view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        view.findViewById(R.id.show_me_details).setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 4 - Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    // --------------
    // ACTIONS
    // --------------

    @Override
    public void onClick(View v) {
        // 5 - Spread the click to the parent activity
        mCallback.onButtonClicked(v);
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    // 3 - Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

}
