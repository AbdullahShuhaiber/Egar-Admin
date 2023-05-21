package com.example.egar_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.egar_admin.R;
import com.example.egar_admin.fragment.DeliveryFragment.DeliveryOrdersFragment;
import com.example.egar_admin.fragment.DeliveryFragment.DeliveryProfileFragment;
import com.example.egar_admin.fragment.DeliveryFragment.DeliveryWalletFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
    }
    @SuppressLint("NonConstantResourceId")
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.menu_orders:
                selectedFragment = new DeliveryOrdersFragment();
                break;
            case R.id.menu_profile:
                selectedFragment = new DeliveryProfileFragment();
                break;
            case R.id.menu_wallet:
                selectedFragment = new DeliveryWalletFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, selectedFragment)
                .commit();

        return true;
    };
}