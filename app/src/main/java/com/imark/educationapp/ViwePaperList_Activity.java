package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import Adapter.ViewPaperAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class ViwePaperList_Activity extends Activity {
    @BindView(R.id.paperRecycleView)
    RecyclerView paperRecycleView;
    ViewPaperAdapter viewPaperAdapter;
    @BindView(R.id.toolbarText)
    TextView topBarText;
    @BindView(R.id.left)
    TextView backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_list);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        paperRecycleView.setLayoutManager(layoutManager);
        setAdapter();
        topBarText.setText("University Paper");
        backBtn.setVisibility(View.VISIBLE);
    }

    private void setAdapter() {
        viewPaperAdapter = new ViewPaperAdapter(this);
        paperRecycleView.setAdapter(viewPaperAdapter);
    }

    public void clickOnRow(int adapterPosition) {
        startActivity(new Intent(this , AllPaperViews_Activity.class));
    }
    @OnClick(R.id.left)
    void setBackBtn(){
        onBackPressed();
    }
}
