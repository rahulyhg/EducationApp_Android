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
 * Created by User on 2/14/2018.
 */

public class ForgotPassword_Activity extends Activity {
    @BindView(R.id.toolbarText)
    TextView topBar;
    @BindView(R.id.left)
    TextView left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);
        topBar.setText(getResources().getString(R.string.forgotPassword));
        left.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id. left)
    void setbackbtn(){
        onBackPressed();
    }

    @OnClick(R.id. submit)
    void setSubmit(){
        onBackPressed();
    }
}
