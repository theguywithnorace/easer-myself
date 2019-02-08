package com.example.easermyself.MainFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easermyself.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFrag extends Fragment {
    private static final String KEY_POSITION = "position";

    public MapFrag() { /*Required empty public constructor */}

    public static MapFrag newInstance(int position) {

        // 2.1 Create new fragment
        MapFrag mapFrag = new MapFrag();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        mapFrag.setArguments(args);

        return(mapFrag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int position = getArguments().getInt(KEY_POSITION, -1);

        View view = inflater.inflate(R.layout.o_fragment_map, container, false);

//toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_map);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        getActivity().setTitle("Map");

        TextView textView = view.findViewById(R.id.map_text);
        textView.setText("Map, en Page numéro " + (position+1));

        Log.e(getClass().getSimpleName(), "onCreateView called for Map, fragment number "+position);

        return view;
    }

}
