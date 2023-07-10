package com.example.egar_admin.adapters.order;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.databinding.ItemOrderBinding;



public class OrderViewHolder extends RecyclerView.ViewHolder {
    ItemOrderBinding binding;

    public OrderViewHolder(ItemOrderBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    public void savaData(Order order){
        binding.textNameUserOrder.setText(order.getUser().getName());
        binding.textEgarNmae.setText(order.getProduct().getName());
        binding.textOderDate.setText(order.getOrderDate());




    }
}
