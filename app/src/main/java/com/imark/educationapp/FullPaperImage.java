package com.imark.educationapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2/16/2018.
 */

public class FullPaperImage extends Activity {
    @BindView(R.id.fullImageView)
    RecyclerView fullImageView;
    FullimageAdapter fullimageAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_view);
        ButterKnife.bind(this);
        LinearLayoutManager myLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        fullImageView.setLayoutManager(myLayout);
        snapHelper.attachToRecyclerView(fullImageView);
        setAdapter();
    }

    private void setAdapter() {
    fullimageAdapter = new FullimageAdapter(this);
    fullImageView.setAdapter(fullimageAdapter);
    }
}
