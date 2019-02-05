package com.example.easermyself;

import android.content.Intent;
import android.os.Bundle;

import com.example.easermyself.base.BaseActivity;
import com.example.easermyself.registering.PhoneNumberAsked;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends BaseActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    public int getFragmentLayout() {return R.layout.login;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.connexionButton)
    public void OnClickConnexionButton(){
        if(this.isCurrentUserLogged())
            this.startNextActivity();
        this.startFirebaseSignInActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.isCurrentUserLogged())
            this.startUserProfileActivity();

    }

    private void startFirebaseSignInActivity() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Collections.singletonList(
                                        new AuthUI.IdpConfig.FacebookBuilder().build())
                        )
                        .build(),
                RC_SIGN_IN);
    }

    private void startNextActivity() {
        Intent intent = new Intent(Login.this, PhoneNumberAsked.class);
        startActivity(intent);
    }

    private void startUserProfileActivity() {
        Intent intent = new Intent(Login.this, UserProfile.class);
        startActivity(intent);
    }
    // Result on connection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            }
        }
    }




}
