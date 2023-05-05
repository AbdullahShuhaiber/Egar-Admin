package com.example.egar_admin.Model;

public class Favorites {
    private String favoritesId;
    private String userId;
    private String serviceId;

    public Favorites() {
        // Required empty constructor for Firebase
    }

    public Favorites(String favoritesId, String userId, String serviceId) {
        this.favoritesId = favoritesId;
        this.userId = userId;
        this.serviceId = serviceId;
    }

    // Getters and Setters
    public String getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(String favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
