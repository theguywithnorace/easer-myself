package com.example.easermyself;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {

    //Keys for our bundle
    private static final String KEY_POSITION = "position";
    private static final String KEY_COLOR = "COLOR";


    public ViewPagerFragment() { /*Required empty public constructor */}

    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static ViewPagerFragment newInstance(int position, int color) {

        // 2.1 Create new fragment
        ViewPagerFragment viewPagerFragmentfrag = new ViewPagerFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);

        viewPagerFragmentfrag.setArguments(args);

        return(viewPagerFragmentfrag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 3 - Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_first_viewpager, container, false);

        // 4 - Get widgets from layout and serialise it
        LinearLayout rootView=  result.findViewById(R.id.linear_layout_rootview);
        TextView textView=  result.findViewById(R.id.text_banal);

        // 5 - Get data from Bundle (created in method newInstance)
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        // 6 - Update widgets with it
        rootView.setBackgroundColor(color);
        textView.setText("Page numéro " + position);

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }

}
