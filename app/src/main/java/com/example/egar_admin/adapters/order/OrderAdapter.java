package com.example.egar_admin.adapters.order;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.databinding.ItemOrderBinding;
import com.example.egar_admin.databinding.ItemProductShowProviderBinding;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding=ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.savaData(orders.get(position));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
