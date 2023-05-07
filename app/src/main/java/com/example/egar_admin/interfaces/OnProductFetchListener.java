package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Product;

import java.util.List;

public interface OnProductFetchListener {
    void onFetchLListSuccess(List<Product> list);
    void onFetchSuccess(Product product);
    void onFetchFailure(String message);

}
