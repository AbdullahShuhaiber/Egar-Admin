package com.example.egar_admin.interfaces;

public interface DataCallBack {
    void onSuccess(String name,String address,String number);

    void onFailure(String message);
}
