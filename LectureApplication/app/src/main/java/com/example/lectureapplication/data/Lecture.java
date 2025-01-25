package com.example.lectureapplication.data;

public class Lecture {
    int id;
    String title;
    String speaker;
    String introduce;
    String keywords;
    String type;
    String place;
    String time;
    String editTime;
    int peopleNum;
    String status;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public String getEditTime() {
        return editTime;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Lecture(){}

    public Lecture(int id, String title, String speaker, String introduce, String keywords, String type, String place, String time, String editTime, String status,int peopleNum) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
        this.introduce = introduce;
        this.keywords = keywords;
        this.type = type;
        this.place = place;
        this.time = time;
        this.editTime = editTime;
        this.peopleNum = peopleNum;
        this.status = status;
    }
}
