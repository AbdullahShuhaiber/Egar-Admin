package com.example.egar_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.databinding.ItemProductShowBinding;

import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeViewHolder> {

     List<Product> products ;


    public ProductHomeAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowBinding binding=ItemProductShowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductHomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeViewHolder holder, int position) {
        holder.savaData(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size() ;
    }




}
