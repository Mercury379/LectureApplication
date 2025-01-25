package com.example.lectureapplication.ui.newhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.databinding.FragmentHome2Binding;
import com.example.lectureapplication.databinding.FragmentHomeBinding;
import com.example.lectureapplication.ui.home.HomeUiAdapter;
import com.example.lectureapplication.ui.home.HomeViewModel;

import java.util.List;

public class NewHomeFragment extends Fragment {

    private FragmentHome2Binding binding;
    private NewHomeUiAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        NewHomeViewModel newHomeViewModel =
//                new ViewModelProvider(this).get(NewHomeViewModel.class);

        binding = FragmentHome2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // 使用 RecyclerView 显示天气信息
        adapter = new NewHomeUiAdapter(getActivity());
        RecyclerView rv = binding.lectureInfoRv2;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        MyApplication.newHomeViewModel.getLectures().observe(getActivity(), new Observer<List<Lecture>>() {
            @Override
            public void onChanged(List<Lecture> lectures) {
                adapter.setLectureList(lectures);
            }
        });
        String[] query_list=new String[]{"标题","讲座类型","关键字","日期"};
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, query_list);
        // 设置下拉列表的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将Adapter设置给Spinner
        binding.querySpinner2.setAdapter(adapter);
        binding.buttonSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.newHomeViewModel.queryLectures(binding.querySpinner2.getSelectedItemPosition(),binding.textSearch2.getText().toString());
            }
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}