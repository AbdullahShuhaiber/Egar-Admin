package com.example.egar_admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.ProductHomeAdapter;
import com.example.egar_admin.adapters.order.OrderAdapter;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.databinding.FragmentOrderTapBinding;
import com.example.egar_admin.interfaces.OnOrderFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class OrderTapFragment extends Fragment {

    FragmentOrderTapBinding binding ;

    OrderAdapter adapter;
    List<Order> orderList = new ArrayList<>();

    public OrderTapFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderTapBinding.inflate(inflater);
        initializeView();
        return binding.getRoot();
    }


    private void initializeView() {
        initializeRecyclerAdapter();
        getOrders();
    }

    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        binding.recOrder.setAdapter(adapter);
        binding.recOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void getOrders(){

        Toast.makeText(getActivity(), ""+FirebaseAuth.getInstance().getUid(), Toast.LENGTH_SHORT).show();
        OrderController.getInstance().getOrdersByServiceProviderId(FirebaseAuth.getInstance().getUid(), new OnOrderFetchListener() {
            @Override
            public void onAddOrderSuccess(String orderId) {

            }

            @Override
            public void onAddOrderFailure(String message) {

            }

            @Override
            public void onDeleteOrderSuccess() {

            }

            @Override
            public void onDeleteOrderFailure(String message) {
                Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onGetOrdersByServiceProviderIdSuccess(List<Order> orders) {
                orderList.clear();
                orderList.addAll(orders);
                adapter.notifyDataSetChanged();
               Toast.makeText(getActivity(), orders.size()+"Size", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onGetOrdersByServiceProviderIdFailure(String message) {
                Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onGetOrdersByStatusSuccess(List<Order> orders) {

                Toast.makeText(getActivity(), orders.size()+"Size", Toast.LENGTH_SHORT).show();


            }
        });
    }
}