package com.imark.educationapp;

import android.app.Activity;
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
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.IOException;

import API.EducationService;
import API.ServiceGenerator;
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
 * Created by User on 2/14/2018.
 */

public class SignUp_Activity extends Activity {
    private int passwordNotVisible = 1;
    private int passwordNotVisible1 = 1;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.cmfPassword)
    EditText cmfPassword;
    @BindView(R.id.editTextEmailUserName)
    EditText editTextEmailUserName;
    @BindView(R.id.editTextEmailUniName)
    EditText editTextEmailUniName;
    @BindView(R.id.userimage)
    SimpleDraweeView userimage;
    Uri outPutfileUri = Uri.parse("");
    private int REQUEST_CAMERA = 0;
    private int SELECT_FILE = 1;
    String isAttachment = "0";
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    Call call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.SignIn)
    void setSignIn() {
        startActivity(new Intent(this, Login_Activity.class));
    }

    @OnClick(R.id.showIcon)
    void setShowPassword() {
        if (passwordNotVisible == 1) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordNotVisible = 0;
        } else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordNotVisible = 1;
        }
//ediText.setSelection(paswword.length());
    }

    @OnClick(R.id.showIcon2)
    void setShowcmfPassword() {
        if (passwordNotVisible1 == 1) {
            cmfPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordNotVisible1 = 0;
        } else {
            cmfPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordNotVisible1 = 1;
        }
//ediText.setSelection(paswword.length());
    }

    @OnClick(R.id.signUpBtn)
    void setSingUp() {
        String emailAddress = editTextEmailUserName.getText().toString().trim();
        String pw1 = password.getText().toString().trim();
        String pw2 = cmfPassword.getText().toString().trim();
        String uniName = editTextEmailUniName.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (emailAddress.equals("")) {
            editTextEmailUserName.setError(getString(R.string.plzEnterEmailId));
        } else if (!AppComman.getInstance(this).isEmailValid(emailAddress)) {
            editTextEmailUserName.setError(getString(R.string.emailValidation));
        } else if (pw1.equals("")) {
            password.setError(getString(R.string.passwordValidation));
        } else if (cmfPassword.equals("")) {
            cmfPassword.setError(getString(R.string.confirmPasswordValidation));
        } else if (!pw2.equals(pw1)) {
            cmfPassword.setError(getString(R.string.confiemPasswordnotChange));
        } else {
            callRegistration(emailAddress, pw1, pw2, uniName);
        }

    }

    private void callRegistration(String emailAddress, String pw1, String pw2, String uniName) {
        //RegistationEntity registrationEntity = new RegistationEntity(firstName, "", "", email, "", "", "", "", "android", facebookId, img);
        AppComman.getInstance(this).setNonTouchableFlags(this);
        progressBar.setVisibility(View.VISIBLE);
        if (AppComman.getInstance(this).isConnectingToInternet(this)) {
            EducationService educationService = ServiceGenerator.createService(EducationService.class);
            RequestBody firstName = RequestBody.create(okhttp3.MultipartBody.FORM, "");
            RequestBody email = RequestBody.create(okhttp3.MultipartBody.FORM, emailAddress);
            RequestBody passwordValue = RequestBody.create(okhttp3.MultipartBody.FORM, pw1);
            RequestBody cpassword = RequestBody.create(okhttp3.MultipartBody.FORM, pw2);
            RequestBody uni = RequestBody.create(okhttp3.MultipartBody.FORM, uniName);
            RequestBody deviceToken = RequestBody.create(okhttp3.MultipartBody.FORM, "");
            RequestBody deviceType = RequestBody.create(okhttp3.MultipartBody.FORM, "android");
            MultipartBody.Part imageUrl = null;
            if (isAttachment.equals("1")) {
                File file = FileUtils.getFile(this, outPutfileUri);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                imageUrl = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            }
            RequestBody fbImg = RequestBody.create(okhttp3.MultipartBody.FORM, "");

            call = educationService.REGISTRATION_RESPONSE_CALL(firstName, email, passwordValue, cpassword, deviceType, deviceToken, uni, imageUrl);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(SignUp_Activity.this).clearNonTouchableFlags(SignUp_Activity.this);
                    if (response != null) {
                        RegistrationResponse registrationResponse = (RegistrationResponse) response.body();
                        if (registrationResponse != null && registrationResponse.getSuccess().equals("1")) {
                            getData(registrationResponse.getResult());
                        } else {
                            if (registrationResponse != null)
                                AppComman.getInstance(SignUp_Activity.this).showDialog(SignUp_Activity.this, registrationResponse.getError());
                            else
                                AppComman.getInstance(SignUp_Activity.this).showDialog(SignUp_Activity.this, getResources().getString(R.string.serverError));
                        }

                    } else
                        AppComman.getInstance(SignUp_Activity.this).showDialog(SignUp_Activity.this, getResources().getString(R.string.serverError));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppComman.getInstance(SignUp_Activity.this).clearNonTouchableFlags(SignUp_Activity.this);
                    AppComman.getInstance(SignUp_Activity.this).showDialog(SignUp_Activity.this, getResources().getString(R.string.serverError));
                }
            });


        } else {
            progressBar.setVisibility(View.GONE);
            AppComman.getInstance(this).clearNonTouchableFlags(this);
            AppComman.getInstance(this).showDialog(this, getResources().getString(R.string.internetFail));
        }
    }

    private void getData(SignUpObject result) {
        AppComman.getInstance(this).setUserLogin(result.getId());
        startActivity(new Intent(this, Home_Activity.class));
        finishAffinity();
    }

    @OnClick({R.id.userimage, R.id.tapUpload})
    void attachmentLayout() {
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
                userimage.setImageURI(outPutfileUri);
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
                userimage.setImageURI(outPutfileUri);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null)
            call.cancel();
    }
}
