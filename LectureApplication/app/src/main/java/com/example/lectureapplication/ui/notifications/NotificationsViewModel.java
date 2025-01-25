package com.example.lectureapplication.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.data.UserLocalDataSource;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> name;
    private final MutableLiveData<String> email;
    private final MutableLiveData<String> phone;
    private final MutableLiveData<Integer> lovetype;


    public NotificationsViewModel() {
        name=new MutableLiveData<>();
        email=new MutableLiveData<>();
        phone=new MutableLiveData<>();
        lovetype=new MutableLiveData<>();
    }

    public LiveData<String> getName() {
        return name;
    }
    public LiveData<String> getEmail() {
        return email;
    }
    public LiveData<String> getPhone() {
        return phone;
    }
    public LiveData<Integer> getType() {
        return lovetype;
    }
    public void setName(String data){
        name.setValue(data);
    }
    public void setEmail(String data){
        email.setValue(data);
    }
    public void setPhone(String data){
        phone.setValue(data);
    }
    public void setLovetype(int data){
        lovetype.setValue(data);
    }
}