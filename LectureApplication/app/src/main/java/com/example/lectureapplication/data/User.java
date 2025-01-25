package com.example.lectureapplication.data;

public class User {
    private int id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String lovetype;
    private int limits;
    private int isAdmin;
    public void setAdmin(int id,String name,String password){
        this.id=id;
        this.name=name;
        this.password=password;
        this.isAdmin=1;
    }
    public void setUser(int id,String name,String password,String phone,String email,String lovetype,int limit){
        this.id=id;
        this.name=name;
        this.password=password;
        this.phone=phone;
        this.email=email;
        this.lovetype=lovetype;
        this.limits=limit;
        this.isAdmin=0;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getLimits() {
        return limits;
    }

    public String getLovetype() {
        return lovetype;
    }

    public void setLimits(int limit) {
        this.limits = limit;
    }

    public void setLovetype(String lovetype) {
        this.lovetype = lovetype;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int isAdmin() {
        return isAdmin;
    }
}
