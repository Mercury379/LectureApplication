package com.example.lectureapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lectureapplication.R;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.data.UserLocalDataSource;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner typeSpinner;
    private EditText name;
    private EditText password;
    private EditText password1;
    private EditText phone;
    private EditText email;
    private Button buttonRegister;
    private Button buttonReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        typeSpinner=(Spinner) findViewById(R.id.typeSpinner);
        String[] type_list=new String[]{"学术讲座","文化讲座","专业讲座","论坛讲座","名人讲座"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type_list);
        typeSpinner.setAdapter(adapter);
        name=(EditText) findViewById(R.id.editName);
        password=(EditText) findViewById(R.id.editPassword);
        password1=(EditText) findViewById(R.id.editPassword1);
        phone=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email);
        buttonRegister=(Button) findViewById(R.id.buttonRegisterUser);
        buttonRegister.setOnClickListener(this);
        buttonReturn=(Button) findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(this);
    }
    private boolean validateData(){
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email.getText().toString());
        if(!isExist(name.getText().toString())){
            Toast.makeText(this, "用户名已存在！", Toast.LENGTH_LONG).show();
            return false;
        }else if(name.getText().toString().equals("")||password.getText().toString().equals("")||phone.getText().toString().equals("")||email.getText().toString().equals("")||password1.getText().toString().equals("")){
            Toast.makeText(this, "输入不能为空！", Toast.LENGTH_LONG).show();
            return false;
        }else if(!password.getText().toString().equals(password1.getText().toString())){
            Toast.makeText(this, "密码与确认密码不一致！", Toast.LENGTH_LONG).show();
            return false;
        }else if(phone.getText().toString().length()!=11){
            Toast.makeText(this, "电话输入有误！", Toast.LENGTH_LONG).show();
            return false;
        }else if(matcher.matches()==false){
            Toast.makeText(this, "邮箱输入有误！", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
    public boolean isExist(String name){
        List<User> userList=UserLocalDataSource.getAllUsers();
        for(int i=0;i<userList.size();i++){
            if(name.equals(userList.get(i).getName())){
                return false;
            }
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonRegisterUser){
            if(validateData()){
                saveData();
                Toast.makeText(this, "注册成功！", Toast.LENGTH_LONG).show();
            }
        }else if(v.getId()==R.id.buttonReturn){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void saveData() {
        User user=new User();
        user.setUser(0,name.getText().toString(),password.getText().toString(),phone.getText().toString(),email.getText().toString(),typeSpinner.getSelectedItem().toString(),0);
        UserLocalDataSource.insertUser(user);
    }
}