package com.example.lectureapplication.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Appoint;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;
import com.example.lectureapplication.ui.home.LectureAppointActivity;

import java.util.ArrayList;
import java.util.List;

public class DashBoardUiAdapter extends RecyclerView.Adapter<DashBoardUiAdapter.ViewHolder> {

    private List<Appoint> appointList;
    private Context context;

    public DashBoardUiAdapter(Context context) {
        appointList = new ArrayList<>();
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appoint_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //处理最外层布局的点击事件
        viewHolder.buttonCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Appoint appoint = appointList.get(position);
                appoint.setStatus("已取消");
                MyApplication.dashboardViewModel.updateAppoints(appoint);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appoint appoint = appointList.get(position);
//        String placeAddress = place.getCountry() + " " + place.getAdm1() + " " + place.getAdm2();
        Lecture lecture=LectureLocalDataSource.queryById(appoint.getLid());
        String title="《"+ lecture.getTitle() +"》";
        holder.textTitle.setText(title);
        holder.textTimePlace.setText("时间地点："+lecture.getTime()+" "+lecture.getPlace());
        holder.textStatus.setText(appoint.getStatus());
        holder.textTime.setText(appoint.getTime());
        if(appoint.getStatus().equals("已取消")){
            holder.buttonCanel.setEnabled(false);
            holder.cardView.setCardBackgroundColor(3);
        }
    }

    @Override
    public int getItemCount() {
        return appointList != null ? appointList.size() : 0;
    }

    public void setLectureList(List<Appoint> appointList) {
        this.appointList = appointList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textStatus;
        private final TextView textTitle;
        private final TextView textTimePlace;
        private final TextView textTime;
        private Button buttonCanel;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            textTitle = (TextView) view.findViewById(R.id.textAppointTitle);
            textStatus=(TextView) view.findViewById(R.id.textStatus);
            textTimePlace=(TextView) view.findViewById(R.id.textTimePlace);
            textTime=(TextView) view.findViewById(R.id.textAppointTime);
            buttonCanel=(Button) view.findViewById(R.id.buttonCancel);
            cardView=(CardView) view.findViewById(R.id.appointCard);
        }
    }

}
