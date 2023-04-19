package com.example.egar_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.databinding.ItemCommentBinding;


public class commentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {



    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding binding=ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CommentsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
