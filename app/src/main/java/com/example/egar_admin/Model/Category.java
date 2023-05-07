package com.example.egar_admin.Model;

import java.util.ArrayList;
import java.util.List;
public class Category {
    private String id ;
    private String name;
    private String description;
    private String imageUrl;
    private List<Category> subcategories;

    public Category() {
        this.name = "";
        this.description = "";
        this.imageUrl = "";
        this.subcategories = new ArrayList<>();
    }

    public Category(String id ,String name, String description, String imageUrl) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.subcategories = new ArrayList<>();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public void addSubcategory(Category subcategory) {
        this.subcategories.add(subcategory);
    }

    public void removeSubcategory(Category subcategory) {
        this.subcategories.remove(subcategory);
    }
}
