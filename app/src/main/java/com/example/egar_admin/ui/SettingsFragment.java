package com.example.egar_admin.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;
import com.example.egar_admin.activity.ChangePasswordActivity;
import com.example.egar_admin.activity.FavoriteActivity;
import com.example.egar_admin.activity.PersonalInformationActivity;
import com.example.egar_admin.databinding.FragmentSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentSettingsBinding binding ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        binding = FragmentSettingsBinding.inflate(inflater);
        setOnClick();
         return binding.getRoot();

    }
    private void setOnClick(){
        binding.cardPersonalInformation.setOnClickListener(this::onClick);
        binding.changepassword.setOnClickListener(this::onClick);
        binding.favoriteP.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_personal_information:
                Intent intent = new Intent(getActivity(), PersonalInformationActivity.class);
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
        }
    }
}