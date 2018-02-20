package com.imark.educationapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.ForgotEntity;
import APIObject.LoginEntity;
import APIResponse.CommonResponse;
import APIResponse.RegistrationResponse;
import Infrastructure.AppComman;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/14/2018.
 */

public class ForgotPassword_Activity extends Activity {
    @BindView(R.id.toolbarText)
    TextView topBar;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    Call call;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;

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
     String email = editTextEmail.getText().toString().trim();
     if(email.equals(""))
         editTextEmail.setError(getResources().getString(R.string.plzEnterEmailId));
     else if(!AppComman.getInstance(this).isEmailValid(email))
         editTextEmail.setError(getResources().getString(R.string.emailValidation));
     else
         callForgotPasswordApi(email);
    }

    private void callForgotPasswordApi(String email) {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            call = educationService.forgotPasswordCall(new ForgotEntity(email));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(ForgotPassword_Activity.this).clearNonTouchableFlags(ForgotPassword_Activity.this);
                    if (response != null) {
                        CommonResponse commonResponse = (CommonResponse) response.body();
                        if (commonResponse.getSuccess().equals("1")) {
                            AppComman.getInstance(ForgotPassword_Activity.this).showDialogWithFinish(ForgotPassword_Activity.this , commonResponse.getResult());
                        } else {
                            if (commonResponse != null)
                                AppComman.getInstance(ForgotPassword_Activity.this).showDialog(ForgotPassword_Activity.this, commonResponse.getError());
                            else
                                AppComman.getInstance(ForgotPassword_Activity.this).showDialog(ForgotPassword_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(ForgotPassword_Activity.this).showDialog(ForgotPassword_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(ForgotPassword_Activity.this).clearNonTouchableFlags(ForgotPassword_Activity.this);
                    AppComman.getInstance(ForgotPassword_Activity.this).showDialog(ForgotPassword_Activity.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null)
            call.cancel();
    }
}
