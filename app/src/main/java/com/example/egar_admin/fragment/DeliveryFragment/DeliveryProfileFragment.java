package com.example.egar_admin.fragment.DeliveryFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;
import com.example.egar_admin.activity.ChangePasswordActivity;
import com.example.egar_admin.activity.Personal_InformationActivity;
import com.example.egar_admin.databinding.FragmentDeliveryProfileBinding;


public class DeliveryProfileFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentDeliveryProfileBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeliveryProfileFragment() {
        // Required empty public constructor
    }


    public static DeliveryProfileFragment newInstance(String param1, String param2) {
        DeliveryProfileFragment fragment = new DeliveryProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryProfileBinding.inflate(inflater);
        setOnClick();
        return binding.getRoot();
    }
    private void setOnClick(){
        binding.cardPersonalInformation.setOnClickListener(this);
        binding.language.setOnClickListener(this);
        binding.changepassword.setOnClickListener(this);
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
            case R.id.language:
                break;
        }

    }
}