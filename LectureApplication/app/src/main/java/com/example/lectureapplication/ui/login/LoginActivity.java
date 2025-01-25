package com.example.lectureapplication.ui.login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lectureapplication.AdminActivity;
import com.example.lectureapplication.MainActivity;
import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;
import com.example.lectureapplication.data.MyDatabaseHelper;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.data.UserLocalDataSource;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText password;
    private Button buttonLogin;
    private Button buttonRegister;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User user=new User();
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
//        User user1=new User();
//        user1.setAdmin(0,"XiaLi","123");
//        UserLocalDataSource.insertUser(user1);
//        User user2=new User();
//        user2.setUser(0,"HeQuan","123","15071359576","1003@qq.com","学术讲座",0);
//        UserLocalDataSource.insertUser(user2);
//        LectureLocalDataSource.insertLecture(new Lecture(0,"人工智能神经网络概述","张三","介绍神经网络知识","人工智能、神经网络","学术讲座","新1-407","2024-6-2 8:30-10:30","2024-6-1 18:30:00","已发布",3));
//        LectureLocalDataSource.insertLecture(new Lecture(0,"民法典法律知识科普","罗翔","邀请中国政法大学罗翔老师来介绍民法典的相关知识，生动有趣，旁征博引。","民法典、法律、罗翔","名人讲座","新1-406","2024-6-5 8:30-10:30","2024-6-4 8:10:00","已发布",1));
//        LectureLocalDataSource.insertLecture(new Lecture(0,"华为智能基座","李四","华为公司资深工程师传授开发经验","华为、AI、开源","专业讲座","新1-311","2024-6-6 8:30-10:30","2024-6-4 7:00:00","已发布",1));
//        LectureLocalDataSource.insertLecture(new Lecture(0,"文化苦旅读书分享","王五","讨论分享余秋雨的代表作《文化苦旅》","余秋雨、文化苦旅、讨论","论坛讲座","新1-417","2024-6-4 8:30-10:30","2024-6-2 14:00:00","已发布",1));
//        LectureLocalDataSource.insertLecture(new Lecture(0,"openEuler讲座","赵六","介绍openEluer","openEuler、华为","专业讲座","新1-201","2024-6-20 8:30-10:30","2024-6-12 15:30:00","已发布",1));
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        if (v.getId() == R.id.buttonLogin){
            user=UserLocalDataSource.queryByName(name.getText().toString());
            if(user!=null && password.getText().toString().equals(user.getPassword())){
                Toast.makeText(this, "登录成功！", Toast.LENGTH_LONG).show();
                MyApplication.user=user;
                if(user.isAdmin()==1){
                    Toast.makeText(this, "欢迎管理员！", Toast.LENGTH_LONG).show();
                    intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "欢迎普通用户！", Toast.LENGTH_LONG).show();
                    //设置从这个界面跳转到个性签名界面
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }else if(user==null){
                Toast.makeText(this, "用户名不存在！", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "密码输入错误！", Toast.LENGTH_LONG).show();
            }
        }else if(v.getId()==R.id.buttonRegister){
            intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}