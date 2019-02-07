package com.example.easermyself;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ViewPagerFragment extends Fragment {

    //Keys for our bundle
    private static final String KEY_POSITION = "position";
    private static final String KEY_COLOR = "COLOR";


    public ViewPagerFragment() { /*Required empty public constructor */}

    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static ViewPagerFragment newInstance(int position, int color) {

        // 2.1 Create new fragment
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);

        viewPagerFragment.setArguments(args);

        return(viewPagerFragment);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        View result = null;
        LinearLayout rootView = null;

        if(position<3){
            result = inflater.inflate(R.layout.fragment_viewpager, container, false);
             rootView=  result.findViewById(R.id.linear_layout_rootview);
            rootView.setBackgroundColor(color);

        }
        else{
            result = inflater.inflate(R.layout.fragment_viewpager2, container, false);
             rootView=  result.findViewById(R.id.linearlayout);
        }


        TextView textView =  result.findViewById(R.id.text_banal);

        rootView.setBackgroundColor(color);
        textView.setText("Page numéro " + position);

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }
}

