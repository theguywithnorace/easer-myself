package com.example.easermyself.MainFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easermyself.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFrag extends Fragment implements View.OnClickListener{

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

        return (contactsFrag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position = getArguments().getInt(KEY_POSITION, -1);

        View view = inflater.inflate(R.layout.o_fragment_contacts, container, false);

        //  button listener :
        view.findViewById(R.id.button_goto_user_profile).setOnClickListener(this);

        //textView
        TextView textView = view.findViewById(R.id.contact_text);
        textView.setText("Contacts, en Page numéro " + (position + 1));

        //toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_contact);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        getActivity().setTitle("Contacts");

        Log.e(getClass().getSimpleName(), "onCreateView called for contacts, fragment number "+position+" base 0");

        return view;
    }

    // ---- GO TO USER PROFILE --- With CALLBACK

    //2 - Declare callback
    private OnButtonClickedListener mCallback;

    // 1 - Declare our interface that will be implemented by any container activity
    public interface OnButtonClickedListener {
        public void onButtonClicked(View view);
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

    //---- MENU ----

    // menu itself
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the fragment menu items.
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_first_item:
                return true;
            case R.id.menu_second_item:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}