package com.example.tech_service;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tech_service.Fragment.SignInFragment;
import com.example.tech_service.Fragment.SignUpFragment;


public class HomeStartFragment extends Fragment {
    private View view;
    private Button btnHomestart;


    public HomeStartFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_start, container, false);
        init();
        return view;

    }

    private void init() {


        btnHomestart = view.findViewById(R.id.btnGetStarted);


        btnHomestart.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SignInFragment.class);
            startActivity(intent);
        });

    }
}