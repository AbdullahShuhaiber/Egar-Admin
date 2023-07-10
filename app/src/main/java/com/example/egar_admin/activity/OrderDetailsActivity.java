package com.example.egar_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.databinding.ActivityOrderDetailsBinding;
import com.example.egar_admin.enums.OrderStatus;
import com.example.egar_admin.interfaces.OnOrderStatusFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.ui.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;


public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //initializeView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void initializeView() {
        //initializeRecyclerAdapter();
       // getOrders();
        getDetails();
        setOnClick();
    }

    private void setOnClick(){
        binding.imgBack.setOnClickListener(this::onClick);
        binding.buttonApproval.setOnClickListener(this::onClick);
        binding.buttonCancel.setOnClickListener(this::onClick);
    }


    private Order order(){
        Order order = (Order) getIntent().getSerializableExtra("order");
        return order;
    }


    private void getDetails() {

        Picasso.get().load(order().getProduct().getImageUrl()).into(binding.imageOrder);
        binding.tvNameUserOrder.setText(order().getUser().getName());
        binding.textOrderNumber.setText(String.valueOf(order().getOrderId()));
        binding.textNameOrder.setText(order().getProduct().getName());
        binding.textOrderDate.setText(order().getOrderDate());
        binding.textPrice.setText(String.valueOf(order().getTotalAmount()));

    }

    private void cancel(){

        OrderController.getInstance().updateOrderStatus(order().getOrderId(), OrderStatus.CANCELLED, new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(OrderDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });

    }

    private void approval(){

        OrderController.getInstance().updateOrderStatus(order().getOrderId(), OrderStatus.COMPLETED, new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(OrderDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });

    }

        @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_back){
            onBackPressed();
        }
        if (v.getId() == R.id.button_Approval){
            approval();
        }if (v.getId() == R.id.button_Cancel){
            cancel();
            }
    }



}