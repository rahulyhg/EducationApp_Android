package com.nktheuniversitycollage.tunicol;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.ImageListObj;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.System.out;

/**
 * Created by User on 2/16/2018.
 */

public class UploadOptionActivity extends Activity {
    List<String> imagesEncodedList;
    File file;
    String imageEncoded;
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    ArrayList<String> localArray = new ArrayList<>();
    Uri imageUri;
    Uri outPutfileUri;
    String uploadBy; //0 = camera , 1 = gallery
    private static int RESULT_LOAD_IMG = 1;
    private static final int RESULT_CAPTURE_IMG = 2;
    @BindView(R.id.toolbarText)
    TextView toolBarText;
    @BindView(R.id.left)
    TextView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_option_screen);
        ButterKnife.bind(this);
        toolBarText.setText(getResources().getString(R.string.upload));
        backBtn.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.takePicBtn)
    void addCameraImage(){
        requestCameraPermission();
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
    @OnClick(R.id.left)
    void setbackBtn()
    {
        onBackPressed();
    }

    private void startCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "attachment.jpg");
        outPutfileUri = FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID + ".provider",
                file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, RESULT_CAPTURE_IMG);
    }

    @OnClick(R.id.galleryBtn)
    void setGallary(){
        requestGalleryPermission();
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
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == RESULT_CAPTURE_IMG && resultCode == this.RESULT_OK) {
                String uri = outPutfileUri.toString();
                Log.e("uri-:", uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,60,out);
                    String url = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
                    outPutfileUri = Uri.parse(url);
                 localArray.add(0,String.valueOf(outPutfileUri));
                 uploadBy = "0";
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (requestCode == RESULT_LOAD_IMG && resultCode == this.RESULT_OK && null != data) {

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {
                    imageUri = data.getData();
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(imageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();
                    localArray.add(0,String.valueOf(imageUri));
                    uploadBy = "1";

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            localArray.add(0, String.valueOf(uri));
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();
                            uploadBy = "1";

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }if(localArray.size()!= 0){
                Intent i = new Intent(this , MyPaperList.class);
                ImageListObj imageListObj = new ImageListObj(localArray);
                i.putExtra("imageListObj",new Gson().toJson(imageListObj , ImageListObj.class));
                i.putExtra("uploadBy" , uploadBy);
                startActivity(i);
            }

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
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
}
