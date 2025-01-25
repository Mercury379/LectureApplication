package com.example.lectureapplication.data;

public class Appoint {
    private int id;
    private int uid;
    private int lid;
    private String status;
    private String time;
    public Appoint(){}

    public Appoint(int id, int uid, int lid, String status, String time) {
        this.id = id;
        this.uid = uid;
        this.lid = lid;
        this.status = status;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
