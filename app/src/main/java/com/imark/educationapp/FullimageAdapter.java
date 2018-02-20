package com.imark.educationapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import APIObject.ExamImageObj;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2/16/2018.
 */

public class FullimageAdapter extends RecyclerView.Adapter<FullimageAdapter.FullImageHolder> {
    Activity activity;
    ArrayList<ExamImageObj> imageObjArrayList;
    public FullimageAdapter(Activity activity, ArrayList<ExamImageObj> imageObjArrayList) {
        this.activity = activity;
        this.imageObjArrayList = imageObjArrayList;
    }

    @Override
    public FullImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_image_row, parent, false);
        return new FullImageHolder(view);
    }

    @Override
    public void onBindViewHolder(FullImageHolder holder, int position) {
        ExamImageObj examImageObj = imageObjArrayList.get(position);
        holder.fullImage.setController(AppComman.getDraweeController(holder.fullImage , examImageObj.getExmimg() , 720));
    }

    @Override
    public int getItemCount() {
        return imageObjArrayList.size();
    }

    public class FullImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fullImage)
        SimpleDraweeView fullImage;

        public FullImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
