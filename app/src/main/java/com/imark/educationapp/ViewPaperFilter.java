package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.CourseEntity;
import APIObject.CouserObject;
import APIObject.LoginEntity;
import APIObject.SignUpObject;
import APIResponse.CoursesResponse;
import APIResponse.RegistrationResponse;
import CustomControl.TextViewIconStyle;
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

public class ViewPaperFilter extends Activity {
    @BindView(R.id.toolbarText)
    TextView topBarText;
    @BindView(R.id.left)
    TextViewIconStyle backBtn;
    @BindView(R.id.courseType)
    Spinner courseType;
    @BindView(R.id.editTextUni)
    EditText editTextUni;
    @BindView(R.id.editTextExamText)
    EditText editTextExamText;
    Call call;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    ArrayList<CouserObject> couserObjectArrayList = new ArrayList<>();
    int courseIdPos = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_paper_filter);
        ButterKnife.bind(this);
        topBarText.setText(getResources().getString(R.string.app_name));
        backBtn.setVisibility(View.VISIBLE);
        getCourseList();
        courseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                courseIdPos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getCourseList() {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            call = educationService.COURSES_RESPONSE_CALL(new CourseEntity(AppComman.getInstance(this).getUserID() , 1));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(ViewPaperFilter.this).clearNonTouchableFlags(ViewPaperFilter.this);
                    if (response != null) {
                        CoursesResponse registrationResponse = (CoursesResponse) response.body();
                        if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                            getData(registrationResponse.getResult());
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(ViewPaperFilter.this).showDialog(ViewPaperFilter.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(ViewPaperFilter.this).showDialog(ViewPaperFilter.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(ViewPaperFilter.this).showDialog(ViewPaperFilter.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(ViewPaperFilter.this).clearNonTouchableFlags(ViewPaperFilter.this);
                    AppComman.getInstance(ViewPaperFilter.this).showDialog(ViewPaperFilter.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }

    private void getData(ArrayList<CouserObject> result) {
        if (result != null && result.size() != 0) {
            couserObjectArrayList = result;
            String[] courseName = new String[couserObjectArrayList.size()];
            for (int i = 0; i < couserObjectArrayList.size(); i++) {
                courseName[i] = couserObjectArrayList.get(i).getName();
            }
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courseName);
            courseType.setAdapter(myAdapter);
        }
    }

    @OnClick(R.id.searchBtn)
    void setSerarch() {
        String uniName = editTextUni.getText().toString().trim();
        String examType = editTextExamText.getText().toString().trim();
        int courseId = 0;
        if(couserObjectArrayList!=null && couserObjectArrayList.size()!= 0){
            courseId = couserObjectArrayList.get(courseIdPos).getId();
        }
       // callSearchApi(uniName , examType , courseId);
        Intent i = new Intent(this , ViwePaperList_Activity.class);
        i.putExtra("uniName",uniName);
        i.putExtra("examType",examType);
        i.putExtra("courseId",courseId);
        startActivity(i);
    }



    @OnClick(R.id.left)
    void setBackBtn() {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null)
            call.cancel();
    }
}
