package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class TermsConditionsActivity extends Activity {
    @BindView(R.id.toolbarText)
    TextView top_bar;
    @BindView(R.id.left)
    TextView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_and_condition);
        ButterKnife.bind(this);
        top_bar.setText(getResources().getString(R.string.termOfService));
    }

    @OnClick(R.id.left)
    void setBackBtn() {
        onBackPressed();
    }

}
