package com.example.egar_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.databinding.ItemProductShowBinding;
import com.example.egar_admin.interfaces.ItemCallbackLongProduct;
import com.example.egar_admin.interfaces.ItemCallbackProduct;

import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeViewHolder> {

    private List<Product> products ;
    private ItemCallbackProduct callbackProduct;
    private ItemCallbackLongProduct callbackLongProduct;



    public ProductHomeAdapter(List<Product> products) {
        this.products = products;
    }

    public void setCallbackLongProduct(ItemCallbackLongProduct callbackLongProduct) {
        this.callbackLongProduct = callbackLongProduct;
    }

    public void setCallbackProduct(ItemCallbackProduct callbackProduct) {
        this.callbackProduct = callbackProduct;
    }
    @NonNull
    @Override
    public ProductHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowBinding binding=ItemProductShowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductHomeViewHolder(binding,callbackProduct,callbackLongProduct);
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
