package com.example.lectureapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Lecture>> lectures;

    public HomeViewModel() {
        lectures = new MutableLiveData<>();
        loadLectures();
    }

    public LiveData<List<Lecture>> getLectures() {
        return lectures;
    }
    public void loadLectures(){
        lectures.setValue(LectureLocalDataSource.getUserLectures());
    }
    public void queryLectures(int type,String word){lectures.setValue(LectureLocalDataSource.queryLectures(type,word));}

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
