package com.example.egar_admin.interfaces;

public interface SignInStatusListener {
    void onUserSignedInAsDeliveryProvider();
    void onUserSignedInAsRegularProvider();
    void onUserNotSignedIn();
}
