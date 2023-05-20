package com.example.egar_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.ActivityNavigator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityNotificationsBinding;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void operationsSccren() {
        setOnClick();
        getWindow().setStatusBarColor(ContextCompat.getColor(NotificationsActivity.this, R.color.white));
    }


    @Override
    protected void onStart() {
        super.onStart();
        operationsSccren();
    }

    private void  setOnClick(){
        binding.backToMain.setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_to_main:
                onBackPressed();
                finish();
                break;
        }
    }
}