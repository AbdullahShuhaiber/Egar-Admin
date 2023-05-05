package com.example.egar_admin.Model;

public class Order {
    private String orderId;
    private String customerId;
    private String serviceId;
    private int quantity;
    private double totalAmount;

    public Order() {
        // Required empty constructor for Firebase
    }

    public Order(String orderId, String customerId, String serviceId, int quantity, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

