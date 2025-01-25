package com.example.lectureapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.LectureLocalDataSource;
import com.example.lectureapplication.data.UserLocalDataSource;
import com.example.lectureapplication.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeUiAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // 使用 RecyclerView 显示天气信息
        adapter = new HomeUiAdapter(getActivity());
        RecyclerView rv = binding.lectureInfoRv;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        homeViewModel.getLectures().observe(getActivity(), new Observer<List<Lecture>>() {
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
        binding.querySpinner.setAdapter(adapter);
        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.queryLectures(binding.querySpinner.getSelectedItemPosition(),binding.textSearch.getText().toString());
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