package com.imark.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Adapter.NavigationAdapter;
import Model.NavigationModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/14/2018.
 */

public class Home_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.left)
    TextView menuText;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.navigationRecyclerView)
    RecyclerView navigationRecyclerView;
    ArrayList<NavigationModel> navigationModelList = new ArrayList<>();
    NavigationAdapter navigationAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbarText.setText(getResources().getString(R.string.home));
        toolbarText.setTextColor(getResources().getColor(R.color.balck));
        menuText.setText(getResources().getString(R.string.menu));
        menuText.setTextSize((int) getResources().getDimension(R.dimen.menu_text));
        menuText.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        navigationRecyclerView.setLayoutManager(layoutManager);
        setUpNavigationdrawerManagement();
    }

    private void setUpNavigationdrawerManagement() {
        NavigationModel navigationModel;
        navigationModel = new NavigationModel(ContextCompat.getDrawable(this, R.drawable.home), getResources().getString(R.string.home));
        navigationModelList.add(navigationModel);

        navigationModel = new NavigationModel(ContextCompat.getDrawable(this, R.drawable.my_profile), getResources().getString(R.string.myProfile));
        navigationModelList.add(navigationModel);

        navigationModel = new NavigationModel(ContextCompat.getDrawable(this, R.drawable.terms), getResources().getString(R.string.termOfService));
        navigationModelList.add(navigationModel);

        navigationModel = new NavigationModel(ContextCompat.getDrawable(this, R.drawable.priva), getResources().getString(R.string.privacy_policy));
        navigationModelList.add(navigationModel);

        navigationModel = new NavigationModel(ContextCompat.getDrawable(this, R.drawable.key), getResources().getString(R.string.resetPassword));
        navigationModelList.add(navigationModel);
        navigationAdapter = new NavigationAdapter(navigationModelList, Home_Activity.this);
        navigationRecyclerView.setAdapter(navigationAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void setOnClickManagement(int position) {
        switch (position) {
            case 0:
                if (drawer.isDrawerOpen(Gravity.START)) {
                    drawer.closeDrawer(Gravity.START);
                }
                break;
            case 1:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case 2:
                Intent intent = new Intent(this, TermsConditionsActivity.class);
                startActivity(intent);
                break;
            case 3:
                startActivity(new Intent(this, PreivacyPolicyActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;


        }
    }

    @OnClick(R.id.uploadImage)
    void setuploadImage(){

    }
    @OnClick(R.id.viewPapers)
    void setViewPapers(){
        startActivity(new Intent(this , ViewPaperFilter.class));
    }
}
