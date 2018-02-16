package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/16/2018.
 */

public class AllPaperViews_Activity extends Activity {
    @BindView(R.id.paperRecycleView)
    RecyclerView paperRecycleView;
    PaperImageAdapter paperImageAdapter;
    @BindView(R.id.toolbarText)
    TextView topBarText;
    @BindView(R.id.left)
    TextView backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_list);
        ButterKnife.bind(this);
        LinearLayoutManager llManager = new GridLayoutManager(this, 3);
        paperRecycleView.setLayoutManager(llManager);
        setAdapter();
        topBarText.setText("Paper View");
        backBtn.setVisibility(View.VISIBLE);
    }

    private void setAdapter() {
        paperImageAdapter = new PaperImageAdapter(this);
        paperRecycleView.setAdapter(paperImageAdapter);
    }

    public void openInFullView(int adapterPosition) {
        startActivity(new Intent(this , FullPaperImage.class));
    }
    @OnClick(R.id.left)
    void setBackBtn(){
        onBackPressed();
    }
}
