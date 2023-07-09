package com.example.egar_admin.Model;

import java.io.Serializable;

public class Provider implements Serializable {
    private String id ;
    private String name;
    private String email;
    private String phoneNumber;
    private String providerType;
    private String address;
    private String city;
    private String bio;
    private String image;
    private  String currentDate ;
    private String currentTime ;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public Provider(String name, String email, String phoneNumber, String providerType, String address, String city, String bio, String image,String currentDate,String currentTime) {
        this.name = name;
        this.email = email;
        this.providerType = providerType;
        this.phoneNumber = phoneNumber;
        this.address=address;
        this.city=city;
        this.bio=bio;
        this.image=image;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
    }

    public Provider(String name, String email, String phoneNumber, String providerType) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.providerType = providerType;
    }


    public Provider(String id ,String name, String email, String providerType, String phoneNumber, String address, String city, String bio,String image) {
        this.id =id;
        this.name = name;
        this.email = email;
        this.providerType = providerType;
        this.phoneNumber = phoneNumber;
        this.address=address;
        this.city=city;
        this.bio=bio;
        this.image=image;

    }

    public Provider(String id, String name, String email, String phoneNumber, String providerType) {
        this.id =id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.providerType = providerType;
    }
    public Provider() {
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
