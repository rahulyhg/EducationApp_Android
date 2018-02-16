package com.imark.educationapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import Adapter.ViewPaperAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/16/2018.
 */

public class PaperImageAdapter extends RecyclerView.Adapter<PaperImageAdapter.PaperImageHolder> {
    Activity activity;

    public PaperImageAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public PaperImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_image_row, parent, false);
        return new PaperImageHolder(view);
    }

    @Override
    public void onBindViewHolder(PaperImageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PaperImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.paperRow)
        RelativeLayout paperRow;
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
