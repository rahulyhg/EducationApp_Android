package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class ViewPaperFilter extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_paper_filter);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.searchBtn)
    void  setSerarch(){
        startActivity(new Intent(this , ViwePaperList_Activity.class));
    }
}
