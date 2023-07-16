package com.example.egar_admin.adapters.notifications;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Notification;
import com.example.egar_admin.databinding.NoteficationsItemBinding;


public class NotificationViewHolder extends RecyclerView.ViewHolder {
    NoteficationsItemBinding binding;
    public NotificationViewHolder(NoteficationsItemBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    public void savaData(Notification notification){
        binding.tvTitle.setText(notification.getTitle());
        binding.tvMessage.setText(notification.getBody());
        binding.tvDate.setText("");
        binding.tvTime.setText("");
    }

}
