package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.wang.avi.AVLoadingIndicatorView;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.LoginEntity;
import APIObject.SignUpObject;
import APIResponse.RegistrationResponse;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/14/2018.
 */

public class Login_Activity extends Activity {
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.editTextEmailUserName)
    EditText editTextEmailUserName;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    Call call;

    private int passwordNotVisible = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.forgotBtn)
    void setForgotPassword() {
        startActivity(new Intent(this, ForgotPassword_Activity.class));
    }

    @OnClick(R.id.singInBtn)
    void setLogin() {
        String emailAddress = editTextEmailUserName.getText().toString().trim();
        String paw = password.getText().toString().trim();
        if (emailAddress.equals("")) {
            editTextEmailUserName.setError(getString(R.string.plzEnterEmailId));
        } else if (!AppComman.getInstance(this).isEmailValid(emailAddress)) {
            editTextEmailUserName.setError(getString(R.string.emailValidation));
        } else if (paw.equals("")) {
            password.setError(getString(R.string.passwordValidation));
        } else
            callLoginCall(paw, emailAddress);

    }

    private void callLoginCall(String paw, String emailAddress) {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);

        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            call = educationService.caLoginResponseCall(new LoginEntity(emailAddress , paw,"android" , ""));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(Login_Activity.this).clearNonTouchableFlags(Login_Activity.this);
                    if (response != null) {
                        RegistrationResponse registrationResponse = (RegistrationResponse) response.body();
                        if (registrationResponse.getSuccess().equals("1")) {
                            getData(registrationResponse.getResult());
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(Login_Activity.this).clearNonTouchableFlags(Login_Activity.this);
                    AppComman.getInstance(Login_Activity.this).showDialog(Login_Activity.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }

    private void getData(SignUpObject result) {
        AppComman.getInstance(this).setUserLogin(result.getId());
        startActivity(new Intent(this, Home_Activity.class));
        finishAffinity();
    }

    @OnClick(R.id.showIcon)
    void setShowPassword() {
        if (passwordNotVisible == 1) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordNotVisible = 0;
        } else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordNotVisible = 1;
        }
    }

    @OnClick(R.id.singUp)
    void setSingUp() {

        startActivity(new Intent(this, SignUp_Activity.class));
    }
}
