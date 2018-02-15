package Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.imark.educationapp.Home_Activity;
import com.imark.educationapp.R;

import java.util.ArrayList;

import Model.NavigationModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 1/9/2018.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ListViewHolder> {
    private ArrayList<NavigationModel> navList;
    private Activity activity;

    public NavigationAdapter(ArrayList<NavigationModel> list, Activity activity) {
        this.navList = list;
        this.activity = activity;
    }


    @Override
    public NavigationAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_row, parent, false);
        return new NavigationAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NavigationAdapter.ListViewHolder holder, int position) {
        holder.navImage1.setImageDrawable(navList.get(position).getNavImage());
        holder.navTitle.setText(navList.get(position).getNavTitle());

    }

    @Override
    public int getItemCount() {
        return navList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        @BindView(R.id.navImage1)
        ImageView navImage1;

        @BindView(R.id.navTitle)
        TextView navTitle;



        public ListViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.navTitle)
        void setName() {
            int position = getAdapterPosition();
                ((Home_Activity)activity).setOnClickManagement(position);

        }
    }
}

