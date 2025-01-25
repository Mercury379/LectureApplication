package com.example.lectureapplication.ui.lecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertLectureActivity extends AppCompatActivity {
    private EditText title;
    private EditText speaker;
    private Spinner type;
    private EditText time;
    private EditText place;
    private EditText peopleNum;
    private EditText introduce;
    private EditText keywords;
    private TextView editTime;
    private Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_lecture);
        title=(EditText)findViewById(R.id.editTextTitle2);
        speaker=(EditText)findViewById(R.id.editTextSpeaker2);
        type=(Spinner) findViewById(R.id.spinnerLectureType2);
        time=(EditText)findViewById(R.id.editTextLectureTime2);
        place=(EditText)findViewById(R.id.editTextPlace2);
        peopleNum=(EditText)findViewById(R.id.editTextPeopleNum2);
        introduce=(EditText)findViewById(R.id.editTextIntroduce2);
        keywords=(EditText)findViewById(R.id.editTextKeyWords2);
        buttonSave=(Button)findViewById(R.id.buttonSave2);
        String[] type_list=new String[]{"学术讲座","文化讲座","专业讲座","论坛讲座","名人讲座"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type_list);
        type.setAdapter(adapter);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lecture lecture=new Lecture();
                long currentTimeMillis = System.currentTimeMillis();
                Date date = new Date(currentTimeMillis);
                // 创建一个 SimpleDateFormat 对象来定义日期时间的格式
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // 使用 SimpleDateFormat 的 format 方法来格式化日期时间
                String formattedDate = sdf.format(date);
                lecture.setEditTime(formattedDate);
                lecture.setStatus("已保存");
                lecture.setTitle(title.getText().toString());
                lecture.setSpeaker(speaker.getText().toString());
                lecture.setIntroduce(introduce.getText().toString());
                lecture.setKeywords(keywords.getText().toString());
                lecture.setTime(time.getText().toString());
                lecture.setPlace(place.getText().toString());
                lecture.setPeopleNum(Integer.valueOf(peopleNum.getText().toString()));
                lecture.setType(type.getSelectedItem().toString());
                MyApplication.lectureViewModel.insertLecture(lecture);
                Toast.makeText(InsertLectureActivity.this, "保存成功！", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}