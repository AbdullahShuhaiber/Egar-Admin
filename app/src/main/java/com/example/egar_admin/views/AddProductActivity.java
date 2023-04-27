package com.example.egar_admin.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.databinding.ActivityAddProductBinding;


public class AddProductActivity extends AppCompatActivity {
    ActivityAddProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}