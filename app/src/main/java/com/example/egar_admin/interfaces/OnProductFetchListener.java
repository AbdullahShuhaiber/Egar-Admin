package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Product;

public interface OnProductFetchListener {
    void onFetchSuccess(Product product);
    void onFetchFailure(String message);

}
