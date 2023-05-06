package com.example.egar_admin.Model;

public class Service {
    private  String id;
    private String name;
    private String description;
    private double price;
    private String image;
    private boolean isFavorite;
    private boolean isInCart;

    public Service() {
    }

    public Service(String id ,String name, String description, double price, String image) {
        this.id =id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.isFavorite = false;
        this.isInCart = false;
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

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public void addToFavorites() {
        this.isFavorite = true;
    }

    public void removeFromFavorites() {
        this.isFavorite = false;
    }

    public void addToCart() {
        this.isInCart = true;
    }

    public void removeFromCart() {
        this.isInCart = false;
    }
}

