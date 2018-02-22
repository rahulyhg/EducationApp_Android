package com.nktheuniversitycollage.tunicol;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.google.gson.Gson;

import java.util.ArrayList;

import APIObject.ExamImageObj;
import APIObject.ExamListObj;
import APIObject.MyPaperListObj;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2/16/2018.
 */

public class FullPaperImage extends Activity {
    @BindView(R.id.fullImageView)
    RecyclerView fullImageView;
    FullimageAdapter fullimageAdapter;
    ArrayList<ExamImageObj> imageObjArrayList;
    int pos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_view);
        ButterKnife.bind(this);
        if(getIntent()!= null){
            pos = getIntent().getIntExtra("pos" , pos);
            if(getIntent().getIntExtra("type",0)==0) {
                ExamListObj examListObj = new Gson().fromJson(getIntent().getStringExtra("examObjstr"), ExamListObj.class);
                if(examListObj!= null)
                    imageObjArrayList = examListObj.getExamImageObjsList();
            }
            else {
                MyPaperListObj examListObj = new Gson().fromJson(getIntent().getStringExtra("examObjstr"), MyPaperListObj.class);
                if(examListObj!= null)
                    imageObjArrayList = examListObj.getExamImg();
            }


        }
        LinearLayoutManager myLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        fullImageView.setLayoutManager(myLayout);
        snapHelper.attachToRecyclerView(fullImageView);
        setAdapter();
    }

    private void setAdapter() {
    fullimageAdapter = new FullimageAdapter(this , imageObjArrayList);
        fullImageView.scrollToPosition(pos);
    fullImageView.setAdapter(fullimageAdapter);
    }
}
