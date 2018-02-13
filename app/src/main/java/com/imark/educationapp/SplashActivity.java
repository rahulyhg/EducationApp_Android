package com.imark.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



import Infrastructure.AppComman;

/**
 * Created by imark on 7/31/2017.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
       // Mint.initAndStartSession(this.getApplication(), "0516b4d1");
        Thread t = new Thread() {
            public void run() {
                try {

                    sleep(2000);
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                   if(AppComman.getInstance(SplashActivity.this).isUserLogIn()){

                            //startActivity(new Intent(SplashActivity.this,HomeActivity.class));

                           // startActivity(new Intent(SplashActivity.this,Navigation.class));

                    }else {
                        //startActivity(new Intent(SplashActivity.this, LoginActivity.class));
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
