package com.imark.educationapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import Adapter.ViewPaperAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2/15/2018.
 */

public class ViwePaperList_Activity extends Activity {
    @BindView(R.id.paperRecycleView)
    RecyclerView paperRecycleView;
    ViewPaperAdapter viewPaperAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_list);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        paperRecycleView.setLayoutManager(layoutManager);
        setAdapter();
    }

    private void setAdapter() {
        viewPaperAdapter = new ViewPaperAdapter();
        paperRecycleView.setAdapter(viewPaperAdapter);
    }
}
