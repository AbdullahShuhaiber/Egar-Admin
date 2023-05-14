package com.example.egar_admin.ui.homeAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;


import com.example.egar_admin.adapters.MyFragmentTapAdapter;

import com.example.egar_admin.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
     MyFragmentTapAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tab.addTab(binding.tab.newTab().setText("Categories"));
        binding.tab.addTab(binding.tab.newTab().setText("orders"));
        binding.tab.addTab(binding.tab.newTab().setText("Comments"));

       // FragmentManager fragmentManager = getSupportFragmentManager();
        //adapter = new MyFragmentTapAdapter(fragmentManager , getLifecycle());
        adapter = new MyFragmentTapAdapter(getParentFragment());
        binding.viewPager.setAdapter(adapter);

        binding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tab.selectTab(binding.tab.getTabAt(position));
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}