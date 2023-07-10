package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Order;

import java.util.List;

public interface OnOrderStatusFetchListener {
    public void onGetOrderStatusSuccess(String orderStatus);

    public void onGetOrderStatusFailure(String s);

    void onGetOrdersSuccess(List<Order> completedOrders);
}
