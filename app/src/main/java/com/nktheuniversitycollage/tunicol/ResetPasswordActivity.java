package com.nktheuniversitycollage.tunicol;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.ResetPasswordEntity;
import APIObject.SocialLoginObj;
import APIResponse.CommonResponse;
import APIResponse.RegistrationResponse;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/15/2018.
 */

public class ResetPasswordActivity extends Activity {
    @BindView(R.id.toolbarText)
    TextView topbarText;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.oldPwd)
    EditText oldPwd;
    @BindView(R.id.newPwd)
    EditText newPwd;
    @BindView(R.id.cmfPwd)
    EditText cmfPwd;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
   Call call;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        ButterKnife.bind(this);
        topbarText.setText(getResources().getString(R.string.resetPassword));
        left.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.left)
    void setBack(){
        onBackPressed();
    }
    @OnClick(R.id.update)
    void setUpdate() {
        String oldStr = oldPwd.getText().toString().trim();
        String newStr = newPwd.getText().toString().trim();
        String cmfStr = cmfPwd.getText().toString().trim();
        if (oldStr.equals(""))
            oldPwd.setError(getResources().getString(R.string.pleaseenteroldPwd));
        else if (newStr.equals(""))
            newPwd.setError(getResources().getString(R.string.pleaseenternewPwd));
        else if (cmfStr.equals(""))
            cmfPwd.setError(getResources().getString(R.string.cmfPassword));
       else if (!cmfStr.equals(newStr))
            cmfPwd.setError(getString(R.string.confiemPasswordnotChange));
        else {
            AppComman.getInstance(this).setNonTouchableFlags(this);
            progressBar.setVisibility(View.VISIBLE);
            if (AppComman.getInstance(this).isConnectingToInternet(this)) {
                EducationService educationService = ServiceGenerator.createService(EducationService.class);
                call = educationService.resetPassword(new ResetPasswordEntity(AppComman.getInstance(this).getUserID(),oldStr,newStr,cmfStr));
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        progressBar.setVisibility(View.GONE);
                        AppComman.getInstance(ResetPasswordActivity.this).clearNonTouchableFlags(ResetPasswordActivity.this);
                        if (response != null) {
                            CommonResponse registrationResponse = (CommonResponse) response.body();
                            if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                                    AppComman.getInstance(ResetPasswordActivity.this).showDialogWithFinish(ResetPasswordActivity.this , getResources().getString(R.string.resetPasswordSuccessfully));
                            } else {
                                if (registrationResponse != null)
                                    AppComman.getInstance(ResetPasswordActivity.this).showDialog(ResetPasswordActivity.this, registrationResponse.getError());
                                else
                                    AppComman.getInstance(ResetPasswordActivity.this).showDialog(ResetPasswordActivity.this, getResources().getString(R.string.serverError));
                            }

                        } else
                            AppComman.getInstance(ResetPasswordActivity.this).showDialog(ResetPasswordActivity.this, getResources().getString(R.string.serverError));
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        AppComman.getInstance(ResetPasswordActivity.this).clearNonTouchableFlags(ResetPasswordActivity.this);
                        AppComman.getInstance(ResetPasswordActivity.this).showDialog(ResetPasswordActivity.this, getResources().getString(R.string.serverError));
                    }
                });
            } else {
                progressBar.setVisibility(View.GONE);
                AppComman.getInstance(this).clearNonTouchableFlags(this);
                AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
            }
        }
    
    }

}
