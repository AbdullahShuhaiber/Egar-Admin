package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Provider;

public interface ServiceProviderCallBack {
    void onSuccess(Provider provider);

    void onFailure(String message);
}
