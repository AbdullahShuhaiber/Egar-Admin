package com.example.egar_admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.activity.OfferActivity;
import com.example.egar_admin.activity.ShowService_Product_Details;
import com.example.egar_admin.adapters.ProductHomeAdapter;
import com.example.egar_admin.controllers.ProductController;

import com.example.egar_admin.databinding.FragmentProductTapBinding;
import com.example.egar_admin.interfaces.ItemCallbackLongProduct;
import com.example.egar_admin.interfaces.ItemCallbackProduct;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.activity.ProductActivity;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductTapFragment extends Fragment implements View.OnClickListener, ItemCallbackProduct , ItemCallbackLongProduct,PopupMenu.OnMenuItemClickListener {

    FragmentProductTapBinding binding;
    private ArrayList<Product> products = new ArrayList<>();
    private ProductHomeAdapter adapter;
    Product product1 = new Product();


    public ProductTapFragment() {
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
        adapter.setCallbackProduct(this::onItemClick);
        adapter.setCallbackLongProduct(this::onItemLongClick);
        binding.rec.setAdapter(adapter);
        binding.rec.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private  void setOnClick() {
        binding.textShowAll.setOnClickListener(this::onClick);
        binding.buttonAddOffer.setOnClickListener(this::onClick);

    }

    private void getProduct(){
        ProductController.getInstance().getAllProducts(FirebaseAuth.getInstance().getUid(), new OnProductFetchListener() {
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
                products.addAll(productList.subList(0, Math.min(3, productList.size())));
                /*int maxItemsToShow = 3;
                int itemsToShow = Math.min(productList.size(), maxItemsToShow);
                for (int i = 0; i < itemsToShow; i++) {
                    Product product = productList.get(i);
                    products.add(product);
                }*/
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchNamesSuccess(ArrayList<String> productNames, String providerId) {

            }

        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.text_showAll){
            Intent intent =new Intent(getActivity(), ProductActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.buttonAddOffer){
            Intent intent =new Intent(getActivity(), OfferActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(getActivity(), ShowService_Product_Details.class);
        intent.putExtra("product", (Serializable) product);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(Product product) {
        product1 =product;
        PopupMenu popup = new PopupMenu(getActivity(), binding.rec);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_click);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_delete:
                Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
                ProductController.getInstance().deleteProduct(product1.getId(), new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        products.remove(products.get(getId()));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });

                return true;
            case R.id.item_update:
                Toast.makeText(getActivity(), "update", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}