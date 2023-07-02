package com.example.egar_admin.interfaces;

public interface ProviderTypeCallback {
    void onProviderTypeChecked(String providerType);
    void onProviderTypeNull(String message);
    void onProviderTypeFailure(String message);
}
