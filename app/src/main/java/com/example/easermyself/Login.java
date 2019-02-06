package com.example.easermyself;

import android.content.Intent;

import com.example.easermyself.Base.BaseActivity;
import com.example.easermyself.DataBase.api.UserHelper;
import com.example.easermyself.Registering.PhoneNumberAsked;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

import butterknife.OnClick;

public class Login extends BaseActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    public int getFragmentLayout() {return R.layout.login;}

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
    // 1 - Http request that create user in firestore

    private void createUserInFirestore(){
        if (this.getCurrentUser() != null){
            String urlPicture = (this.getCurrentUser().getPhotoUrl() != null) ? this.getCurrentUser().getPhotoUrl().toString() : null;
            String username = this.getCurrentUser().getDisplayName();
            String uid = this.getCurrentUser().getUid();

            UserHelper.createUser(uid, username, urlPicture);   //.addOnFailureListener(this.onFailureListener());
        }
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
                this.createUserInFirestore();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }
        }
    }




}
