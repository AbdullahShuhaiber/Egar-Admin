package com.example.egar_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.ActivityNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar_admin.Model.Notification;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.notifications.NotificationAdapter;
import com.example.egar_admin.controllers.NotificationController;
import com.example.egar_admin.databinding.ActivityNotificationsBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityNotificationsBinding binding;

    List<Notification> notificationList = new ArrayList<>();
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void operationsSccren() {
        setOnClick();
        getWindow().setStatusBarColor(ContextCompat.getColor(NotificationsActivity.this, R.color.white));
    }


    @Override
    protected void onStart() {
        super.onStart();
        initializeRecyclerAdapter();
        operationsSccren();
        getNotificationsFromDatabase();
    }

    private void initializeRecyclerAdapter() {
        adapter = new NotificationAdapter(notificationList);
        binding.recN.setAdapter(adapter);
        binding.recN.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    private void  setOnClick(){
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

    }
    private void getNotificationsFromDatabase(){
        NotificationController.getInstance().getAllNotifications(new NotificationController.NotificationCallback() {
            @Override
            public void onNotificationsLoaded(List<Notification> notifications) {
                //Toast.makeText(Notifications.this, "size"+notifications.size(), Toast.LENGTH_SHORT).show();
                if (notifications.size() == 0 ){
//                    binding.rvNotifications.setVisibility(View.GONE);
                    binding.animationView.setVisibility(View.VISIBLE);
                    binding.textView11.setVisibility(View.VISIBLE);
                }else {
                    notificationList.clear();
                    notificationList.addAll(notifications);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNotificationsError(Exception e) {
                Snackbar.make(binding.getRoot(),e.getMessage(),Snackbar.LENGTH_LONG).show();

            }
        });
    }
}