package com.example.lectureapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.data.UserLocalDataSource;
import com.example.lectureapplication.databinding.FragmentNotificationsBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotificationsFragment extends Fragment implements View.OnClickListener {

    private FragmentNotificationsBinding binding;
    public NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText name=binding.username;
        final EditText phone=binding.userPhone;
        final EditText email=binding.userEmail;
        final Spinner lovetype=binding.userTypeSpinner;
        binding.buttonUpdate.setOnClickListener(this);
        binding.buttonReset.setOnClickListener(this);
        String[] type_list=new String[]{"学术讲座","文化讲座","专业讲座","论坛讲座","名人讲座"};
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, type_list);
        // 设置下拉列表的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将Adapter设置给Spinner
        binding.userTypeSpinner.setAdapter(adapter);
        notificationsViewModel.getName().observe(getViewLifecycleOwner(), name::setText);
        notificationsViewModel.getPhone().observe(getViewLifecycleOwner(), phone::setText);
        notificationsViewModel.getEmail().observe(getViewLifecycleOwner(), email::setText);
        notificationsViewModel.getType().observe(getViewLifecycleOwner(), lovetype::setSelection);
        getUser();
        return root;
    }
    public void getUser(){
        User user=UserLocalDataSource.queryByName(MyApplication.user.getName());
        notificationsViewModel.setName(user.getName());
        notificationsViewModel.setEmail(user.getEmail());
        notificationsViewModel.setPhone(user.getPhone());
        String[] type_list=new String[]{"学术讲座","文化讲座","专业讲座","论坛讲座","名人讲座"};
        for(int i=0;i<5;i++){
            if(user.getLovetype().equals(type_list[i])){
                notificationsViewModel.setLovetype(i);
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.buttonUpdate){
            if(validateData()){
                User user=MyApplication.user;
                user.setEmail(binding.userEmail.getText().toString());
                user.setPhone(binding.userPhone.getText().toString());
                user.setLovetype(binding.userTypeSpinner.getSelectedItem().toString());
                UserLocalDataSource.updateUser(user);
                Toast.makeText(getContext(), "修改成功！", Toast.LENGTH_LONG).show();
            }
        }else{
            getUser();
        }
    }
    private boolean validateData(){
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(binding.userEmail.getText().toString());
        if(binding.userPhone.getText().toString().equals("")||binding.userEmail.getText().toString().equals("")){
            Toast.makeText(getContext(), "输入不能为空！", Toast.LENGTH_LONG).show();
            return false;
        }else if(binding.userPhone.getText().toString().length()!=11){
            Toast.makeText(getContext(), "电话输入有误！", Toast.LENGTH_LONG).show();
            return false;
        }else if(matcher.matches()==false){
            Toast.makeText(getContext(), "邮箱输入有误！", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
}