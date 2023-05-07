package com.example.egar_admin.controllers;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar_admin.Model.Category;
import com.example.egar_admin.interfaces.OnCategoryFetchListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesController {
    private static final String TAG = "CategoriesController";
    private static CategoriesController instance;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CategoriesController() {
        // Private constructor to prevent direct instantiation
    }

    public static CategoriesController getInstance() {
        if (instance == null) {
            synchronized(CategoriesController.class) {
                if (instance == null) {
                    instance = new CategoriesController();
                }
            }
        }
        return instance;
    }

    public void addCategory(Category category) {
        // Add category to Firestore
        // Get a reference to the categories collection in Firestore
        CollectionReference categoriesCollection = FirebaseFirestore.getInstance().collection("categories");

        // Convert category object to a HashMap
        HashMap<String, Object> categoryData = new HashMap<>();
        categoryData.put("id", category.getId());
        categoryData.put("name", category.getName());
        categoryData.put("imageUrl", category.getImageUrl());

        // Add the category to Firestore
        categoriesCollection.add(categoryData)
                .addOnSuccessListener(documentReference -> {
                    // On success, log a message to the console
                    Log.d(TAG, "Category added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    // On failure, log an error message to the console
                    Log.e(TAG, "Error adding category", e);
                });
    }

    public void getCategoryById(String categoryId, OnCategoryFetchListener listener) {
        // Get category by ID from Firestore
        DocumentReference docRef = db.collection("categories").document(categoryId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Category category = documentSnapshot.toObject(Category.class);
                    listener.onFetchSuccess(category);
                } else {
                    listener.onFetchFailure("Category not found");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFetchFailure("Failed to fetch category");
            }
        });
    }

    public void getAllCategories(OnCategoryFetchListener listener) {
        // Get all categories from Firestore
        db.collection("categories")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Get a list of categories
                    List<Category> categoryList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Category category = document.toObject(Category.class);
                        categoryList.add(category);
                    }
                    // Pass the list of categories to the listener
                    listener.onFetchAllSuccess(categoryList);
                })
                .addOnFailureListener(e -> {
                    // Handle any errors
                    listener.onFetchFailure("Failed to fetch categories");
                });
    }
}
