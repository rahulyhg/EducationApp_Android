package com.imark.educationapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class EditProfile_Activity extends Activity{
    @BindView(R.id.toolbarText)
    TextView topBarText;
    @BindView(R.id.left)
    TextView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        ButterKnife.bind(this);
        topBarText.setText(getResources().getString(R.string.editProfile));
        backBtn.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.left)
    void setBackbtn(){
        onBackPressed();
    }
    @OnClick(R.id.update)
    void update(){
        onBackPressed();
    }
}
