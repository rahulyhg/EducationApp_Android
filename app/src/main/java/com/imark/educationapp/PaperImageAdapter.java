package com.imark.educationapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import APIObject.ExamImageObj;
import Adapter.ViewPaperAdapter;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/16/2018.
 */

public class PaperImageAdapter extends RecyclerView.Adapter<PaperImageAdapter.PaperImageHolder> {
    Activity activity;
    ArrayList<ExamImageObj> imageObjArrayList;

    public PaperImageAdapter(Activity activity, ArrayList<ExamImageObj> imageObjArrayList) {
        this.activity = activity;
        this.imageObjArrayList = imageObjArrayList;
    }

    @Override
    public PaperImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_image_row, parent, false);
        return new PaperImageHolder(view);
    }

    @Override
    public void onBindViewHolder(PaperImageHolder holder, int position) {
        ExamImageObj examImageObj = imageObjArrayList.get(position);
        holder.sdvImageview.setController(AppComman.getDraweeController(holder.sdvImageview , examImageObj.getExmimg() , 500));
    }

    @Override
    public int getItemCount() {
        return imageObjArrayList.size();
    }

    public class PaperImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.paperRow)
        RelativeLayout paperRow;
        @BindView(R.id.sdvImageview)
        SimpleDraweeView sdvImageview;

        public PaperImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this ,itemView);
        }
        @OnClick(R.id.paperRow)
        void setPaperRow(){
            ((AllPaperViews_Activity)activity).openInFullView(getAdapterPosition());
        }
    }
}
