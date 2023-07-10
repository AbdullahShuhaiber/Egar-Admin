package com.example.egar_admin.enums;

public enum OrderStatus {
    PENDING("Pending"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    IN_DELIVERY("Delivery"),
    CANCELLED("Cancelled");
    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

