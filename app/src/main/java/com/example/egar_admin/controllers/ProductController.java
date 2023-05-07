package com.example.egar_admin.controllers;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductController {
    private static ProductController instance;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ProductController() {
        // Private constructor to prevent direct instantiation
    }

    public static ProductController getInstance() {
        if (instance == null) {
            synchronized(ProductController.class) {
                if (instance == null) {
                    instance = new ProductController();
                }
            }
        }
        return instance;
    }


    public void addProduct(Product product, ProcessCallback callback) {
        // Add product to Firestore
        // Get a reference to the products collection in Firestore
        CollectionReference productsCollection = FirebaseFirestore.getInstance().collection("products");

        // Convert product object to a HashMap
        HashMap<String, Object> productData = new HashMap<>();
        productData.put("id", product.getId());
        productData.put("name", product.getName());
        productData.put("description", product.getDescription());
        productData.put("price", product.getPrice());
        productData.put("imageUrl", product.getImageUrl());
        productData.put("isFavorite", product.isFavorite());
        productData.put("quantityInCart", product.getQuantityInCart());

        // Add the product to Firestore
        productsCollection.add(productData)
                .addOnSuccessListener(documentReference -> {
                    // On success, log a message to the console
                    callback.onSuccess("Product added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    // On failure, log an error message to the console
                    callback.onFailure("Error adding product");
                });
    }

    public void updateProduct(Product product,ProcessCallback callback) {
        // Update product in Firestore
        DocumentReference productRef = db.collection("products").document(product.getId());

        productRef.update("name", product.getName(),
                        "description", product.getDescription(),
                        "price", product.getPrice(),
                        "imageUrl", product.getImageUrl())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Product updated successfully");
                        callback.onSuccess("Product updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating product", e);
                        callback.onFailure("Error updating product");
                    }
                });
    }

    public void deleteProduct(Product product,ProcessCallback callback) {
        // Delete product from Firestore
        db.collection("products").document(product.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Product deleted successfully");
                        callback.onSuccess("Product deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting product", e);
                        callback.onFailure("Error deleting product");
                    }
                });
    }

    public void getAllProducts(OnProductFetchListener listener) {
        // Get all products from Firestore
        db.collection("products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Get a list of products
                    List<Product> productList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Product product = document.toObject(Product.class);
                        productList.add(product);
                    }
                    // Return the list of products to the listener
                    listener.onFetchLListSuccess(productList);
                })
                .addOnFailureListener(e -> {
                    // Return the error message to the listener
                    listener.onFetchFailure("Failed to fetch products");
                });
    }

    public void getProductById(String productId, OnProductFetchListener listener) {
        // Get product by ID from Firestore
        DocumentReference docRef = db.collection("products").document(productId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Product product = documentSnapshot.toObject(Product.class);
                    listener.onFetchSuccess(product);
                } else {
                    listener.onFetchFailure("Product not found");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFetchFailure("Failed to fetch product");
            }
        });
    }
}


