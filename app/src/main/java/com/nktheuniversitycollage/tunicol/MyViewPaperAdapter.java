package com.nktheuniversitycollage.tunicol;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import APIObject.MyPaperListObj;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/21/2018.
 */

public class MyViewPaperAdapter extends RecyclerView.Adapter<MyViewPaperAdapter.ViewPaperHolder>{
    Activity activity;
    ArrayList<MyPaperListObj> examListObjsList;


    public MyViewPaperAdapter(Activity activity, ArrayList<MyPaperListObj> examListObjsList) {
        this.activity = activity;
        this.examListObjsList = examListObjsList;
    }

    @Override
    public MyViewPaperAdapter.ViewPaperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_row, parent, false);
        return new MyViewPaperAdapter.ViewPaperHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewPaperAdapter.ViewPaperHolder holder, int position) {
        MyPaperListObj examListObj = examListObjsList.get(position);
        holder.courseName.setText(examListObj.getCourseName());
        holder.univercityName.setText(examListObj.getCollageName());
        holder.examType.setText(examListObj.getExamName());
        if(examListObj.getExamImg().size()!= 0)
            holder.paperImage.setController(AppComman.getDraweeController(holder.paperImage , examListObj.getExamImg().get(0).getExmimg() , 400));
        else {
            holder.paperImage.setImageURI("");

        }
        if(examListObj.getApproved()==1)
        {
            holder.status.setVisibility(View.VISIBLE);
            holder.status.setText(activity.getResources().getString(R.string.approved));
            holder.status.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.status.setVisibility(View.VISIBLE);
            holder.status.setText(activity.getResources().getString(R.string.pending));
            holder.status.setTextColor(activity.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return examListObjsList.size();
    }

    public class ViewPaperHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.paper_layout)
        RelativeLayout paper_layout;
        @BindView(R.id.paperImage)
        SimpleDraweeView paperImage;
        @BindView(R.id.univercityName)
        TextView univercityName;
        @BindView(R.id.courseName)
        TextView courseName;
        @BindView(R.id.examType)
        TextView examType;

        @BindView(R.id.status)
        TextView status;




        public ViewPaperHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @OnClick(R.id.paper_layout)
        void setPaper_layout(){
            ((MyPaperViwePaperList_Activity)activity).clickOnRow(getAdapterPosition());
        }
    }
}
