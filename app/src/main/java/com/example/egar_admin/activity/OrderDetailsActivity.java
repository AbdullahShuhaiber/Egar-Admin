package com.example.egar_admin.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.databinding.ActivityOrderDetailsBinding;


public class OrderDetailsActivity extends AppCompatActivity {
    ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }



}