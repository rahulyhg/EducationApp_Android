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

public class ResetPasswordActivity extends Activity {
    @BindView(R.id.toolbarText)
    TextView topbarText;
    @BindView(R.id.left)
    TextView left;

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
}
