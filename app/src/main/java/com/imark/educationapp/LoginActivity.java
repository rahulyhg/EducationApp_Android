package com.imark.educationapp;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.Window;

/**
 * Created by User on 2/13/2018.
 */

public class LoginActivity extends Activity{
    Activity activity = this;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Window window = activity.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(activity,R.color.blue_color));
    }
}
