package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/14/2018.
 */

public class Login_Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.forgotBtn)
    void setForgotPassword(){
        startActivity(new Intent(this ,ForgotPassword_Activity.class));
    }
    @OnClick(R.id.singInBtn)
    void setLogin(){
        startActivity(new Intent(this ,Home_Activity.class));
    }
}
