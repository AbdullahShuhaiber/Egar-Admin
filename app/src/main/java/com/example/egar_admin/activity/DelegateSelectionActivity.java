package com.example.egar_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.egar_admin.databinding.ActivityDelegateSelectionBinding;

public class DelegateSelectionActivity extends AppCompatActivity {
    ActivityDelegateSelectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDelegateSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}