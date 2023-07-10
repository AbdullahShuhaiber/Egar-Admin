package com.example.egar_admin.fragment.DeliveryFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.egar_admin.Model.Order;
import com.example.egar_admin.adapters.order.OrderAdapter;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.interfaces.OnOrderFetchListener;
import com.example.egar_admin.interfaces.OnOrderStatusFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class DeliveryOrdersFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    com.example.egar_admin.databinding.FragmentDeliveryOrdersBinding binding;
    OrderAdapter adapter;
    List<Order> orderList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeliveryOrdersFragment() {
        // Required empty public constructor
    }


    public static DeliveryOrdersFragment newInstance(String param1, String param2) {
        DeliveryOrdersFragment fragment = new DeliveryOrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = com.example.egar_admin.databinding.FragmentDeliveryOrdersBinding.inflate(inflater);
        getOrdersByStatusCompleted();

        initializeRecyclerAdapter();
        return binding.getRoot();
    }
    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        binding.rvOrdersDelivery.setAdapter(adapter);
        binding.rvOrdersDelivery.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void getOrdersByStatusCompleted(){
        OrderController.getInstance().getCompletedOrders(new OnOrderStatusFetchListener() {
            @Override
            public void onGetOrderStatusSuccess(String orderStatus) {

            }

            @Override
            public void onGetOrderStatusFailure(String s) {

            }

            @Override
            public void onGetOrdersSuccess(List<Order> completedOrders) {
                orderList.clear();
                orderList.addAll(completedOrders);
                adapter.notifyDataSetChanged();

            }
        });
//        OrderController.getInstance().getCompletedOrders(new OnOrderStatusFetchListener() {
//            @Override
//            public void onGetOrderStatusSuccess(String orderStatus) {
//
//            }
//
//            @Override
//            public void onGetOrderStatusFailure(String s) {
//
//            }
//
//            @Override
//            public void onGetOrdersSuccess(List<Order> completedOrders) {
//                Toast.makeText(getActivity(), "size"+completedOrders.size(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(getActivity(), "تم جلب الطلبات بنجاح"+completedOrders.size(), Toast.LENGTH_SHORT).show();
////                orderList.clear();
////                orderList.addAll(completedOrders);
////                adapter.notifyDataSetChanged();
//            }
//        });
    }


}