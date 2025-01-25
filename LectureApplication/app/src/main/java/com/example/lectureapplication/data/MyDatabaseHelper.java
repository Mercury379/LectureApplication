package com.example.lectureapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private String createUser = "create table User (" +
            "id integer primary key autoincrement," +
            "name text," +
            "password text," +
            "phone text," +
            "email text," +
            "lovetype text," +
            "limits integer," +
            "isAdmin integer)";
    private String createLecture = "create table Lecture (" +
            "id integer primary key autoincrement," +
            "title text," +
            "speaker text," +
            "introduce text," +
            "keywords text," +
            "type text," +
            "place text," +
            "time text," +
            "editTime text," +
            "status text," +
            "peopleNum integer)";
    private String createUserLecture = "create table UserLecture (" +
            "id integer primary key autoincrement," +
            "uId integer," +
            "lId integer," +
            "status text," +
            "time text)";
    //autoincrement自增长编号
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUser);
        db.execSQL(createLecture);
        db.execSQL(createUserLecture);
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
