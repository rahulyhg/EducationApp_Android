package com.nktheuniversitycollage.tunicol;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/13/2018.
 */

public class SelectionActivity extends Activity{
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.alredybtn)
    Button alredybtn;

    Activity activity = this;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);
        ButterKnife.bind(this);
        Window window = activity.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(activity,R.color.blue_color));
      //  setStartBtn();
    }
    @OnClick(R.id.startBtn)
    void setStartBtn(){
        setButtonState(startBtn , alredybtn);
        startActivity(new Intent(this , SignUp_Activity.class));
    }
 @OnClick(R.id.alredybtn)
    void setAlredyBtn(){

     setButtonState(alredybtn , startBtn);
     startActivity(new Intent(this , Login_Activity.class));
    }

    public void setButtonState(Button textView1, Button textView2) {
        textView1.setSelected(true);
        textView2.setSelected(false);
        textView1.setTextColor(getResources().getColor(R.color.white));
        textView2.setTextColor(getResources().getColor(R.color.blue_color));
    }
}
