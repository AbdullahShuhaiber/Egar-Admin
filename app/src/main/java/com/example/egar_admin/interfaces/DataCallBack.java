package com.example.egar_admin.interfaces;

public interface DataCallBack {
    void onSuccess(String name,String address,String number,String providerImage);

    void onFailure(String message);
}
