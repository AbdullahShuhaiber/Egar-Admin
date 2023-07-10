package com.example.egar_admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.R;
import com.example.egar_admin.activity.OrderActivity;
import com.example.egar_admin.adapters.ProductHomeAdapter;
import com.example.egar_admin.adapters.order.OrderAdapter;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.databinding.FragmentOrderTapBinding;
import com.example.egar_admin.enums.OrderStatus;
import com.example.egar_admin.interfaces.OnOrderFetchListener;
import com.example.egar_admin.interfaces.OnOrdersWithCountFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class OrderTapFragment extends Fragment implements View.OnClickListener {

    FragmentOrderTapBinding binding ;

    OrderAdapter adapter;
    List<Order> orderList = new ArrayList<>();
    List<Order> orderList2 = new ArrayList<>();
    List<Order> orderList3 = new ArrayList<>();
    List<Order> orderList4 = new ArrayList<>();


    int sizeCancelled = 0;
    int sizeCompleted = 0;
    int sizeProgress = 0;
    int sizePending = 0;


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
        //getOrders();
        getOrderPending();
        getOrderInProgress();
        getOrderCompleted();
        getOrderCancelled();
        setOnClick();
    }

    private void initializeRecyclerAdapter() {
        adapter = new OrderAdapter(orderList);
        binding.recOrderPending.setAdapter(adapter);
        binding.recOrderPending.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new OrderAdapter(orderList2);
        binding.recOrderInProgress.setAdapter(adapter);
        binding.recOrderInProgress.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new OrderAdapter(orderList3);
        binding.recOrderCompleted.setAdapter(adapter);
        binding.recOrderCompleted.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new OrderAdapter(orderList4);
        binding.recOrderCancelled.setAdapter(adapter);
        binding.recOrderCancelled.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    private void setOnClick(){
        binding.LayoutPending.setOnClickListener(this::onClick);
        binding.LayoutInProgress.setOnClickListener(this::onClick);
        binding.LayoutCompleted.setOnClickListener(this::onClick);
        binding.LayoutCancelled.setOnClickListener(this::onClick);
        binding.tvPendingShowAll.setOnClickListener(this::onClick);
    }

    private void getOrderPending(){
        OrderController.getInstance().getOrdersWithCountByStatusAndServiceProviderId(OrderStatus.PENDING, FirebaseAuth.getInstance().getUid(), new OnOrdersWithCountFetchListener() {
            @Override
            public void onGetOrdersWithCountByStatusSuccess(List<Order> orders, int orderCount) {
                orderList.clear();
                orderList.addAll(orders);
                adapter.notifyDataSetChanged();
                binding.textCountOrderPending.setText(String.valueOf(orders.size()));
                sizePending = orders.size();
            }

            @Override
            public void onGetOrdersWithCountByStatusFailure(String message) {

            }
        });

    }

    private void getOrderInProgress(){
        OrderController.getInstance().getOrdersWithCountByStatusAndServiceProviderId(OrderStatus.IN_PROGRESS, FirebaseAuth.getInstance().getUid(), new OnOrdersWithCountFetchListener() {
            @Override
            public void onGetOrdersWithCountByStatusSuccess(List<Order> orders, int orderCount) {
                orderList2.clear();
                orderList2.addAll(orders);
                adapter.notifyDataSetChanged();
                binding.textCountOrderInProgress.setText(String.valueOf(orders.size()));
                sizeProgress = orders.size();
            }

            @Override
            public void onGetOrdersWithCountByStatusFailure(String message) {

            }
        });

    }

    private void getOrderCompleted(){
        OrderController.getInstance().getOrdersWithCountByStatusAndServiceProviderId(OrderStatus.COMPLETED, FirebaseAuth.getInstance().getUid(), new OnOrdersWithCountFetchListener() {
            @Override
            public void onGetOrdersWithCountByStatusSuccess(List<Order> orders, int orderCount) {
                orderList3.clear();
                orderList3.addAll(orders);
                adapter.notifyDataSetChanged();
                binding.textCountOrderCompleted.setText(String.valueOf(orders.size()));
                sizeCompleted = orders.size();
            }

            @Override
            public void onGetOrdersWithCountByStatusFailure(String message) {

            }
        });

    }

    private void getOrderCancelled(){
        OrderController.getInstance().getOrdersWithCountByStatusAndServiceProviderId(OrderStatus.CANCELLED, FirebaseAuth.getInstance().getUid(), new OnOrdersWithCountFetchListener() {
            @Override
            public void onGetOrdersWithCountByStatusSuccess(List<Order> orders, int orderCount) {
                orderList4.clear();
                orderList4.addAll(orders);
                adapter.notifyDataSetChanged();
                binding.textCountOrderCancelled.setText(String.valueOf(orders.size()));
                sizeCancelled = orders.size();
            }

            @Override
            public void onGetOrdersWithCountByStatusFailure(String message) {

            }
        });

    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.LayoutPending){
            if (binding.recOrderPending.getVisibility() == View.GONE){
                binding.recOrderPending.setVisibility(View.VISIBLE);
                if (sizePending > 0){
                    binding.tvPendingShowAll.setVisibility(View.VISIBLE);
                }
            }else if (binding.recOrderPending.getVisibility() == View.VISIBLE){
                binding.recOrderPending.setVisibility(View.GONE);
                binding.tvPendingShowAll.setVisibility(View.GONE);
            }
        }
        if (v.getId() == R.id.LayoutInProgress){
            if (binding.recOrderInProgress.getVisibility() == View.GONE){
                binding.recOrderInProgress.setVisibility(View.VISIBLE);
                if (sizeProgress > 0) {
                    binding.tvInProgressShowAll.setVisibility(View.VISIBLE);
                }

            }else if (binding.recOrderInProgress.getVisibility() == View.VISIBLE){
                binding.recOrderInProgress.setVisibility(View.GONE);
                binding.tvInProgressShowAll.setVisibility(View.GONE);

            }
        }
        if (v.getId() == R.id.LayoutCompleted){
            if (binding.recOrderCompleted.getVisibility() == View.GONE){
                binding.recOrderCompleted.setVisibility(View.VISIBLE);
                if (sizeCompleted > 0) {
                    binding.tvCompletedShowAll.setVisibility(View.VISIBLE);
                }

            }else if (binding.recOrderCompleted.getVisibility() == View.VISIBLE){
                binding.recOrderCompleted.setVisibility(View.GONE);
                    binding.tvCompletedShowAll.setVisibility(View.GONE);
            }
        }
        if (v.getId() == R.id.LayoutCancelled){
            if (binding.recOrderCancelled.getVisibility() == View.GONE){
                binding.recOrderCancelled.setVisibility(View.VISIBLE);
                if (sizeCancelled > 0) {
                    binding.tvCancelledShowAll.setVisibility(View.VISIBLE);
                }

            }else if (binding.recOrderCancelled.getVisibility() == View.VISIBLE){
                binding.recOrderCancelled.setVisibility(View.GONE);
                binding.tvCancelledShowAll.setVisibility(View.GONE);

            }
        }
        if (v.getId() == R.id.tv_Pending_show_all){
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("status",String.valueOf(OrderStatus.PENDING));
            startActivity(intent);
        }
        if (v.getId() == R.id.tv_InProgress_show_all){
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("status",String.valueOf(OrderStatus.IN_PROGRESS));
            startActivity(intent);
        }
        if (v.getId() == R.id.tv_Completed_show_all){
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("status",String.valueOf(OrderStatus.COMPLETED));
            startActivity(intent);
        }
        if (v.getId() == R.id.tv_Cancelled_show_all){
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("status",String.valueOf(OrderStatus.CANCELLED));
            startActivity(intent);
        }


    }
}