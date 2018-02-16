package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class ProfileActivity extends Activity {
    @BindView(R.id.toolbarText)
    TextView topbatText ;
    @BindView(R.id.editText)
    TextView editProfile ;
    @BindView(R.id.left)
    TextView backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        ButterKnife.bind(this);
        topbatText.setText(getResources().getString(R.string.myProfile));
        editProfile.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.editText)
    void getEditProfile(){
        startActivity(new Intent(this,EditProfile_Activity.class));
    }
    @OnClick(R.id.left)
    void setBackbtn(){
        onBackPressed();
    }
}
