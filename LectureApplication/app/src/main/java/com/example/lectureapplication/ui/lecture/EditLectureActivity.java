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

public class EditLectureActivity extends AppCompatActivity {
    private Lecture lecture;
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
        setContentView(R.layout.activity_edit_lecture);
        int lectureId = getIntent().getIntExtra("lectureId",0);
        lecture= LectureLocalDataSource.queryById(lectureId);
        title=(EditText)findViewById(R.id.editTextTitle);
        speaker=(EditText)findViewById(R.id.editTextSpeaker);
        type=(Spinner) findViewById(R.id.spinnerLectureType);
        time=(EditText)findViewById(R.id.editTextLectureTime);
        place=(EditText)findViewById(R.id.editTextPlace);
        peopleNum=(EditText)findViewById(R.id.editTextPeopleNum);
        introduce=(EditText)findViewById(R.id.editTextIntroduce);
        keywords=(EditText)findViewById(R.id.editTextKeyWords);
        editTime=(TextView) findViewById(R.id.textViewEditTime2);
        buttonSave=(Button)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                MyApplication.lectureViewModel.updateLecture(lecture);
                Toast.makeText(EditLectureActivity.this, "保存成功", Toast.LENGTH_LONG).show();
            }
        });
        initText();
    }
    public void initText(){
        String[] type_list=new String[]{"学术讲座","文化讲座","专业讲座","论坛讲座","名人讲座"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type_list);
        type.setAdapter(adapter);
        title.setText(lecture.getTitle());
        speaker.setText(lecture.getSpeaker());
        for(int i=0;i<5;i++){
            if(type_list[i].equals(lecture.getType())){
                type.setSelection(i);
            }
        }
        time.setText(lecture.getTime());
        place.setText(lecture.getPlace());
        peopleNum.setText(String.valueOf(lecture.getPeopleNum()));
        introduce.setText(lecture.getIntroduce());
        keywords.setText(lecture.getKeywords());
        editTime.setText(lecture.getEditTime());
    }
}