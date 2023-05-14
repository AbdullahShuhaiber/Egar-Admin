package com.example.egar_admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.ProductAdapter;
import com.example.egar_admin.controllers.ProductController;
import com.example.egar_admin.databinding.FragmentProductTapBinding;
import com.example.egar_admin.interfaces.OnProductFetchListener;

import java.util.ArrayList;

public class ProductTapFragment extends Fragment {

    FragmentProductTapBinding binding;
    private ArrayList<Product> products = new ArrayList<>();
    //private ProductAdapter adapter;


    public ProductTapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        getCategory();
    }

    private void initializeRecyclerAdapter() {
       // adapter = new ProductAdapter(products);
        //adapter.setCallback(this);
        //binding.rec.setAdapter(adapter);
        //binding.rec.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    private void getCategory(){
/*        ProductController.getInstance().getAllProducts(new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list) {
                products.clear();
                products.addAll(list);
                Log.d("EGAR", "onFetchLListSuccess: ");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchSuccess(Product product) {
                Log.d("EGAR", "onFetchSuccess: ");
            }

            @Override
            public void onFetchFailure(String message) {
                Log.d("EGAR", "onFetchFailure: ");

            }
        });*/



    }

}