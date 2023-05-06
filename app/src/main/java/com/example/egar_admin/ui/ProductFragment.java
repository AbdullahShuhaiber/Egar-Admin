package com.example.egar_admin.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;
import com.example.egar_admin.databinding.FragmentHomeBinding;
import com.example.egar_admin.databinding.FragmentProductBinding;
import com.google.android.material.tabs.TabLayout;


public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;

    public ProductFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Wedding clothes"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("equipment"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Homes ,Apartments"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Workspaces"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("cars"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Stores"));
        binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}