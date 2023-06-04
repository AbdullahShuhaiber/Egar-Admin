package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface OnProductFetchListener {
    void onFetchLListSuccess(ArrayList<Product> list,String id);
    void onFetchSuccess(Product product);
    void onFetchFailure(String message);

}
