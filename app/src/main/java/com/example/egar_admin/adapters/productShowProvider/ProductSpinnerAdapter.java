package com.example.egar_admin.adapters.productShowProvider;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.databinding.ItemProductShowProviderBinding;

import java.util.List;

public class ProductSpinnerAdapter extends RecyclerView.Adapter<ProductSpinnerViewHolder> {

    List<Product> products;

    public ProductSpinnerAdapter(List<Product> products) {
        this.products = products;
    }


    @NonNull
    @Override
    public ProductSpinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowProviderBinding binding=ItemProductShowProviderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductSpinnerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpinnerViewHolder holder, int position) {
        holder.savaData(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
