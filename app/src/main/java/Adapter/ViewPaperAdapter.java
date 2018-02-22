package Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nktheuniversitycollage.tunicol.R;
import com.nktheuniversitycollage.tunicol.ViwePaperList_Activity;

import java.util.ArrayList;

import APIObject.ExamListObj;
import Infrastructure.AppComman;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/15/2018.
 */

public class ViewPaperAdapter  extends RecyclerView.Adapter<ViewPaperAdapter.ViewPaperHolder>{
    Activity activity;
    ArrayList<ExamListObj> examListObjsList;
    public ViewPaperAdapter(Activity activity, ArrayList<ExamListObj> examListObjsList) {
        this.activity = activity;
        this.examListObjsList = examListObjsList;
    }

    @Override
    public ViewPaperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_row, parent, false);
        return new ViewPaperHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewPaperHolder holder, int position) {
       ExamListObj examListObj = examListObjsList.get(position);
        holder.courseName.setText(examListObj.getCourseName());
        holder.univercityName.setText(examListObj.getCollageName());
        holder.examType.setText(examListObj.getExamName());
        if(examListObj.getExamImageObjsList().size()!= 0)
            holder.paperImage.setController(AppComman.getDraweeController(holder.paperImage , examListObj.getExamImageObjsList().get(0).getExmimg() , 400));
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
