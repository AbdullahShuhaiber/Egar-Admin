package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Product;

public interface ItemCallback {

    void onDelete(Product product);

    void onUpdate(int index);
}
