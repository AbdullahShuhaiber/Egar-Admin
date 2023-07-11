package com.example.egar_admin.ui.homeAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;


import com.example.egar_admin.FirebaseManger.FirebaseFetchingDataController;
import com.example.egar_admin.adapters.MyFragmentTapAdapter;


import com.example.egar_admin.databinding.FragmentHomeBinding;
import com.example.egar_admin.interfaces.DataCallBackUser;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
     MyFragmentTapAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //String nameAdmin=FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString().trim();
        //binding.tvNameAdmin.setText(nameAdmin);
        getNameAdmin();

        binding.tab.addTab(binding.tab.newTab().setText("Product"));
        binding.tab.addTab(binding.tab.newTab().setText("Orders"));
        binding.tab.addTab(binding.tab.newTab().setText("Offer"));
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


    public void getNameAdmin(){
        FirebaseFetchingDataController.getInstance().getCurrentUserData(new DataCallBackUser() {
            @Override
            public void onSuccess(String name, String address,String number,String providerImage) {
                binding.tvNameAdmin.setText(name);
                binding.tvLoction.setText(address);
                binding.tvPhoneNumber.setText(number);
                Picasso.get().load(providerImage).into(binding.imgEdit);
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });
    }
}