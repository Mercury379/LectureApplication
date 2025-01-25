package com.example.lectureapplication.ui.home;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Lecture;

import java.util.ArrayList;
import java.util.List;

public class HomeUiAdapter extends RecyclerView.Adapter<HomeUiAdapter.ViewHolder> {

    private List<Lecture> lectureList;
    private Context context;

    public HomeUiAdapter(Context context) {
        lectureList = new ArrayList<>();
//        lectureList.add(new Lecture(0,"1","1","1","1","1","1","1","1","1",1));
//        lectureList.add(new Lecture(1,"2","2","2","2","2","2","2","2","2",2));
//        lectureList.add(new Lecture(0,"1","1","1","1","1","1","1","1","1",1));
//        lectureList.add(new Lecture(1,"2","2","2","2","2","2","2","2","2",2));
//        lectureList.add(new Lecture(0,"1","1","1","1","1","1","1","1","1",1));
//        lectureList.add(new Lecture(1,"2","2","2","2","2","2","2","2","2",2));
//        lectureList.add(new Lecture(0,"1","1","1","1","1","1","1","1","1",1));
//        lectureList.add(new Lecture(1,"2","2","2","2","2","2","2","2","2",2));
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lecture_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //处理最外层布局的点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Lecture lecture = lectureList.get(position);
                Intent intent = new Intent(parent.getContext(), LectureAppointActivity.class);
                // 向下一个 Activity 传递数据
                intent.putExtra("lectureId", lecture.getId());
                context.startActivity(intent);
//                ((WeatherActivity) context).showRealWeather(place.getId());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lecture lecture = lectureList.get(position);
//        String placeAddress = place.getCountry() + " " + place.getAdm1() + " " + place.getAdm2();
        String title="《"+lecture.getTitle()+"》";
        String speaker="主讲人："+lecture.getSpeaker();
        holder.textTitle.setText(title);
        holder.textSpeaker.setText(speaker);
        holder.textKeywords.setText(lecture.getKeywords());
        holder.textTime.setText(lecture.getEditTime());
    }

    @Override
    public int getItemCount() {
        return lectureList != null ? lectureList.size() : 0;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList = lectureList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTitle;
        private final TextView textSpeaker;
        private final TextView textKeywords;
        private final TextView textTime;

        public ViewHolder(View view) {
            super(view);
            textTitle = (TextView) view.findViewById(R.id.textTitle);
            textSpeaker=(TextView) view.findViewById(R.id.textSpeaker);
            textKeywords=(TextView) view.findViewById(R.id.textKeywords);
            textTime=(TextView) view.findViewById(R.id.textTime);
        }
    }

}
