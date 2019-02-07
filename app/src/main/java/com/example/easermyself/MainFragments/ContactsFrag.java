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
public class ContactsFrag extends Fragment {
    private static final String KEY_POSITION = "position";

    public ContactsFrag() { /*Required empty public constructor */}

    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static ContactsFrag newInstance(int position) {
        // 2.1 Create new fragment
        ContactsFrag contactsFrag = new ContactsFrag();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        contactsFrag.setArguments(args);

        return(contactsFrag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int position = getArguments().getInt(KEY_POSITION, -1);

        View view = inflater.inflate(R.layout.o_fragment_contacts, container, false);

        TextView textView = view.findViewById(R.id.contact_text);
        textView.setText("Contacts, en Page numéro " + (position+1));

        Log.e(getClass().getSimpleName(), "onCreateView called for contacts, fragment number "+position+" base 0");

        return view;
    }

}
