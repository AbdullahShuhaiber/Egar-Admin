package com.example.egar_admin.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.ProductAdapter;
import com.example.egar_admin.controllers.ProductController;
import com.example.egar_admin.databinding.FragmentHomeBinding;
import com.example.egar_admin.databinding.FragmentProductBinding;
import com.example.egar_admin.interfaces.ItemCallback;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment implements ItemCallback {

    private FragmentProductBinding binding;
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;

    public ProductFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initializeView();


//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Wedding clothes"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("equipment"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Homes ,Apartments"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Workspaces"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("cars"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Stores"));
//        binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void initializeView() {
        initializeRecyclerAdapter();
        getProduct();
    }

    private void initializeRecyclerAdapter() {
        adapter = new ProductAdapter(products);
        adapter.setCallback(this);
        binding.recyclerViewProduct.setAdapter(adapter);
        binding.recyclerViewProduct.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    private void getProduct(){
        ProductController.getInstance().getAllProducts(new OnProductFetchListener() {
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
        });

    }

    @Override
    public void onDelete(int index) {
        ProductController.getInstance().delete(products.get(index).getId(), new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                adapter.notifyItemRemoved(index);
                Toast.makeText(getActivity(), "product deleted successfully", Toast.LENGTH_SHORT).show();
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