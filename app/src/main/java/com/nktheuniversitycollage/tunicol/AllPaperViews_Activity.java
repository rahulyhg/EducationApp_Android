package com.nktheuniversitycollage.tunicol;

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

import API.EducationService;
import API.ServiceGenerator;
import APIObject.ExamImageObj;
import APIObject.ExamListObj;
import APIObject.HistObj;
import APIObject.MyPaperListObj;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
Call call;
    ArrayList<ExamImageObj>imageObjArrayList = new ArrayList<>();
   MyPaperListObj examListObjsList ;
    ExamListObj examListObj ;
    int type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_list);
        ButterKnife.bind(this);
        if(getIntent()!= null){
            //0 myPaper and 1 all
            if(getIntent().getIntExtra("type" , 0) == 1) {
                examListObj = new Gson().fromJson(getIntent().getStringExtra("examObjstr"), ExamListObj.class);
                if (examListObj != null){
                    imageObjArrayList = examListObj.getExamImageObjsList();
                    callHits(examListObj.getId());
                    type = 0;
                }
            }else{
                examListObjsList = new Gson().fromJson(getIntent().getStringExtra("examObjstr"), MyPaperListObj.class);
                if (examListObjsList != null){
                    imageObjArrayList = examListObjsList.getExamImg();
                    type = 1;
                }

            }

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

    private void callHits(int id) {
        EducationService educationService = ServiceGenerator.createService(EducationService.class);
        call = educationService.mHits(new HistObj(id,AppComman.getInstance(this).getUserID()));
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void setAdapter() {
        paperImageAdapter = new PaperImageAdapter(this , imageObjArrayList);
        paperRecycleView.setAdapter(paperImageAdapter);
    }

    public void openInFullView(int adapterPosition) {
        Intent intent = new Intent(this ,FullPaperImage.class );
        String examObj;
        if(type == 0)
         examObj = new Gson().toJson(examListObj );
        else
            examObj = new Gson().toJson(examListObjsList );
        intent.putExtra("examObjstr" ,examObj );
        intent.putExtra("pos",adapterPosition);
        intent.putExtra("type",type);

        startActivity(intent);
    }
    @OnClick(R.id.left)
    void setBackBtn(){
        onBackPressed();
    }
}
