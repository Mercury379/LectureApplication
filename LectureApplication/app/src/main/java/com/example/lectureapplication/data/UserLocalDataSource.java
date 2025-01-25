package com.example.lectureapplication.data;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lectureapplication.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class UserLocalDataSource {

    private static SQLiteDatabase database;

    static {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MyApplication.context,"Lecture.db", 1);
        database = dbHelper.getWritableDatabase();//打开数据库
    }

    public static void insertUser(User user) {
        // 新增用户
        ContentValues values = new ContentValues();
        // 组装用户
        values.put("name",user.getName() );
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        values.put("phone",user.getPhone());
        values.put("lovetype",user.getLovetype());
        values.put("limits",user.getLimits());
        values.put("isAdmin",user.isAdmin());
        database.insert("User", null, values);
    }

    public static void updateUser(User user) {
        // 修改用户
        ContentValues values = new ContentValues();
        // 组装数据
        values.put("id",user.getId() );
        values.put("name",user.getName() );
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        values.put("phone",user.getPhone());
        values.put("lovetype",user.getLovetype());
        values.put("limits",user.getLimits());
        values.put("isAdmin",user.isAdmin());
        database.update("User", values, "id = ? ", new String[]{String.valueOf(user.getId())});
    }

//    public static void deletUser(User user) {
//        database.delete("User", "id = ?", new String[]{String.valueOf(user.getId())});
//    }

    @SuppressLint("Range")
    public static User queryByName(String name) {
        User user = null;
        //id=？相当于where,?是占位符，后续new String[]给出,否则更新所有行
        Cursor cursor = database.query("User", null, "name=?", new String[]{String.valueOf(name)}, null, null, null);
        if (cursor.moveToFirst() == true) {
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String lovetype = cursor.getString(cursor.getColumnIndex("lovetype"));
            int limits = cursor.getInt(cursor.getColumnIndex("limits"));
            int isAdmin = cursor.getInt(cursor.getColumnIndex("isAdmin"));
            user = new User();
            if(isAdmin==0){
                user.setUser(id,name,password,phone,email,lovetype,limits);
            }else {
                user.setAdmin(id,name,password);
            }
        }
        cursor.close();
        return user;
    }
    @SuppressLint("Range")
    public static List<User> queryUsers(String name) {
        String selection;
        String[] selectionArgs = new String[]{"%"+name+"%"};
        selection="name LIKE ?";
        // 获取所有笔记信息
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query("User", null,
                selection, selectionArgs, null, null, "id");
        if (cursor.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String lovetype = cursor.getString(cursor.getColumnIndex("lovetype"));
                int limits = cursor.getInt(cursor.getColumnIndex("limits"));
                String name1 = cursor.getString(cursor.getColumnIndex("name"));
                User user=new User();
                user.setUser(id,name1,password,phone,email,lovetype,limits);
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    @SuppressLint("Range")//忽略与整数范围或类型有关的警告
    public static List<User> getAllUsers() {
        // 获取所有笔记信息
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query("User", null,
                null, null, null, null, "id");
        if (cursor.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String lovetype = cursor.getString(cursor.getColumnIndex("lovetype"));
                int limits = cursor.getInt(cursor.getColumnIndex("limits"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                User user=new User();
                user.setUser(id,name,password,phone,email,lovetype,limits);
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }
}
