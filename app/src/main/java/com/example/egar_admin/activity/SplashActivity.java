package com.example.egar_admin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.R;
import com.example.egar_admin.SharedPreferences.AppSharedPreferences;

import com.example.egar_admin.controllers.AppController;
import com.example.egar_admin.databinding.ActivitySplashBinding;
import com.example.egar_admin.interfaces.SignInStatusListener;
import com.example.egar_admin.ui.MainActivity;


public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(SplashActivity.this, AppSharedPreferences.getInstance().getSharedPreferences().getString("isFirstRun","no"), Toast.LENGTH_SHORT).show();

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


    private void controlSplashActivity () {
        //3000ms - 3s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppSharedPreferences appSharedPreferences = AppSharedPreferences.getInstance();
                String isFirstRun = appSharedPreferences.getSharedPreferences().getString("isFirstRun", "no");

                if (isFirstRun.equals("yse")) {
                    Log.d("isFirstRun", isFirstRun);
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (isFirstRun.equals("no")) {
                    Log.e("isFirstRun2", isFirstRun);
                    Intent intent = new Intent(SplashActivity.this, GetStarted.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(SplashActivity.this, "Pager Error", Toast.LENGTH_SHORT).show();
                }
                FirebaseAuthController.getInstance().isSignedIn(new SignInStatusListener() {
                    @Override
                    public void onUserSignedInAsDeliveryProvider() {
                        Intent intent = new Intent(getApplicationContext(),DeliveryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onUserSignedInAsRegularProvider() {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onUserNotSignedIn() {

                    }
                });
            }
        }, 888);
    }
}
