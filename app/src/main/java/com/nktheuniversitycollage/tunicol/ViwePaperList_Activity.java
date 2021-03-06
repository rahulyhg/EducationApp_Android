package com.nktheuniversitycollage.tunicol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.ExamListObj;
import APIObject.ExamSearchEntity;
import APIResponse.ExamResponse;
import Adapter.ViewPaperAdapter;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    String uniName = "" , examType = "";
    int coueseId = 0;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    @BindView(R.id.noData)
    TextView nodata;
    Call call;
    ArrayList<ExamListObj> examListObjsList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_list);
        ButterKnife.bind(this);
        if(getIntent()!= null){
           uniName = getIntent().getStringExtra("uniName");
           examType = getIntent().getStringExtra("examType");
           coueseId = getIntent().getIntExtra("courseId" , 0);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        paperRecycleView.setLayoutManager(layoutManager);
        callSearchApi();
        topBarText.setText("University Paper");
        backBtn.setVisibility(View.VISIBLE);
    }

    private void setAdapter() {
        viewPaperAdapter = new ViewPaperAdapter(this , examListObjsList);
        paperRecycleView.setAdapter(viewPaperAdapter);
    }

    public void clickOnRow(int adapterPosition) {
        String examObj = new Gson().toJson(examListObjsList.get(adapterPosition) , ExamListObj.class);
        Intent intent = new Intent(this , AllPaperViews_Activity.class);
        intent.putExtra("examObjstr" ,examObj );
        intent.putExtra("type",1);
        startActivity(intent);
    }
    @OnClick(R.id.left)
    void setBackBtn(){
        onBackPressed();
    }

    private void callSearchApi() {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            call = educationService.EXAM_RESPONSE_CALL(new ExamSearchEntity(uniName , coueseId , examType));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(ViwePaperList_Activity.this).clearNonTouchableFlags(ViwePaperList_Activity.this);
                    if (response != null) {
                        ExamResponse registrationResponse = (ExamResponse) response.body();
                        if (registrationResponse.getSuccess().equals("1")) {
                           // setAdapter();
                            nodata.setVisibility(View.GONE);
                            examListObjsList =  registrationResponse.getExamList();
                            setAdapter();
                        } else {
                            nodata.setVisibility(View.VISIBLE);
                            if (registrationResponse != null)
                                AppComman.getInstance(ViwePaperList_Activity.this).showDialog(ViwePaperList_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(ViwePaperList_Activity.this).showDialog(ViwePaperList_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(ViwePaperList_Activity.this).showDialog(ViwePaperList_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    nodata.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(ViwePaperList_Activity.this).clearNonTouchableFlags(ViwePaperList_Activity.this);
                    AppComman.getInstance(ViwePaperList_Activity.this).showDialog(ViwePaperList_Activity.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            nodata.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }
}
