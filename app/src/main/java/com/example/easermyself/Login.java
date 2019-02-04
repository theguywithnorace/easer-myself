package com.example.easermyself;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        setContentView(R.layout.login);
    }

    //Connexion via facebook
    AuthUI.IdpConfig provider = new AuthUI.IdpConfig.FacebookBuilder().build();

    // Create and launch sign-in intent
    startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
    RC_SIGN_IN);
}
