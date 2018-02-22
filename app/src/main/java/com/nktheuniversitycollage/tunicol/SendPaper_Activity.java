package com.nktheuniversitycollage.tunicol;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.util.ArrayList;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.CourseEntity;
import APIObject.CouserObject;
import APIObject.ImageArray;
import APIObject.UpdateExamEntity;
import APIResponse.CommonResponse;
import APIResponse.CoursesResponse;
import APIResponse.UpdateExamResponse;
import CustomControl.ProgressRequestBody;
import CustomControl.TextViewIconStyle;
import Infrastructure.AppComman;
import Infrastructure.FileUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2/16/2018.
 */

public class SendPaper_Activity extends Activity implements AdapterView.OnItemSelectedListener, ProgressRequestBody.UploadCallbacks {
    @BindView(R.id.toolbarText)
    TextView topBarText;
    @BindView(R.id.left)
    TextViewIconStyle backBtn;
    ImageArray imageArray;
    @BindView(R.id.courseType)
    Spinner courseType;
    @BindView(R.id.editTextExam)
    EditText editTextExam;
    @BindView(R.id.editTextshortDescription)
    EditText editTextshortDescription;
    @BindView(R.id.editTextuniName)
    EditText editTextuniName;

    Call call;
    Call callImage;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    ArrayList<CouserObject> couserObjectArrayList = new ArrayList<>();
    int courseIdPos = 0;
    @BindView(R.id.simpleProgressBar)
    ProgressBar uploadingProgressBar;
    RequestBody requestFile;
    int imageRequest, imageResponse = 0;
    @BindView(R.id.uploadinLayout)
    RelativeLayout uploadinLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_paper_activity);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            imageArray = new Gson().fromJson(getIntent().getStringExtra("imageStr"), ImageArray.class);

        }
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
            call = educationService.COURSES_RESPONSE_CALL(new CourseEntity(AppComman.getInstance(this).getUserID(), 0));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(SendPaper_Activity.this).clearNonTouchableFlags(SendPaper_Activity.this);
                    if (response != null) {
                        CoursesResponse registrationResponse = (CoursesResponse) response.body();
                        if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                            getData(registrationResponse.getResult());
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(SendPaper_Activity.this).showDialogWithFinish(SendPaper_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(SendPaper_Activity.this).showDialogWithFinish(SendPaper_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(SendPaper_Activity.this).showDialogWithFinish(SendPaper_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(SendPaper_Activity.this).clearNonTouchableFlags(SendPaper_Activity.this);
                    AppComman.getInstance(SendPaper_Activity.this).showDialogWithFinish(SendPaper_Activity.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialogWithFinish(this, getResources().getString(R.string.internetFail));
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

    @OnClick(R.id.saveBtn)
    void setSerarch() {
        String examStr = "";
        examStr = editTextExam.getText().toString().trim();
        String sortDiscption = "";
        sortDiscption = editTextshortDescription.getText().toString().trim();
        String uniName = "";
        uniName = editTextuniName.getText().toString().trim();
        int courseId = 0;
        if (couserObjectArrayList != null && couserObjectArrayList.size() != 0) {
            courseId = couserObjectArrayList.get(courseIdPos).getId();
        }
        if (examStr.equals(""))
            editTextExam.setError(getResources().getString(R.string.pleaseEnterExam));
        else if (uniName.equals(""))
            editTextuniName.setError(getResources().getString(R.string.enterUni));
        else
            uploadExamData(examStr, sortDiscption, uniName, AppComman.getInstance(this).getUserID(), courseId);
       /* startActivity(new Intent(this, Home_Activity.class));
        finish();*/
    }

    private void uploadExamData(String examStr, String sortDiscption, String uniName, String userID, int courseId) {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            call = educationService.mUpdateExamResponseCall(new UpdateExamEntity(courseId, examStr, sortDiscption, uniName, userID));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(SendPaper_Activity.this).clearNonTouchableFlags(SendPaper_Activity.this);
                    if (response != null) {
                        UpdateExamResponse registrationResponse = (UpdateExamResponse) response.body();
                        if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                            startUploadingImagesCall(registrationResponse.getExamIdObj().getExamId());
                            uploadinLayout.setVisibility(View.VISIBLE);
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(SendPaper_Activity.this).showDialog(SendPaper_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(SendPaper_Activity.this).showDialog(SendPaper_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(SendPaper_Activity.this).showDialog(SendPaper_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(SendPaper_Activity.this).clearNonTouchableFlags(SendPaper_Activity.this);
                    AppComman.getInstance(SendPaper_Activity.this).showDialog(SendPaper_Activity.this, getResources().getString(R.string.serverError));
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialogWithFinish(this, getResources().getString(R.string.internetFail));
        }
    }

    private void startUploadingImagesCall(String examId) {
        imageRequest = imageArray.getImagesPath().size() - 1;
        for (int i = 1; i < imageArray.getImagesPath().size(); i++) {
            startUploadingImages(examId, imageArray.getImagesPath().get(i));
        }
    }

    private void startUploadingImages(String id, String imagepath) {
        Uri imageUri = Uri.parse(imagepath);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            AppComman.getInstance(SendPaper_Activity.this).setNonTouchableFlags(SendPaper_Activity.this);
            //progressBar.setVisibility(View.VISIBLE);
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            RequestBody examIdTxt = RequestBody.create(okhttp3.MultipartBody.FORM, id);
            MultipartBody.Part imageUrl = null;
            File file = FileUtils.getFile(this, imageUri);
            //  RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

            uploadingProgressBar.setVisibility(View.VISIBLE);
            ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
            if (file != null) {
                requestFile =
                        RequestBody.create(
                                MediaType.parse(getContentResolver().getType(imageUri)),
                                file
                        );
            }
            imageUrl = MultipartBody.Part.createFormData("images", file.getName(), fileBody);

            callImage = educationService.addImage(examIdTxt, imageUrl);
            callImage.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    imageResponse++;
                    CommonResponse commonResponse = (CommonResponse) response.body();
                    AppComman.getInstance(SendPaper_Activity.this).clearNonTouchableFlags(SendPaper_Activity.this);
                    if (commonResponse.getResult() != null && commonResponse.getSuccess().equals("1")) {
                        if (imageResponse == imageRequest) {
                            uploadingProgressBar.setVisibility(View.GONE);
                            uploadinLayout.setVisibility(View.GONE);
                            Toast.makeText(SendPaper_Activity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SendPaper_Activity.this, Home_Activity.class));
                            finishAffinity();
                        }

                    } else {
                        uploadingProgressBar.setVisibility(View.GONE);
                        Toast.makeText(SendPaper_Activity.this, "somthing wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    imageResponse++;
                    AppComman.getInstance(SendPaper_Activity.this).clearNonTouchableFlags(SendPaper_Activity.this);
                    uploadingProgressBar.setVisibility(View.GONE);
                    Toast.makeText(SendPaper_Activity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            imageResponse++;
            AppComman.getInstance(SendPaper_Activity.this).clearNonTouchableFlags(SendPaper_Activity.this);
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).showDialog(this, this.getResources().getString(R.string.internetFail));
        }


    }

    @OnClick(R.id.left)
    void setBackBtn() {
        onBackPressed();
    }

    @Override
    public void onProgressUpdate(int percentage) {
        uploadingProgressBar.setProgress(percentage);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFinish() {
        uploadingProgressBar.setProgress(100);
        uploadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null)
            call.cancel();
        if(callImage!=null)
            callImage.cancel();
    }
}
