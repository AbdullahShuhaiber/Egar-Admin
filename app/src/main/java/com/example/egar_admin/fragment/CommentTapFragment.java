package com.example.egar_admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentTapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentTapFragment extends Fragment {

    public CommentTapFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_tap, container, false);
    }
}