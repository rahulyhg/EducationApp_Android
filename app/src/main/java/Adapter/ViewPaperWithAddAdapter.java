package Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.imark.educationapp.MyPaperList;
import com.imark.educationapp.R;
import com.imark.educationapp.ViwePaperList_Activity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/16/2018.
 */

public class ViewPaperWithAddAdapter extends RecyclerView.Adapter<ViewPaperWithAddAdapter.ViewPaperAddHolder> {
    Activity activity;
    ArrayList<String> imageArray;

    public ViewPaperWithAddAdapter(Activity activity, ArrayList<String> imageArray) {
        this.activity = activity;
        this.imageArray = imageArray;
    }

    @Override
    public ViewPaperAddHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_row_with_add, parent, false);
        return new ViewPaperAddHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewPaperAddHolder holder, int position) {

        if (position == 0) {
            holder.addIcon.setVisibility(View.VISIBLE);
            holder.deleteIcon.setVisibility(View.GONE);
        } else {
            holder.sdvImageview.setImageURI(imageArray.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return imageArray.size();
    }

    public void removeItem(int adapterPosition) {
        imageArray.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(adapterPosition, imageArray.size());
    }

    public class ViewPaperAddHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sdvImageview)
        SimpleDraweeView sdvImageview;
        @BindView(R.id.addIcon)
        ImageView addIcon;
        @BindView(R.id.paperRow)
        RelativeLayout paperRow;
        @BindView(R.id.deleteIcon)
        ImageView deleteIcon;


        public ViewPaperAddHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.paperRow)
        void setSdvImageview() {
            if (getAdapterPosition() == 0)
                ((MyPaperList) activity).addMoreImages();
        }
        @OnClick(R.id.paperRow)
        void setDeleteIcon() {
                ((MyPaperList) activity).deleteImage(getAdapterPosition());
        }
    }
}
