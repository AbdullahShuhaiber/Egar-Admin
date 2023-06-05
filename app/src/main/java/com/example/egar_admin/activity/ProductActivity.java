package com.example.egar_admin.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.egar_admin.Model.Product;
import com.example.egar_admin.adapters.ProductAdapter;

import com.example.egar_admin.controllers.ProductController;

import com.example.egar_admin.databinding.ActivityProductBinding;
import com.example.egar_admin.interfaces.ItemCallback;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;


public class ProductActivity extends AppCompatActivity implements View.OnClickListener , ItemCallback {


    private ArrayList<Product> products = new ArrayList<>();

    private ProductAdapter adapter;
    ActivityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();

    }


    @Override
    public void onClick(View v) {

    }

    private void initializeView() {
        initializeRecyclerAdapter();
        getProduct();
    }

    private void initializeRecyclerAdapter() {
        adapter = new ProductAdapter(products);
        adapter.setCallback(this);
        binding.recyclerViewProduct.setAdapter(adapter);
        binding.recyclerViewProduct.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
    }

    private void getProduct(){
        ProductController.getInstance().getAllProducts(FirebaseAuth.getInstance().getCurrentUser().getUid(), new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list, String id) {

            }

            @Override
            public void onFetchSuccess(Product product) {

            }

            @Override
            public void onFetchFailure(String message) {

            }

            @Override
            public void onFetchListSuccess(ArrayList<Product> productList, String providerId) {
                products.clear();
                products.addAll(productList);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDelete(int index) {
        ProductController.getInstance().delete(products.get(index).getId(), new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                products.remove(index);
                adapter.notifyDataSetChanged();
               ;
                Toast.makeText(getApplication(), "products deleted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public void onUpdate(int index) {

    }
}