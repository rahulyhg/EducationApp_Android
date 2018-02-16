package Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.imark.educationapp.R;
import com.imark.educationapp.ViwePaperList_Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class ViewPaperAdapter  extends RecyclerView.Adapter<ViewPaperAdapter.ViewPaperHolder>{
    Activity activity;
    public ViewPaperAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ViewPaperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_row, parent, false);
        return new ViewPaperHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewPaperHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewPaperHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.paper_layout)
        RelativeLayout paper_layout;
        public ViewPaperHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @OnClick(R.id.paper_layout)
        void setPaper_layout(){
            ((ViwePaperList_Activity)activity).clickOnRow(getAdapterPosition());
        }
    }
}
