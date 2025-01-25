package com.example.lectureapplication.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.data.UserLectureLocalDataSource;
import com.example.lectureapplication.data.UserLocalDataSource;

import java.util.List;

public class UserViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;

    public UserViewModel() {
        users = new MutableLiveData<>();
        loadUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
    public void loadUsers(){
        users.setValue(UserLocalDataSource.getAllUsers());
    }
    public void queryLectures(String name){users.setValue(UserLocalDataSource.queryUsers(name));}
    public void updateUsers(User user){
        UserLocalDataSource.updateUser(user);
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
