package com.example.egar_admin.adapters.order;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.databinding.ItemOrderBinding;
import com.example.egar_admin.databinding.ItemProductShowProviderBinding;
import com.squareup.picasso.Picasso;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    ItemOrderBinding binding;

    public OrderViewHolder(ItemOrderBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    public void savaData(Order order){
        binding.textNameUserOrder.setText(order.getUserId());
        binding.textEgarNmae.setText(order.getOrderId());
        binding.textOderDate.setText(order.getOrderDate());



    }
}
