package com.example.easermyself.MainFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easermyself.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilesToMatchFrag extends Fragment {
    private static final String KEY_POSITION = "position";

    public ProfilesToMatchFrag() { /*Required empty public constructor */}

    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static ProfilesToMatchFrag newInstance(int position) {
        // 2.1 Create new fragment
        ProfilesToMatchFrag profilesToMatchFrag = new ProfilesToMatchFrag();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        profilesToMatchFrag.setArguments(args);

        return(profilesToMatchFrag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int position = getArguments().getInt(KEY_POSITION, -1);

        View view = inflater.inflate(R.layout.o_fragment_map, container, false);

        TextView textView = view.findViewById(R.id.map_text);
        textView.setText("ProfilesToMatch, en Page numéro " + (position+1));

        Log.e(getClass().getSimpleName(), "onCreateView called for Map, fragment number "+position+" en base 0");

        return view;
    }
}
