package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.widget.Spinner;


import Infrastructure.AppComman;

/**
 * Created by imark on 7/31/2017.
 */

public class SplashActivity extends Activity {

    Activity activity = SplashActivity.this;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        Window window = activity.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(activity,R.color.blue_color));
       // Mint.initAndStartSession(this.getApplication(), "0516b4d1");
        Thread t = new Thread() {
            public void run() {
                try {

                    sleep(2000);

                   if(AppComman.getInstance(SplashActivity.this).isUserLogIn()){
                       startActivity(new Intent(SplashActivity.this,Home_Activity.class));
                    }else {
                       startActivity(new Intent(SplashActivity.this,SelectionActivity.class));
                    }
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        t.start();
    }
}
