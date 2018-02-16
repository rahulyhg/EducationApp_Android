package com.imark.educationapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 2/16/2018.
 */

public class FullimageAdapter extends RecyclerView.Adapter<FullimageAdapter.FullImageHolder> {
    Activity activity;
    public FullimageAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public FullImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_image_row, parent, false);
        return new FullImageHolder(view);
    }

    @Override
    public void onBindViewHolder(FullImageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class FullImageHolder extends RecyclerView.ViewHolder {
        public FullImageHolder(View itemView) {
            super(itemView);
        }
    }
}
