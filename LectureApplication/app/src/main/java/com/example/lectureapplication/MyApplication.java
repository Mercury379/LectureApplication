package com.example.lectureapplication;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.example.lectureapplication.data.User;
import com.example.lectureapplication.ui.dashboard.DashboardViewModel;
import com.example.lectureapplication.ui.lecture.LectureViewModel;
import com.example.lectureapplication.ui.newhome.NewHomeViewModel;
import com.example.lectureapplication.ui.user.UserViewModel;


public class MyApplication extends Application {

    public static Context context;
    public static User user;
//    public static MemoViewModel memoViewModel;
    public static DashboardViewModel dashboardViewModel;
    public static UserViewModel userViewModel;
    public static LectureViewModel lectureViewModel;
    public static NewHomeViewModel newHomeViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        dashboardViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(DashboardViewModel.class);
        userViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(UserViewModel.class);
        lectureViewModel=new ViewModelProvider.AndroidViewModelFactory(this).create(LectureViewModel.class);
        newHomeViewModel=new ViewModelProvider.AndroidViewModelFactory(this).create(NewHomeViewModel.class);
//        memoViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(MemoViewModel.class);
    }
}
