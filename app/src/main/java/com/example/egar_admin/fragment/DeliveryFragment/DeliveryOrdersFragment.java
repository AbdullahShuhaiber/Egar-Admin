package com.example.egar_admin.fragment.DeliveryFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.R;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.databinding.FragmentDeliveryOrdersBinding;
import com.example.egar_admin.enums.OrderStatus;
import com.example.egar_admin.interfaces.OnOrderFetchListener;

import java.util.List;


public class DeliveryOrdersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentDeliveryOrdersBinding binding;

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
        binding = FragmentDeliveryOrdersBinding.inflate(inflater);
//        getOrdersByStatusCompleted();
        Toast.makeText(getActivity(), OrderStatus.COMPLETED.toString(), Toast.LENGTH_SHORT).show();
        return binding.getRoot();
    }
    private void getOrdersByStatusCompleted(){
        OrderController.getInstance().getOrdersByStatus(OrderStatus.COMPLETED, new OnOrderFetchListener() {
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

            }

            @Override
            public void onGetOrdersByServiceProviderIdSuccess(List<Order> orders) {

            }

            @Override
            public void onGetOrdersByServiceProviderIdFailure(String message) {

            }

            @Override
            public void onGetOrdersByStatusSuccess(List<Order> orders) {
                Toast.makeText(getActivity(), "تم جلب الطلبات بنجاح"+orders.size(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}