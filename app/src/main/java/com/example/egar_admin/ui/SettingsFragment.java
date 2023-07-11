package com.example.egar_admin.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;
import com.example.egar_admin.activity.ChangePasswordActivity;
import com.example.egar_admin.activity.ChatActivity;
import com.example.egar_admin.activity.FavoriteActivity;
import com.example.egar_admin.activity.Personal_InformationActivity;
import com.example.egar_admin.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    FragmentSettingsBinding binding;
    public SettingsFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater);
        setOnClick();
         return binding.getRoot();

    }
    private void setOnClick(){
        binding.cardPersonalInformation.setOnClickListener(this::onClick);
        binding.changepassword.setOnClickListener(this::onClick);
        binding.favoriteP.setOnClickListener(this::onClick);
        binding.conversations.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_personal_information:
                Intent intent = new Intent(getActivity(), Personal_InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.changepassword:
                Intent intent1 = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent1);
                break;
            case R.id.favorite_p:
                Intent intent2 = new Intent(getActivity(), FavoriteActivity.class);
                startActivity(intent2);
                break;
            case R.id.conversations:
                Intent intent3 = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent3);
                break;
        }
    }
}