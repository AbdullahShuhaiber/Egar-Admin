package com.example.egar_admin.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.egar_admin.fragment.RegisterAdminFragment;
import com.example.egar_admin.fragment.RegisterDeliveryFragment;

public class MyFragmentAdapter extends FragmentStateAdapter {



    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new RegisterDeliveryFragment();
        }
        return new RegisterAdminFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
