package com.example.egar_admin.adapters.notifications;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egar_admin.Model.Notification;
import com.example.egar_admin.databinding.NoteficationsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    private List<Notification> notificationList = new ArrayList<>();

    public NotificationAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteficationsItemBinding binding = NoteficationsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.savaData(notificationList.get(position));

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
