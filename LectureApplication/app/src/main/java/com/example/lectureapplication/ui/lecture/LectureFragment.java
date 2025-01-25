package com.example.lectureapplication.ui.lecture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Lecture;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.databinding.FragmentLectureBinding;
import com.example.lectureapplication.databinding.FragmentUserBinding;
import com.example.lectureapplication.ui.user.UserUiAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LectureFragment extends Fragment {

    private FragmentLectureBinding binding;
    private LectureUiAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLectureBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new LectureUiAdapter(getActivity());
        RecyclerView rv = binding.alllectureInfoRv;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        MyApplication.lectureViewModel.getLectures().observe(getActivity(), new Observer<List<Lecture>>() {
            @Override
            public void onChanged(List<Lecture> lectures) {
                adapter.setLecturesList(lectures);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InsertLectureActivity.class);
                // 向下一个 Activity 传递数据
                startActivity(intent);
            }
        });
        return root;
    }
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}