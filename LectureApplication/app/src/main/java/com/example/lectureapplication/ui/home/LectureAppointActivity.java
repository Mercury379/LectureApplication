package com.example.lectureapplication.ui.home;

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
import com.example.lectureapplication.data.UserLectureLocalDataSource;
import com.example.lectureapplication.data.UserLocalDataSource;

public class LectureAppointActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_lecture_appoint);
        int lectureId = getIntent().getIntExtra("lectureId",0);
        lecture=LectureLocalDataSource.queryById(lectureId);
        title=(TextView)findViewById(R.id.textViewTitle);
        speaker=(TextView)findViewById(R.id.textViewSpeaker);
        type=(TextView)findViewById(R.id.textViewType);
        time=(TextView)findViewById(R.id.textViewTime);
        place=(TextView)findViewById(R.id.textViewPlace);
        peopleNum=(TextView)findViewById(R.id.textViewPeopleNum);
        introduce=(TextView)findViewById(R.id.textViewIntroduce);
        keywords=(TextView)findViewById(R.id.textViewKeywords);
        editTime=(TextView)findViewById(R.id.textViewEditTime);
        initText();
        buttonAppoint=(Button) findViewById(R.id.buttonAppoint);
        User user=UserLocalDataSource.queryByName(MyApplication.user.getName());
        buttonAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getLimits()==0){
//                    UserLectureLocalDataSource.insertAppoint(user,LectureLocalDataSource.queryById(lectureId));
//                    Toast.makeText(MyApplication.context, "您已被拉入讲座预约黑名单，请联系管理员操作！", Toast.LENGTH_LONG).show();
                    if(canAppoint(lectureId)){
                        MyApplication.dashboardViewModel.insertAppoints(user,lectureId);
                        Toast.makeText(MyApplication.context, "预约成功！", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MyApplication.context, "该讲座预约名额已满！", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MyApplication.context, "您已被拉入讲座预约黑名单，请联系管理员操作！", Toast.LENGTH_LONG).show();
                }
            }
        });
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
    public boolean canAppoint(int lId){
        Lecture lecture1=LectureLocalDataSource.queryById(lId);
        int count=UserLectureLocalDataSource.countLectuerAppoint(lId);
        if(lecture1.getPeopleNum()>count){
            return true;
        }else{
            return false;
        }
    }
}