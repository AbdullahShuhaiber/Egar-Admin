package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Order;

import java.util.List;

public interface OnOrdersWithCountFetchListener {
    void onGetOrdersWithCountByStatusSuccess(List<Order> orders, int orderCount);

    void onGetOrdersWithCountByStatusFailure(String message);

}
