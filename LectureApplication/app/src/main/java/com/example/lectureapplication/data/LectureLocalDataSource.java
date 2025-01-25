package com.example.lectureapplication.data;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.ui.lecture.LectureFragment;

import java.util.ArrayList;
import java.util.List;

public class LectureLocalDataSource {

    private static SQLiteDatabase database;

    static {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MyApplication.context,"Lecture.db", 1);
        database = dbHelper.getWritableDatabase();//打开数据库
    }

    public static void insertLecture(Lecture lecture) {
        // 新增用户
        ContentValues values = new ContentValues();
        // 组装用户
        values.put("title",lecture.getTitle());
        values.put("speaker",lecture.getSpeaker());
        values.put("introduce", lecture.getIntroduce());
        values.put("keywords",lecture.getKeywords());
        values.put("type",lecture.getType());
        values.put("place",lecture.getPlace());
        values.put("time", lecture.getTime());
        values.put("editTime",lecture.getEditTime());
        values.put("status",lecture.getStatus());
        values.put("peopleNum",lecture.getPeopleNum());
        database.insert("Lecture", null, values);
    }
//
    public static void updateLecture(Lecture lecture) {
        // 新增用户
        ContentValues values = new ContentValues();
        // 组装用户
        values.put("title",lecture.getTitle());
        values.put("speaker",lecture.getSpeaker());
        values.put("introduce", lecture.getIntroduce());
        values.put("keywords",lecture.getKeywords());
        values.put("type",lecture.getType());
        values.put("place",lecture.getPlace());
        values.put("time", lecture.getTime());
        values.put("editTime",lecture.getEditTime());
        values.put("status",lecture.getStatus());
        values.put("peopleNum",lecture.getPeopleNum());
        database.update("Lecture", values, "id = ? ", new String[]{String.valueOf(lecture.getId())});
    }

//    public static void deletUser(User user) {
//        database.delete("User", "id = ?", new String[]{String.valueOf(user.getId())});
//    }
//
    @SuppressLint("Range")
    public static Lecture queryById(int id) {
        Lecture lecture = null;
        //id=？相当于where,?是占位符，后续new String[]给出,否则更新所有行
        Cursor cursor = database.query("Lecture", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()){
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String speaker=cursor.getString(cursor.getColumnIndex("speaker"));
            String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
            String keywords=cursor.getString(cursor.getColumnIndex("keywords"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            String place=cursor.getString(cursor.getColumnIndex("place"));
            String time=cursor.getString(cursor.getColumnIndex("time"));
            String editTime=cursor.getString(cursor.getColumnIndex("editTime"));
            int peopleNum=cursor.getInt(cursor.getColumnIndex("peopleNum"));
            String status=cursor.getString(cursor.getColumnIndex("status"));
            lecture=new Lecture(id,title,speaker,introduce,keywords,type,place,time,editTime,status,peopleNum);
        }
        cursor.close();
        return lecture;
    }

    @SuppressLint("Range")//忽略与整数范围或类型有关的警告
    public static List<Lecture> getAllLectures() {
        // 获取所有笔记信息
        List<Lecture> lectures = new ArrayList<>();
        Cursor cursor = database.query("Lecture", null,
                null, null, null, null, "editTime DESC");
        if (cursor.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String speaker=cursor.getString(cursor.getColumnIndex("speaker"));
                String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
                String keywords=cursor.getString(cursor.getColumnIndex("keywords"));
                String type=cursor.getString(cursor.getColumnIndex("type"));
                String place=cursor.getString(cursor.getColumnIndex("place"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                String editTime=cursor.getString(cursor.getColumnIndex("editTime"));
                int peopleNum=cursor.getInt(cursor.getColumnIndex("peopleNum"));
                String status=cursor.getString(cursor.getColumnIndex("status"));
                if(status.equals("已发布")){
                    lectures.add(new Lecture(id,title,speaker,introduce,keywords,type,place,time,editTime,status,peopleNum));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lectures;
    }
    @SuppressLint("Range")//忽略与整数范围或类型有关的警告
    public static List<Lecture> getLectures() {
        // 获取所有笔记信息
        List<Lecture> lectures = new ArrayList<>();
        Cursor cursor = database.query("Lecture", null,
                null, null, null, null, "editTime");
        if (cursor.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String speaker=cursor.getString(cursor.getColumnIndex("speaker"));
                String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
                String keywords=cursor.getString(cursor.getColumnIndex("keywords"));
                String type=cursor.getString(cursor.getColumnIndex("type"));
                String place=cursor.getString(cursor.getColumnIndex("place"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                String editTime=cursor.getString(cursor.getColumnIndex("editTime"));
                int peopleNum=cursor.getInt(cursor.getColumnIndex("peopleNum"));
                String status=cursor.getString(cursor.getColumnIndex("status"));
                lectures.add(new Lecture(id,title,speaker,introduce,keywords,type,place,time,editTime,status,peopleNum));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lectures;
    }
    @SuppressLint("Range")//忽略与整数范围或类型有关的警告
    public static List<Lecture> getUserLectures() {
        // 获取所有笔记信息
        List<Lecture> lectures = new ArrayList<>();
        Cursor cursor = database.query("Lecture", null,
                null, null, null, null, "editTime DESC");
        if (cursor.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String speaker=cursor.getString(cursor.getColumnIndex("speaker"));
                String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
                String keywords=cursor.getString(cursor.getColumnIndex("keywords"));
                String type=cursor.getString(cursor.getColumnIndex("type"));
                String place=cursor.getString(cursor.getColumnIndex("place"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                String editTime=cursor.getString(cursor.getColumnIndex("editTime"));
                int peopleNum=cursor.getInt(cursor.getColumnIndex("peopleNum"));
                String status=cursor.getString(cursor.getColumnIndex("status"));
                if(UserLocalDataSource.queryByName(MyApplication.user.getName()).getLovetype().equals(type)&&status.equals("已发布")){
                    lectures.add(new Lecture(id,title,speaker,introduce,keywords,type,place,time,editTime,status,peopleNum));
                }
            } while (cursor.moveToNext());
        }
        Cursor cursor1 = database.query("Lecture", null,
                null, null, null, null, "editTime DESC");
        if (cursor1.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor1.getInt(cursor1.getColumnIndex("id"));
                String title=cursor1.getString(cursor1.getColumnIndex("title"));
                String speaker=cursor1.getString(cursor1.getColumnIndex("speaker"));
                String introduce=cursor1.getString(cursor1.getColumnIndex("introduce"));
                String keywords=cursor1.getString(cursor1.getColumnIndex("keywords"));
                String type=cursor1.getString(cursor1.getColumnIndex("type"));
                String place=cursor1.getString(cursor1.getColumnIndex("place"));
                String time=cursor1.getString(cursor1.getColumnIndex("time"));
                String editTime=cursor1.getString(cursor1.getColumnIndex("editTime"));
                int peopleNum=cursor1.getInt(cursor1.getColumnIndex("peopleNum"));
                String status=cursor1.getString(cursor1.getColumnIndex("status"));
                if(!UserLocalDataSource.queryByName(MyApplication.user.getName()).getLovetype().equals(type)&&status.equals("已发布")){
                    lectures.add(new Lecture(id,title,speaker,introduce,keywords,type,place,time,editTime,status,peopleNum));
                }
            } while (cursor1.moveToNext());
        }
        cursor.close();
        return lectures;
    }
    @SuppressLint("Range")//忽略与整数范围或类型有关的警告
    public static List<Lecture> queryLectures(int queryType,String queryWord) {
        String selection;
        String[] selectionArgs = new String[]{"%"+queryWord+"%"};
        switch (queryType){
            case 0:
                selection="title LIKE ?";
                break;
            case 1:
                selection="type LIKE ?";
                break;
            case 2:
                selection="keywords LIKE ?";
                break;
            case 3:
                selection="time LIKE ?";
                break;
            default:
                selection=null;

        }
        List<Lecture> lectures = new ArrayList<>();
        Cursor cursor = database.query("Lecture", null,
                selection,selectionArgs, null, null, "editTime DESC");
        if (cursor.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String speaker=cursor.getString(cursor.getColumnIndex("speaker"));
                String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
                String keywords=cursor.getString(cursor.getColumnIndex("keywords"));
                String type=cursor.getString(cursor.getColumnIndex("type"));
                String place=cursor.getString(cursor.getColumnIndex("place"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                String editTime=cursor.getString(cursor.getColumnIndex("editTime"));
                int peopleNum=cursor.getInt(cursor.getColumnIndex("peopleNum"));
                String status=cursor.getString(cursor.getColumnIndex("status"));
                if(status.equals("已发布")){
                    lectures.add(new Lecture(id,title,speaker,introduce,keywords,type,place,time,editTime,status,peopleNum));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lectures;
    }

}
