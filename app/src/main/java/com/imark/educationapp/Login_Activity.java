package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/14/2018.
 */

public class Login_Activity extends Activity {
    @BindView(R.id.password)
    EditText password;
    private int passwordNotVisible=1;
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
    @OnClick(R.id.showIcon)
    void setShowPassword(){
        if (passwordNotVisible == 1) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordNotVisible = 0;
        } else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordNotVisible = 1;
        }
//ediText.setSelection(paswword.length());
    }
}
