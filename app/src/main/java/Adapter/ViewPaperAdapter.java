package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imark.educationapp.R;

/**
 * Created by User on 2/15/2018.
 */

public class ViewPaperAdapter  extends RecyclerView.Adapter<ViewPaperAdapter.ViewPaperHolder>{
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
        public ViewPaperHolder(View itemView) {
            super(itemView);
        }
    }
}
