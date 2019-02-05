package com.example.easermyself.registering;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.easermyself.R;
import com.example.easermyself.UserProfile;
import com.example.easermyself.base.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneNumberAsked extends BaseActivity {

    @Override
    public int getFragmentLayout() {  return R.layout.phone_number_asked;    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        setContentView(R.layout.phone_number_asked);
        ButterKnife.bind(this); //Configure Butterknife

        ImageView avatar = findViewById(R.id.avatar);
        if(this.getCurrentUser() != null && this.getCurrentUser().getPhotoUrl() != null)
            Glide.with(this)
                    .load(this.getCurrentUser().getPhotoUrl())
                    .into(avatar);

    }


    @OnClick(R.id.okButton)
    public void OnClickOkButton(){
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);

    }

    @Nullable
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }

}
