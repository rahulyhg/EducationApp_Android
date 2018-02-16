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

public class SignUp_Activity extends Activity {
    private int passwordNotVisible=1;
    private int passwordNotVisible1=1;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.cmfPassword)
    EditText cmfPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.SignIn)
    void setSignIn(){
        startActivity(new Intent(this , Login_Activity.class));
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
    @OnClick(R.id.showIcon2)
    void setShowcmfPassword(){
        if (passwordNotVisible1 == 1) {
            cmfPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordNotVisible1 = 0;
        } else {
            cmfPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordNotVisible1 = 1;
        }
//ediText.setSelection(paswword.length());
    }
}
