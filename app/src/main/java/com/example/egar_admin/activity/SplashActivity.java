package com.example.egar_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.databinding.ActivitySplashBinding;
import com.example.egar_admin.ui.MainActivity;


public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        controlSplashActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void controlSplashActivity() {
        //3000ms - 3s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*getApplicationContext(),GetStarted.class*/
                Intent intent = new Intent(getApplicationContext(), FirebaseAuthController.getInstance().isSignedIn() ? MainActivity.class : LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}