package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import CustomControl.TextViewIconStyle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class ViewPaperFilter extends Activity {
    @BindView(R.id.toolbarText)
    TextView topBarText;
    @BindView(R.id.left)
    TextViewIconStyle backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_paper_filter);
        ButterKnife.bind(this);
        topBarText.setText("The University Collage");
        backBtn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.searchBtn)
    void setSerarch() {
        startActivity(new Intent(this, ViwePaperList_Activity.class));
    }

    @OnClick(R.id.left)
    void setBackBtn() {
        onBackPressed();
    }
}
