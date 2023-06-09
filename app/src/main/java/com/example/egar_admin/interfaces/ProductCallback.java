package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Product;

import java.util.List;

public interface ProductCallback {
    void onSuccess(List<Product> productList);
    void onProductFetchSuccess(Product product);
    void onFailure(String message);
}
