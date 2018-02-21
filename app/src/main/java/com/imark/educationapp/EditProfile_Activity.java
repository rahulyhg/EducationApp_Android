package com.imark.educationapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import API.EducationService;
import API.ServiceGenerator;
import APIObject.EditProfileEntity;
import APIObject.LoginEntity;
import APIObject.SignUpObject;
import APIResponse.RegistrationResponse;
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
 * Created by User on 2/15/2018.
 */

public class EditProfile_Activity extends Activity {
    @BindView(R.id.toolbarText)
    TextView topBarText;
    @BindView(R.id.left)
    TextView backBtn;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.dobEditText)
    EditText dobEditText;
    @BindView(R.id.editTextUnivercity)
    EditText editTextUnivercity;
    @BindView(R.id.userimage)
    SimpleDraweeView userImage;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;

    Call call;
    Uri outPutfileUri = Uri.parse("");
    private int REQUEST_CAMERA = 0;
    private int SELECT_FILE = 1;
    String isAttachment = "0";
    SignUpObject signUpObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        ButterKnife.bind(this);
        topBarText.setText(getResources().getString(R.string.editProfile));
        backBtn.setVisibility(View.VISIBLE);
        signUpObject = new Gson().fromJson(AppComman.getInstance(this).getUserObject(), SignUpObject.class);
        if (signUpObject != null) {
            if (!signUpObject.getName().equals(""))
                userName.setText(signUpObject.getName());
            editTextUnivercity.setText(signUpObject.getUniversity());
            dobEditText.setText(signUpObject.getDateOfBirth());
            userImage.setController(AppComman.getDraweeController(userImage, signUpObject.getProfilePic(), 500));
        }
    }

    @OnClick(R.id.left)
    void setBackbtn() {
        onBackPressed();
    }


    @OnClick(R.id.userimage)
    void setImage() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.choose_option_dialog);
        dialog.setTitle(getResources().getString(R.string.app_name));
        TextView camera = (TextView) dialog.findViewById(R.id.camera);
        TextView gallery = (TextView) dialog.findViewById(R.id.gallery);
        TextView textViewCancel = (TextView) dialog.findViewById(R.id.cancel);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCameraPermission();
                dialog.dismiss();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestGalleryPermission();
                dialog.dismiss();
            }
        });
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void requestGalleryPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    201);
        } else {
            startGalleryIntent();
        }
    }

    private void startGalleryIntent() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
//        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), SELECT_FILE);
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA},
                    200);
        } else {
            startCameraIntent();
        }
    }

    private void startCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "attachment.jpg");
        outPutfileUri = FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID + ".provider",
                file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    startCameraIntent();
                }
                break;
            case 201:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startGalleryIntent();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                outPutfileUri = data.getData();
                isAttachment = "1";
                userImage.setImageURI(outPutfileUri);
            } else if (requestCode == REQUEST_CAMERA) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri);
                    String url = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "attachment", null);
                    outPutfileUri = Uri.parse(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isAttachment = "1";
                userImage.setImageURI(outPutfileUri);

            }
        }
    }

    @OnClick(R.id.update)
    void update() {
        String dobString = dobEditText.getText().toString().trim();
        String userNameTxt = userName.getText().toString().trim();
        String uniName = editTextUnivercity.getText().toString().trim();
        if (userNameTxt.equals(""))
            userName.setError(getResources().getString(R.string.pleseName));
        else if (dobString.equals(""))
            dobEditText.setError(getResources().getString(R.string.pleaseDate));
        else if (uniName.equals(""))
            editTextUnivercity.setError(getResources().getString(R.string.enterUni));
        else
            callUpdateProfile(dobString, userNameTxt, uniName);

    }

    private void callUpdateProfile(String dobString, String userName, String uniName) {
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            RequestBody firstName = RequestBody.create(okhttp3.MultipartBody.FORM, userName);
            RequestBody uniNameText = RequestBody.create(okhttp3.MultipartBody.FORM, uniName);
            RequestBody dob = RequestBody.create(okhttp3.MultipartBody.FORM, dobString);
            RequestBody userId = RequestBody.create(okhttp3.MultipartBody.FORM, AppComman.getInstance(this).getUserID());
            MultipartBody.Part imageUrl = null;
            if (isAttachment.equals("1")) {
                File file = FileUtils.getFile(this, outPutfileUri);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                imageUrl = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            }
            RequestBody fbImg = RequestBody.create(okhttp3.MultipartBody.FORM, "");

            call = educationService.editProfilCall(firstName,dob,uniNameText,userId,imageUrl);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(EditProfile_Activity.this).clearNonTouchableFlags(EditProfile_Activity.this);
                    if (response != null) {
                        RegistrationResponse registrationResponse = (RegistrationResponse) response.body();
                        if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                            getData(registrationResponse.getResult());
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(EditProfile_Activity.this).showDialog(EditProfile_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(EditProfile_Activity.this).showDialog(EditProfile_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(EditProfile_Activity.this).showDialog(EditProfile_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(EditProfile_Activity.this).clearNonTouchableFlags(EditProfile_Activity.this);
                    AppComman.getInstance(EditProfile_Activity.this).showDialog(EditProfile_Activity.this, getResources().getString(R.string.serverError));
                }
            });


        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }

    private void getData(SignUpObject result) {
        AppComman.getInstance(this).setUserObject(new Gson().toJson(result));
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.dobEditText)
    void setDate2() {
        onHideKeyboard(this);
        showTruitonDatePickerDialog(dobEditText);
    }

    private void showTruitonDatePickerDialog(final EditText dobEditText) {
        final Calendar c;
        c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                   /*     dobTextView.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year); */
                        dobEditText.setText(new SimpleDateFormat("dd-MMM-yyyy").format(calendar.getTime()));
                    }
                }, mYear, mMonth, mDay);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    private void onHideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null)
            call.cancel();
    }
}
