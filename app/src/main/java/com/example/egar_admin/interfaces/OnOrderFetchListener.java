package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Order;

import java.util.List;

public interface OnOrderFetchListener {
    void onAddOrderSuccess(String orderId);
    void onAddOrderFailure(String message);

    void onDeleteOrderSuccess();
    void onDeleteOrderFailure(String message);
    void onGetOrdersByServiceProviderIdSuccess(List<Order> orders);
    void onGetOrdersByServiceProviderIdFailure(String message);
}

