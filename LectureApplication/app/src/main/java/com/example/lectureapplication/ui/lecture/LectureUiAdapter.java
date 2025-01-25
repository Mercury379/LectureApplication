package com.example.lectureapplication.ui.lecture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.ui.home.LectureAppointActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 控制器类，用于将数据、ReclclerView和viewHolder进行绑定和响应动作
 * 可以将数据初始化的过程放在这个类里
 */
public class LectureUiAdapter extends RecyclerView.Adapter<LectureUiAdapter.ViewHolder> {
    private List<Lecture> lecturesList;
    private Context context;
    public LectureUiAdapter(Context context) {
        lecturesList = new ArrayList<>();
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView status;
        private TextView title;
        private TextView editTime;
        private Button buttonRelease;
        private Button buttonCancel;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            status=(TextView)view.findViewById(R.id.textLectureStatus);
            title=(TextView)view.findViewById(R.id.textLectureTitle);
            editTime=(TextView)view.findViewById(R.id.textEditTime);
            buttonCancel=(Button)view.findViewById(R.id.buttonCancelRelease);
            buttonRelease=(Button)view.findViewById(R.id.buttonRelease);
            cardView=(CardView) view.findViewById(R.id.lectureCard);
        }
    }

    @NonNull
    @Override
    /**
     * 把layout.xml变成一个view控件
     */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //这部分代码从parent获取上下文，并创建了LayoutInflater负责将XML布局文件转化为实际的视图对象
        //使用inflate方法将contacts_item布局文件转化为一个View对象，传入的三个参数分别是：要转化的布局资源ID、用于获取布局参数的父布局、一个布尔值指示是否应该立即将新创建的视图附加到父视图上。
        //在这个例子中，最后一个参数是false，意味着新创建的视图不会被立即附加到父视图上，而是稍后由RecyclerView自己管理
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lecture_record_rv, parent, false);
        //将转化后的视图对象引用赋值给view变量，这个view对象代表RecyclerView中的一个单独的数据项。
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.buttonRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTimeMillis = System.currentTimeMillis();
                Date date = new Date(currentTimeMillis);
                // 创建一个 SimpleDateFormat 对象来定义日期时间的格式
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // 使用 SimpleDateFormat 的 format 方法来格式化日期时间
                String formattedDate = sdf.format(date);
                int position = viewHolder.getAdapterPosition();
                Lecture lecture = lecturesList.get(position);
                lecture.setStatus("已发布");
                lecture.setEditTime(formattedDate);
                MyApplication.lectureViewModel.updateLecture(lecture);
                MyApplication.newHomeViewModel.loadLectures();
            }
        });
        viewHolder.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Lecture lecture= lecturesList.get(position);
                lecture.setStatus("已取消");
                MyApplication.lectureViewModel.updateLecture(lecture);
                MyApplication.newHomeViewModel.loadLectures();
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Lecture lecture= lecturesList.get(position);
                Intent intent = new Intent(parent.getContext(), EditLectureActivity.class);
                // 向下一个 Activity 传递数据
                intent.putExtra("lectureId", lecture.getId());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lecture lecture = lecturesList.get(position);
        holder.title.setText(lecture.getTitle());
        holder.status.setText(lecture.getStatus());
        holder.editTime.setText(lecture.getEditTime());
        if(lecture.getStatus().equals("已取消")){
            holder.cardView.setCardBackgroundColor(3);
            holder.buttonCancel.setEnabled(false);
            holder.buttonRelease.setEnabled(true);
        }else if(lecture.getStatus().equals("已保存")){
            holder.cardView.setCardBackgroundColor(Color.rgb(133,250,250));
            holder.buttonRelease.setEnabled(true);
            holder.buttonCancel.setEnabled(false);
        }else {
            holder.cardView.setCardBackgroundColor(Color.rgb(208,239,172));
            holder.buttonCancel.setEnabled(true);
            holder.buttonRelease.setEnabled(false);
        }
    }
    public void setLecturesList(List<Lecture> lecturesList) {
        this.lecturesList = lecturesList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return lecturesList.size();
    }
    // 为 Activity 添加菜单
}
