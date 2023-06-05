package com.example.egar_admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.ProductHomeAdapter;
import com.example.egar_admin.controllers.ProductController;

import com.example.egar_admin.databinding.FragmentProductTapBinding;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.activity.ProductActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ProductTapFragment extends Fragment implements View.OnClickListener {

    FragmentProductTapBinding binding;
    private ArrayList<Product> products = new ArrayList<>();
    private ProductHomeAdapter adapter;


    public ProductTapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProductTapBinding.inflate(getLayoutInflater());

        initializeView();


        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void initializeView() {
        initializeRecyclerAdapter();
        getProduct();
        setOnClick();
    }

    private void initializeRecyclerAdapter() {
        adapter = new ProductHomeAdapter(products);
        //adapter.setCallback(this);
        binding.rec.setAdapter(adapter);
        binding.rec.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private  void setOnClick() {
        binding.textShowAll.setOnClickListener(this::onClick);
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
    public void onClick(View v) {
        if (v.getId() == R.id.text_showAll){
            Intent intent =new Intent(getActivity(), ProductActivity.class);
            startActivity(intent);
        }
    }

}