package com.example.egar_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityShowServiceProductDetailsBinding;
import com.squareup.picasso.Picasso;

public class ShowService_Product_Details extends AppCompatActivity implements View.OnClickListener {

    ActivityShowServiceProductDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowServiceProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDetails();
       // getOffer();

    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowService_Product_Details.this, R.color.black));
    }

    @Override
    protected void onStart() {
        super.onStart();
       // operationsSccren();
    }

    private Product product(){
        Product product = (Product) getIntent().getSerializableExtra("product");
        return product;
    }




    private void getDetails(){
        binding.tvTextProductName.setText(product().getName());
        binding.tvTextDescription.setText(product().getDescription());
        binding.tvPrice.setText(String.valueOf(product().getPrice()));
        Picasso.get().load(product().getImageUrl()).into(binding.productImg);


    }

    @Override
    public void onClick(View v) {

    }
}