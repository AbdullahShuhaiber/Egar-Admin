package com.example.egar_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.order.OrderAdapter;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.databinding.ActivityOrderBinding;
import com.example.egar_admin.enums.OrderStatus;
import com.example.egar_admin.interfaces.ItemCallBackOrder;
import com.example.egar_admin.interfaces.OnOrdersWithCountFetchListener;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener, ItemCallBackOrder {

    ActivityOrderBinding binding;

    OrderAdapter adapter;
    List<Order> orderList = new ArrayList<>();

    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();
    }


    private void initializeView() {
        initializeRecyclerAdapter();
        getOrders();
        binding.imgBack.setOnClickListener(this::onClick);
    }

    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        adapter.setCallBackOrder(this::onItemClick);
        binding.recOrder.setAdapter(adapter);
        binding.recOrder.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void getOrders() {

        if (getIntent().getStringExtra("status") == String.valueOf(OrderStatus.PENDING)){
            status = getIntent().getStringExtra("status");

        }
        OrderController.getInstance().getOrdersWithCountByStatusAndServiceProviderId(OrderStatus.PENDING, FirebaseAuth.getInstance().getUid(), new OnOrdersWithCountFetchListener() {
            @Override
            public void onGetOrdersWithCountByStatusSuccess(List<Order> orders, int orderCount) {
                orderList.clear();
                orderList.addAll(orders);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onGetOrdersWithCountByStatusFailure(String message) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_back){
            onBackPressed();
        }
    }

    @Override
    public void onItemClick(Order order) {
        Intent intent = new Intent(getApplicationContext(),OrderDetailsActivity.class);
        intent.putExtra("order", (Serializable) order);
        startActivity(intent);
    }
}