package com.example.lectureapplication.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lectureapplication.data.Appoint;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.data.UserLectureLocalDataSource;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<List<Appoint>> appoints;

    public DashboardViewModel() {
        appoints = new MutableLiveData<>();
        loadAppoints();
    }

    public LiveData<List<Appoint>> getAppoints() {
        return appoints;
    }
    public void loadAppoints(){
        appoints.setValue(UserLectureLocalDataSource.getAllAppoints());
    }
    public void updateAppoints(Appoint appoint){
        UserLectureLocalDataSource.updateAppoint(appoint);
        loadAppoints();
    }
    public void insertAppoints(User user, int lectureId){
        UserLectureLocalDataSource.insertAppoint(user,LectureLocalDataSource.queryById(lectureId));
        loadAppoints();
    }
//    public void queryLectures(int type,String word){appoints.setValue(LectureLocalDataSource.queryLectures(type,word));}
}