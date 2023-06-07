package com.example.egar_admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityDeliveryBinding;
import com.example.egar_admin.fragment.DeliveryFragment.DeliveryOrdersFragment;
import com.example.egar_admin.fragment.DeliveryFragment.DeliveryProfileFragment;
import com.example.egar_admin.fragment.DeliveryFragment.DeliveryWalletFragment;
import com.example.egar_admin.ui.homeAdmin.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeliveryActivity extends AppCompatActivity implements View.OnClickListener{

    int selectedTab = 1;
    ActivityDeliveryBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        initializeView();
    }
/*    @SuppressLint("NonConstantResourceId")
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
    };*/

    private void initializeView(){
        onSelectedTab();
        setOnClick();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.deliverymenu, menu);
        setTitle("Delivery");
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private  void setOnClick(){
        binding.ordersLayout.setOnClickListener(this::onClick);
        binding.profileLayout.setOnClickListener(this::onClick);
        binding.walletLayout.setOnClickListener(this::onClick);
    }

    private void onSelectedTab(){
        if (selectedTab ==1){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView, DeliveryOrdersFragment.class,null)
                    .commit();

            binding.profileImage.setBackgroundResource(R.drawable.baseline_supervised_user_circle_24);
            binding.walletImage.setBackgroundResource(R.drawable.baseline_account_balance_wallet_24);

            binding.tvOrders.setVisibility(View.VISIBLE);
            binding.ordersImage.setBackgroundResource(R.drawable.ic_orders);
            binding.ordersLayout.setBackgroundResource(R.drawable.home_round);

        }

        if (selectedTab ==2){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView,DeliveryProfileFragment.class,null)
                    .commit();

            binding.tvProfile.setVisibility(View.VISIBLE);
            binding.profileImage.setBackgroundResource(R.drawable.baseline_supervised_user_circle_24);
            binding.profileLayout.setBackgroundResource(R.drawable.home_round);

        }

        if (selectedTab ==3){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentView,DeliveryWalletFragment.class,null)
                    .commit();

            binding.tvWallet.setVisibility(View.VISIBLE);
            binding.walletImage.setBackgroundResource(R.drawable.baseline_account_balance_wallet_24);
            binding.walletLayout.setBackgroundResource(R.drawable.home_round);

        }


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ordersLayout){
            if (selectedTab !=1){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView, DeliveryOrdersFragment.class,null)
                        .commit();

                binding.tvProfile.setVisibility(View.GONE);
                binding.tvWallet.setVisibility(View.GONE);



                binding.profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.walletLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                binding.tvOrders.setVisibility(View.VISIBLE);
                binding.ordersImage.setBackgroundResource(R.drawable.ic_orders);
                binding.ordersLayout.setBackgroundResource(R.drawable.home_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.ordersLayout.startAnimation(scaleAnimation);

                selectedTab = 1;
            }

        }
        if (v.getId() == R.id.profileLayout){
            if (selectedTab !=2){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView,DeliveryProfileFragment.class,null)
                        .commit();
                binding.tvWallet.setVisibility(View.GONE);
                binding.tvOrders.setVisibility(View.GONE);


                binding.ordersLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.walletLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                binding.tvProfile.setVisibility(View.VISIBLE);
                binding.profileImage.setBackgroundResource(R.drawable.baseline_supervised_user_circle_24);
                binding.profileLayout.setBackgroundResource(R.drawable.home_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.profileLayout.startAnimation(scaleAnimation);

                selectedTab = 2;
            }
        }
        if (v.getId() == R.id.walletLayout){
            if (selectedTab !=3){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView, DeliveryWalletFragment.class,null)
                        .commit();
                binding.tvProfile.setVisibility(View.GONE);
                binding.tvOrders.setVisibility(View.GONE);

                binding.ordersLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                binding.profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                binding.tvWallet.setVisibility(View.VISIBLE);
                binding.walletImage.setBackgroundResource(R.drawable.baseline_account_balance_wallet_24);
                binding.walletLayout.setBackgroundResource(R.drawable.home_round);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(400);
                scaleAnimation.setFillAfter(true);
                binding.walletLayout.startAnimation(scaleAnimation);

                selectedTab = 3;
            }
        }
    }

    private void logout() {
        FirebaseAuthController.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}