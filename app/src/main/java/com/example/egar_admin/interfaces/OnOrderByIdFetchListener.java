package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Order;

public interface OnOrderByIdFetchListener {
    void onGetOrderSuccess(Order order);

    void onGetOrderFailure(String order_not_found);

}
