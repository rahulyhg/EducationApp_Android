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
    }

    @OnClick(R.id.editText)
    void getEditProfile() {
        startActivity(new Intent(this, EditProfile_Activity.class));
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
}
