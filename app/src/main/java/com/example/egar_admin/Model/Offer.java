package com.example.egar_admin.Model;

import java.util.Date;

public class Offer {
    private String id;
    private String productId;
    private double price;
    private int quantity;
    private String startDate;
    private String endDate;

    public Offer() {
    }

    public Offer(String productId, double price, int quantity, String startDate, String endDate) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Offer(String id, String productId, double price, int quantity, String startDate, String endDate) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
