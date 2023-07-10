package com.example.egar_admin.adapters.order;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.databinding.ItemOrderBinding;
import com.example.egar_admin.interfaces.ItemCallBackOrder;


public class OrderViewHolder extends RecyclerView.ViewHolder {
    ItemOrderBinding binding;

    ItemCallBackOrder callBackOrder;

    public OrderViewHolder(ItemOrderBinding binding,ItemCallBackOrder callBackOrder) {
        super(binding.getRoot());
        this.binding=binding;
        this.callBackOrder=callBackOrder;
    }
    public void savaData(Order order){
        binding.textNameUserOrder.setText(order.getUser().getName());
        binding.textEgarNmae.setText(order.getProduct().getName());
        binding.textOderDate.setText(order.getOrderDate());

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackOrder.onItemClick(order);
            }
        });



    }
}
