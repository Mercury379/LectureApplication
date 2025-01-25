package com.example.lectureapplication.ui.lecture;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.data.UserLocalDataSource;

import java.util.List;

public class LectureViewModel extends ViewModel {

    private MutableLiveData<List<Lecture>> lectures;

    public LectureViewModel() {
        lectures = new MutableLiveData<>();
        loadUsers();
    }

    public LiveData<List<Lecture>> getLectures() {
        return lectures;
    }
    public void loadUsers(){
        lectures.setValue(LectureLocalDataSource.getLectures());
    }
    public void updateLecture(Lecture  lecture){LectureLocalDataSource.updateLecture(lecture);loadUsers();}
    public void insertLecture(Lecture lecture){
        LectureLocalDataSource.insertLecture(lecture);
        loadUsers();
    }

    // 根据关键字查询城市信息
//    public void fetchDataFromRepository(String keyWord) {
//        PlaceRepository repository = new PlaceRepository();
//        repository.fetchData(keyWord).observeForever(new Observer<List<Place>>() {
//            @Override
//            public void onChanged(List<Place> data) {
//                places.setValue(data);
//            }
//        });
//    }
}
