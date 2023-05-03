package com.example.egar_admin.activity;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.egar_admin.adapters.MyFragmentAdapter;
import com.example.egar_admin.databinding.ActivityRegisterBinding;
import com.google.android.material.tabs.TabLayout;


public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private MyFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tabLayoutRegister.addTab(binding.tabLayoutRegister.newTab().setText("admin"));
        binding.tabLayoutRegister.addTab(binding.tabLayoutRegister.newTab().setText("delivery"));


        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new MyFragmentAdapter(fragmentManager , getLifecycle());
        binding.pagerRegister.setAdapter(adapter);


        binding.tabLayoutRegister.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.pagerRegister.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.pagerRegister.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayoutRegister.selectTab(binding.tabLayoutRegister.getTabAt(position));
            }
        });

    }

}