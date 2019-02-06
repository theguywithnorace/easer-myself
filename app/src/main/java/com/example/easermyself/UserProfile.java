package com.example.easermyself;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.easermyself.Framents.ActivityForFragment;
import com.example.easermyself.base.BaseActivity;
import com.example.easermyself.dataBase.api.UserHelper;
import com.example.easermyself.dataBase.models.User;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import butterknife.BindView;
import butterknife.OnClick;

public class UserProfile extends BaseActivity {

    @Override
    public int getFragmentLayout() { return R.layout.user_profile; }
    @BindView(R.id.profile_activity_imageview_profile) ImageView imageViewProfile;
    @BindView(R.id.profile_activity_edit_text_username) TextInputEditText textInputEditTextUsername;
    @BindView(R.id.profile_activity_text_view_email) TextView textViewEmail;
    @BindView(R.id.profile_activity_progress_bar) ProgressBar progressBar;
    @BindView(R.id.profile_activity_check_box_is_mentor) CheckBox checkBoxIsMentor;

    // For DATA
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;
    private static final int UPDATE_USERNAME = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.updateUIWhenCreating();
    }

    // to try fragments
    @OnClick(R.id.go_to_fragment)
    public void onClickGoToFragment(){
    Intent intent = new Intent(UserProfile.this, ActivityForFragment.class);
    startActivity(intent);
    }

    //back to the app
    @OnClick(R.id.profile_activity_check_box_is_mentor)
    public void onClickCheckboxIsMentor(){ this.updateUserIsMentor();}

    @OnClick(R.id.profile_activity_update_username)
    public void onClickUpdateUsername(){this.updateUsernameInFirebase();}

    @OnClick(R.id.profile_activity_button_sign_out)
    public void onClickSignOutButton(){ this.signOutUserFromFirebase(); }

    @OnClick(R.id.profile_activity_button_delete)
    public void onClickDeleteButton(){
        new AlertDialog.Builder(this)
                .setMessage(R.string.popup_message_confirmation_delete_account)
                .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteUserFromFirebase();
                    }
                })
                .setNegativeButton(R.string.popup_message_choice_no, null)
                .show();
    }

    private void updateUIWhenCreating(){
        if (this.getCurrentUser() != null){
            if (this.getCurrentUser().getPhotoUrl() != null) {
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .into(imageViewProfile);
            }

            // email recupéré avec firebase auth, et non Database
            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? "no email" : this.getCurrentUser().getEmail();
            this.textViewEmail.setText(email);

            // UserName and isMentor from Firestore
            UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(
                    new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            User currentUser = documentSnapshot.toObject(User.class);
                            String username = TextUtils.isEmpty(currentUser.getUsername()) ? "no username" : currentUser.getUsername();
                            checkBoxIsMentor.setChecked(currentUser.getIsMentor());
                            textInputEditTextUsername.setText(username);
                        }
                    });
        }
    }

    // --- Rest Requests ---
    private void signOutUserFromFirebase() {
        AuthUI.getInstance()
                   .signOut(this)
                   .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    private void deleteUserFromFirebase() {
        if (this.getCurrentUser() != null) {
            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));

            UserHelper.deleteUser(this.getCurrentUser().getUid()).addOnFailureListener(this.onFailureListener());
        }
    }


    //Update isMentor (is or not ?)
    private void updateUserIsMentor(){
       if(this.getCurrentUser() != null)
        UserHelper.updateIsMentor(this.getCurrentUser().getUid(), checkBoxIsMentor.isChecked()).addOnFailureListener(this.onFailureListener());
    }

    private void updateUsernameInFirebase() {
        this.progressBar.setVisibility(View.VISIBLE);

        String username = this.textInputEditTextUsername.getText().toString();
        if (this.getCurrentUser() != null){
            if (!username.isEmpty() &&  !username.equals("no username")){
                UserHelper.updateUsername(username, this.getCurrentUser().getUid()).addOnFailureListener(this.onFailureListener())
                                                                                    .addOnSuccessListener(this.updateUIAfterRESTRequestsCompleted(UPDATE_USERNAME));
            }
        }
    }

    // --- UI ---

    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin) {
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                switch (origin) {
                    case SIGN_OUT_TASK:
                        Intent intent = new Intent(UserProfile.this, Login.class);
                        startActivity(intent);
                        break;
                    case DELETE_USER_TASK:
                        finish();
                        Intent intentBis = new Intent(UserProfile.this, Login.class);
                        startActivity(intentBis);
                        break;
                    case UPDATE_USERNAME:
                        progressBar.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        break;
                }
            }
        };
    }

}
