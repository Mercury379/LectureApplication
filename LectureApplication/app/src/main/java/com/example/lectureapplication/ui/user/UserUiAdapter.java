package com.example.lectureapplication.ui.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器类，用于将数据、ReclclerView和viewHolder进行绑定和响应动作
 * 可以将数据初始化的过程放在这个类里
 */
public class UserUiAdapter extends RecyclerView.Adapter<UserUiAdapter.ViewHolder> {
    private List<User> usersList;
    private Context context;
    public UserUiAdapter(Context context) {
        usersList = new ArrayList<>();
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private Button buttonReset;
        private Button buttonBan;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.userName);
            buttonReset=(Button) view.findViewById(R.id.buttonResetPassword);
            buttonBan=(Button) view.findViewById(R.id.buttonBan);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_rv, parent, false);
        //将转化后的视图对象引用赋值给view变量，这个view对象代表RecyclerView中的一个单独的数据项。
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                User user = usersList.get(position);
                user.setPassword("123456");
                MyApplication.userViewModel.updateUsers(user);
            }
        });
        viewHolder.buttonBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                User user = usersList.get(position);
                user.setLimits(1);
                MyApplication.userViewModel.updateUsers(user);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.name.setText(user.getName());
    }
    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return usersList.size();
    }
    // 为 Activity 添加菜单
}
