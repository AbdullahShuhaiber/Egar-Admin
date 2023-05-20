package com.example.egar_admin.activity;

import android.os.Bundle;






import androidx.appcompat.app.AppCompatActivity;


import com.example.egar_admin.databinding.ActivityCommentsBinding;
import com.example.egar_admin.databinding.ActivityProductBinding;


public class CommentsActivity extends AppCompatActivity {

    ActivityCommentsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // initializeView();

    }
}