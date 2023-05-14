package com.example.egar_admin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.R;
import com.example.egar_admin.SharedPreferences.AppSharedPreferences;

import com.example.egar_admin.databinding.ActivitySplashBinding;
import com.example.egar_admin.ui.MainActivity;


public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
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

/*    private boolean showViewPagerAndGoToNextScreenIfNeeded() {
        boolean isFirstRun = AppSharedPreferences.getInstance().getSharedPreferences().getBoolean("isFirstRun", true);
        if (isFirstRun) {
            // عرض ViewPager
            Intent intent = new Intent(getApplicationContext(), GetStarted.class);
            startActivity(intent);

            // ...
            // تحديث حالة عرض الـViewPager
            AppSharedPreferences.getInstance().getEditor().putBoolean("isFirstRun", false).apply();
            return true;
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return false;
        }
    }*/



    private void controlSplashActivity () {
        //3000ms - 3s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*getApplicationContext(),GetStarted.class*/
                FirebaseAuthController authController = FirebaseAuthController.getInstance();
                if (authController != null && authController.isSignedIn() /*&& showViewPagerAndGoToNextScreenIfNeeded()*/) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), GetStarted.class);
                    startActivity(intent);
                }

            }
        }, 3000);
    }
}
