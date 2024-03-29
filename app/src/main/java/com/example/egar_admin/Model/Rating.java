package com.example.egar_admin.Model;

public class Rating {
    private String orderId;
    private String productId;
    private String userId;
    private String serviceProviderID;
    private float ratingValue;
    private String reviewText;
    private String date;

    public Rating() {
    }

    public Rating(String orderId, String productId, String userId, float ratingValue, String reviewText, String date, String serviceProviderID) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.ratingValue = ratingValue;
        this.reviewText = reviewText;
        this.date = date;
        this.serviceProviderID=serviceProviderID;
    }

    public String getServiceProviderID() {
        return serviceProviderID;
    }

    public void setServiceProviderID(String serviceProviderID) {
        this.serviceProviderID = serviceProviderID;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(float ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

