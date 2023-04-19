package com.example.egar_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.databinding.ItemProductsBinding;


public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsViewHolder> {
    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductsBinding binding=ItemProductsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderDetailsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
