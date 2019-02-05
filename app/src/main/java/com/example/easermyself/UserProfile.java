package com.example.easermyself;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.easermyself.base.BaseActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.OnClick;

public class UserProfile extends BaseActivity {


    @Override
    public int getFragmentLayout() { return R.layout.user_profile; }
    // For DATA
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;

    @BindView(R.id.profile_activity_imageview_profile)
    ImageView imageViewProfile;
    @BindView(R.id.profile_activity_edit_text_username)
    TextInputEditText textInputEditTextUsername;
    @BindView(R.id.profile_activity_text_view_email)
    TextView textViewEmail;
    @BindView(R.id.profile_activity_progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.updateUIWhenCreating();
    }

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
        if(this.getCurrentUser() != null){
            if(this.getCurrentUser().getPhotoUrl() != null){
                //download profile picture
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .into(imageViewProfile);
                //set email
                String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? "not found"
                        : this.getCurrentUser().getEmail();
                String username = TextUtils.isEmpty(this.getCurrentUser().getDisplayName()) ? "no username"
                        : this.getCurrentUser().getDisplayName();
                textViewEmail.setText(email);
                textInputEditTextUsername.setText(username);

            }
        }
    }

    private void signOutUserFromFirebase() {
        AuthUI.getInstance()
                   .signOut(this)
                   .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK))
        ;
    }

    private void deleteUserFromFirebase() {
        AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK))
        ;


    }
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
                    default:
                        break;
                }
            }
        };
    }

}
