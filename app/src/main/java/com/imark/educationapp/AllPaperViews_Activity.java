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

import com.google.gson.Gson;

import java.util.ArrayList;

import APIObject.ExamImageObj;
import APIObject.ExamListObj;
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
    @BindView(R.id.noData)
    TextView noData;

    ArrayList<ExamImageObj>imageObjArrayList = new ArrayList<>();
    ExamListObj examListObj ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_list);
        ButterKnife.bind(this);
        if(getIntent()!= null){
            examListObj = new Gson().fromJson(getIntent().getStringExtra("examObjstr") , ExamListObj.class);
            if(examListObj!= null)
                imageObjArrayList = examListObj.getExamImageObjsList();
        }
        LinearLayoutManager llManager = new GridLayoutManager(this, 3);
        paperRecycleView.setLayoutManager(llManager);
        if(imageObjArrayList.size() == 0)
            noData.setVisibility(View.VISIBLE);
        else
            noData.setVisibility(View.GONE);
       setAdapter();
        topBarText.setText("Paper View");
        backBtn.setVisibility(View.VISIBLE);
    }

    private void setAdapter() {
        paperImageAdapter = new PaperImageAdapter(this , imageObjArrayList);
        paperRecycleView.setAdapter(paperImageAdapter);
    }

    public void openInFullView(int adapterPosition) {
        Intent intent = new Intent(this ,FullPaperImage.class );
        String examObj = new Gson().toJson(examListObj , ExamListObj.class);
        intent.putExtra("examObjstr" ,examObj );
        intent.putExtra("pos",adapterPosition);
        startActivity(intent);
    }
    @OnClick(R.id.left)
    void setBackBtn(){
        onBackPressed();
    }
}
