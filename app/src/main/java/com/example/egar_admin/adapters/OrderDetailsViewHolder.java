package com.example.egar_admin.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.databinding.ItemProductsBinding;


public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {

    ItemProductsBinding binding;
    public OrderDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        binding=ItemProductsBinding.bind(itemView);
    }

    public void savaData(){

    }
}
