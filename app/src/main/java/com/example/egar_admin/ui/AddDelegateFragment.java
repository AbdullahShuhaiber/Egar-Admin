package com.example.egar_admin.ui;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;
import com.example.egar_admin.databinding.FragmentAddDelegateBinding;

import com.google.android.material.snackbar.Snackbar;


public class AddDelegateFragment extends Fragment {

    private FragmentAddDelegateBinding binding;
    public AddDelegateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddDelegateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment

        return root;
    }


}