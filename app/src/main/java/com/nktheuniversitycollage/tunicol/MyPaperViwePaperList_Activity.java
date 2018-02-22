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
import APIObject.MyPaperListObj;
import APIObject.UserIdObj;
import APIResponse.MyExamresponse;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/21/2018.
 */

public class MyPaperViwePaperList_Activity extends Activity {
    @BindView(R.id.paperRecycleView)
    RecyclerView paperRecycleView;

    MyViewPaperAdapter myViewPaperAdapter;
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
    ArrayList<MyPaperListObj> examListObjsList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paper_list);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        paperRecycleView.setLayoutManager(layoutManager);
        callSearchApi();
        topBarText.setText(getResources().getString(R.string.myPaper));
        backBtn.setVisibility(View.VISIBLE);
    }

    private void setAdapter() {
        myViewPaperAdapter = new MyViewPaperAdapter(this , examListObjsList);
        paperRecycleView.setAdapter(myViewPaperAdapter);
    }

    public void clickOnRow(int adapterPosition) {
        String examObj = new Gson().toJson(examListObjsList.get(adapterPosition) );
        Intent intent = new Intent(this , AllPaperViews_Activity.class);
        intent.putExtra("examObjstr" ,examObj );
        intent.putExtra("type",0);
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
            call = educationService.mMyExamCall(new UserIdObj(AppComman.getInstance(this).getUserID()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(MyPaperViwePaperList_Activity.this).clearNonTouchableFlags(MyPaperViwePaperList_Activity.this);
                    if (response != null) {
                        MyExamresponse registrationResponse = (MyExamresponse) response.body();
                        if (registrationResponse.getSuccess()!= null && registrationResponse.getSuccess().equals("1")) {
                            // setAdapter();
                            nodata.setVisibility(View.GONE);
                            examListObjsList =  registrationResponse.getMyPaperList();
                            setAdapter();
                        } else {
                            nodata.setVisibility(View.VISIBLE);
                            if (registrationResponse != null)
                                AppComman.getInstance(MyPaperViwePaperList_Activity.this).showDialog(MyPaperViwePaperList_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(MyPaperViwePaperList_Activity.this).showDialog(MyPaperViwePaperList_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(MyPaperViwePaperList_Activity.this).showDialog(MyPaperViwePaperList_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    nodata.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(MyPaperViwePaperList_Activity.this).clearNonTouchableFlags(MyPaperViwePaperList_Activity.this);
                    AppComman.getInstance(MyPaperViwePaperList_Activity.this).showDialog(MyPaperViwePaperList_Activity.this, getResources().getString(R.string.serverError));
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
