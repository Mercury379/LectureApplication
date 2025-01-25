package com.example.lectureapplication.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectureapplication.MyApplication;
import com.example.lectureapplication.R;
import com.example.lectureapplication.data.Appoint;
import com.example.lectureapplication.data.User;
import com.example.lectureapplication.databinding.FragmentDashboardBinding;
import com.example.lectureapplication.databinding.FragmentHome2Binding;
import com.example.lectureapplication.databinding.FragmentUserBinding;
import com.example.lectureapplication.ui.dashboard.DashBoardUiAdapter;

import java.util.List;

public class UserFragment extends Fragment {
    private FragmentUserBinding binding;
    private UserUiAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new UserUiAdapter(getActivity());
        RecyclerView rv = binding.userInfoRv;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        MyApplication.userViewModel.getUsers().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsersList(users);
            }
        });
        binding.buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.userViewModel.queryLectures(binding.queryText.getText().toString());
            }
        });
        return root;
    }

}
