package com.example.lectureapplication.data;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lectureapplication.MyApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserLectureLocalDataSource {

    private static SQLiteDatabase database;

    static {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MyApplication.context,"Lecture.db", 1);
        database = dbHelper.getWritableDatabase();//打开数据库
    }

    public static void insertAppoint(User user,Lecture lecture) {
        // 新增用户
        ContentValues values = new ContentValues();
        // 组装用户
        values.put("uId",user.getId());
        values.put("lId",lecture.getId());
        values.put("status", "已预约");
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        // 创建一个 SimpleDateFormat 对象来定义日期时间的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 使用 SimpleDateFormat 的 format 方法来格式化日期时间
        String formattedDate = sdf.format(date);
        values.put("time",formattedDate);
        database.insert("UserLecture", null, values);
    }

    public static void updateAppoint(Appoint appoint) {
        // 修改用户
        ContentValues values = new ContentValues();
        // 组装数据
        values.put("id",appoint.getId() );
        values.put("uId",appoint.getUid() );
        values.put("lId", appoint.getLid());
        values.put("status", appoint.getStatus());
        values.put("time",appoint.getTime());
        database.update("UserLecture", values, "id = ? ", new String[]{String.valueOf(appoint.getId())});
    }
    @SuppressLint("Range")//忽略与整数范围或类型有关的警告
    public static List<Appoint> getAllAppoints() {
        // 获取所有笔记信息
        List<Appoint> appoints = new ArrayList<>();
        Cursor cursor = database.query("UserLecture", null,
                null, null, null, null, "time DESC");
        if (cursor.moveToFirst() == true) {//确保Cursor至少有一个记录
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                int uid=cursor.getInt(cursor.getColumnIndex("uId"));
                int lid=cursor.getInt(cursor.getColumnIndex("lId"));
                String status=cursor.getString(cursor.getColumnIndex("status"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                appoints.add(new Appoint(id,uid,lid,status,time));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return appoints;
    }
    @SuppressLint("Range")//忽略与整数范围或类型有关的警告
    public static int countLectuerAppoint(int lId){
        int count=0;
        String[] columns = new String[]{"COUNT(*)"}; // 只需要计算记录条数，所以只选择COUNT(*)
        String whereClause = "lId = ? and status=?"; // WHERE子句，设置条件为lId等于某个值
        String[] whereArgs = new String[]{""+lId+"","已预约"}; // 替换?的值

        Cursor cursor = database.query("UserLecture", columns,
                whereClause, whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0); // 获取COUNT(*)的结果
            // 在这里使用count变量
        }

        cursor.close(); // 不要忘记关闭Cursor
        return count;
    }
}
