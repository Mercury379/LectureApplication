package com.example.lectureapplication.ui.newhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.data.UserLocalDataSource;

public class NewLectureAppointActivity extends AppCompatActivity {
    private Lecture lecture;
    private TextView title;
    private TextView speaker;
    private TextView type;
    private TextView time;
    private TextView place;
    private TextView peopleNum;
    private TextView introduce;
    private TextView keywords;
    private TextView editTime;
    private Button buttonAppoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lecture_appoint);
        int lectureId = getIntent().getIntExtra("lectureId",0);
        lecture= LectureLocalDataSource.queryById(lectureId);
        title=(TextView)findViewById(R.id.textViewTitle2);
        speaker=(TextView)findViewById(R.id.textViewSpeaker2);
        type=(TextView)findViewById(R.id.textViewType2);
        time=(TextView)findViewById(R.id.textViewTime2);
        place=(TextView)findViewById(R.id.textViewPlace2);
        peopleNum=(TextView)findViewById(R.id.textViewPeopleNum2);
        introduce=(TextView)findViewById(R.id.textViewIntroduce2);
        keywords=(TextView)findViewById(R.id.textViewKeywords2);
        editTime=(TextView)findViewById(R.id.textViewEditTime2);
        initText();
    }
    public void initText(){
        title.setText("《"+lecture.getTitle()+"》");
        speaker.setText("主讲人："+lecture.getSpeaker());
        type.setText("讲座类型："+lecture.getType());
        time.setText("时间："+lecture.getTime());
        place.setText("地点："+lecture.getPlace());
        peopleNum.setText("讲座名额："+lecture.getPeopleNum()+"人");
        introduce.setText("简介："+lecture.getIntroduce());
        keywords.setText("关键词："+lecture.getKeywords());
        editTime.setText(lecture.getEditTime());
    }
}