package com.example.egar_admin.Model;

public class Provider {
    private String name;
    private String email;
    private String phoneNumber;
    private String providerType;
    private String password;

    public Provider() {
        // يجب توفير البناء الافتراضي العام للاستخدام مع Firebase Realtime Database
    }

    public Provider(String name, String email, String phoneNumber, String providerType, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.providerType = providerType;
        this.password = password;
    }

    // Getters و Setters لكل حقل بيانات

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
