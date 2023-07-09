package com.example.egar_admin.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.egar_admin.fragment.CommentTapFragment;
import com.example.egar_admin.fragment.OfferProductFragment;
import com.example.egar_admin.fragment.OrderTapFragment;
import com.example.egar_admin.fragment.ProductTapFragment;

public class MyFragmentTapAdapter extends FragmentStateAdapter {


    public MyFragmentTapAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }



  /*  public MyFragmentTapAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }*/

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new ProductTapFragment();
        }else if (position == 1){
            return new OrderTapFragment();
        }else if (position == 2){
            return new OfferProductFragment();
        }else
            return new CommentTapFragment();


    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
