package com.imark.educationapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import APIObject.SignUpObject;
import Infrastructure.AppComman;
import Infrastructure.MyPreference;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class ProfileActivity extends Activity {
    @BindView(R.id.toolbarText)
    TextView topbatText;
    @BindView(R.id.editText)
    TextView editProfile;
    @BindView(R.id.left)
    TextView backBtn;
    @BindView(R.id.editTextEmailUserName)
    TextView editTextEmailUserName;
    @BindView(R.id.editTextUnivercity)
    TextView editTextUnivercity;
    @BindView(R.id.editTextNumPostViews)
    TextView editTextNumPostViews;
    @BindView(R.id.editTextEmaillastDateSign)
    TextView editTextEmaillastDateSign;
    @BindView(R.id.editTextmemberSince)
    TextView editTextmemberSince;
    @BindView(R.id.editTextdateOfBirth)
    TextView editTextdateOfBirth;

    @BindView(R.id.userProfile)
    SimpleDraweeView userProfile;
    SignUpObject signUpObject;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        ButterKnife.bind(this);
        topbatText.setText(getResources().getString(R.string.myProfile));
        editProfile.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.blue_color));
        getData();
    }

    private void getData() {
        signUpObject = new Gson().fromJson(AppComman.getInstance(this).getUserObject(), SignUpObject.class);
        if(signUpObject != null){
            if(!signUpObject.getName().equals(""))
                editTextEmailUserName.setText(signUpObject.getName());
            editTextUnivercity.setText(signUpObject.getUniversity());
            editTextNumPostViews.setText(signUpObject.getViews());
            editTextEmaillastDateSign.setText(signUpObject.getLastLogin());
            editTextdateOfBirth.setText(signUpObject.getDateOfBirth());
            editTextmemberSince.setText(signUpObject.getMenberFrom());
            userProfile.setController(AppComman.getDraweeController(userProfile , signUpObject.getProfilePic(),500));
        }
    }

    @OnClick(R.id.editText)
    void getEditProfile() {
        startActivityForResult(new Intent(this, EditProfile_Activity.class) , MyPreference.editProfile);
    }

    @OnClick(R.id.left)
    void setBackbtn() {
        onBackPressed();
    }

    @OnClick(R.id.signUpBtn)
    void setSignOut() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getResources().getString(R.string.app_name));
        adb.setMessage(getResources().getString(R.string.log_out));
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppComman.getInstance(ProfileActivity.this).clearPreference();
                startActivity(new Intent(ProfileActivity.this, SelectionActivity.class));
                finishAffinity();
            }

        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        adb.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MyPreference.editProfile && resultCode == RESULT_OK){
            getData();
        }
    }
}
