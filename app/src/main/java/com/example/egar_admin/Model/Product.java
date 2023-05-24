package com.example.egar_admin.Model;

import android.net.Uri;

public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private Uri imageUrl;
    private boolean isFavorite;
    private int quantityInCart;

    public Product() {
        // Default constructor required for Firestore
    }

    public Product(String name, String description, double price, Uri imageUrl, int quantityInCart) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantityInCart = quantityInCart;
    }

    public Product(String id, String name, String description, double price, Uri imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isFavorite = false;
        this.quantityInCart = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public void addToCart() {
        quantityInCart++;
    }

    public void removeFromCart() {
        if (quantityInCart > 0) {
            quantityInCart--;
        }
    }
}

