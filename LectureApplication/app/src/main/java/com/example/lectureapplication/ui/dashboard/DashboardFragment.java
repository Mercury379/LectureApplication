package com.example.lectureapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.data.Appoint;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.databinding.FragmentDashboardBinding;
import com.example.lectureapplication.ui.home.HomeUiAdapter;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashBoardUiAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // 使用 RecyclerView 显示天气信息
        adapter = new DashBoardUiAdapter(getActivity());
        RecyclerView rv = binding.appointInfoRv;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        MyApplication.dashboardViewModel.getAppoints().observe(getActivity(), new Observer<List<Appoint>>() {
            @Override
            public void onChanged(List<Appoint> appoints) {
                adapter.setLectureList(appoints);
            }
        });
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}