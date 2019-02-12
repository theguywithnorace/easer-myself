package com.example.easermyself.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easermyself.R;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactsViewHolder> {

    //For data
    private List<MyContacts> myContactsList;

    //Constructor
    public ContactRecyclerAdapter(List<MyContacts> myContactsList) {
        this.myContactsList = myContactsList;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);
        return ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder contactsViewHolder, int i) {
        contactsViewHolder.updateWithMyContacts(this.myContactsList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.myContactsList.size();
    }



}
