package com.example.egar_admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.R;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.interfaces.OnOrderFetchListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderTapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderTapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderTapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderTapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderTapFragment newInstance(String param1, String param2) {
        OrderTapFragment fragment = new OrderTapFragment();
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
        getOrders();
        return inflater.inflate(R.layout.fragment_order_tap, container, false);
    }
    private void getOrders(){
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

            }

            @Override
            public void onGetOrdersByServiceProviderIdSuccess(List<Order> orders) {
                Toast.makeText(getActivity(), orders.size()+"Size", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onGetOrdersByServiceProviderIdFailure(String message) {

            }
        });
    }
}