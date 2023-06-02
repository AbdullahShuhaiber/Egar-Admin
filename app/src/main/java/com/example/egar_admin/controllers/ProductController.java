package com.example.egar_admin.controllers;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductController {
    private static ProductController instance;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ProductController() {
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


    public void addProduct(Product product, Uri imageUrl, ProcessCallback callback) {
        // Get a reference to the products collection in Firestore
        CollectionReference productsCollection = FirebaseFirestore.getInstance().collection("products");

        // Convert product object to a HashMap
        HashMap<String, Object> productData = new HashMap<>();
        productData.put("name", product.getName());
        productData.put("description", product.getDescription());
        productData.put("price", product.getPrice());
        productData.put("isFavorite", product.isFavorite());
        productData.put("quantityInCart", product.getQuantityInCart());
        productData.put("serviceProviderId", product.getServiceProviderId()); // Add merchantId to product data
        productData.put("category", product.getCategory()); // Add category to product data

        FirebaseStorage storage = FirebaseStorage.getInstance();

        String imageName = "product_" + System.currentTimeMillis();

        StorageReference imagesRef = storage.getReference().child("product_images").child(imageName);

        UploadTask uploadTask = imagesRef.putFile(imageUrl);

        uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            // Return the download URL of the uploaded image
            return imagesRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Get the URL of the uploaded image
                Uri downloadUri = task.getResult();

                // Add the image URL to the product data
                productData.put("imageUrl", downloadUri.toString());

                // Add the product data to Firestore
                productsCollection.add(productData)
                        .addOnSuccessListener(documentReference -> {
                            String documentId = documentReference.getId();
                            callback.onSuccess("Product added with ID: " + documentId);
                        })
                        .addOnFailureListener(e -> {
                            callback.onFailure("Error adding product");
                        });
            } else {
                callback.onFailure("Error uploading image");
            }
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

    public void getAllProducts(String serviceProviderId, OnProductFetchListener listener) {
        // Get all products from Firestore where merchantId matches
        db.collection("products")
                .whereEqualTo("serviceProviderId", serviceProviderId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Get a list of products
                    ArrayList<Product> productList = new ArrayList<>();
                    int productCount = queryDocumentSnapshots.size(); // Total product count
                    AtomicInteger processedCount = new AtomicInteger(0); // Number of products processed so far

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Retrieve product data
                        Product product = document.toObject(Product.class);

                        // Retrieve image URL from the product data
                        String imageUrl = String.valueOf(product.getImageUrl());

                        // Download the image from Firebase Storage
                        FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    // Set the downloaded image URL to the product
                                    product.setImageUrl(Uri.parse(uri.toString()));

                                    // Add the product to the list
                                    productList.add(product);

                                    // Increase the processed count
                                    int count = processedCount.incrementAndGet();

                                    // Check if all products have been processed
                                    if (count == productCount) {
                                        // Return the list of products to the listener
                                        listener.onFetchLListSuccess(productList);
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    // Handle image download failure
                                    listener.onFetchFailure("Failed to download product image");
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    // Return the error message to the listener
                    listener.onFetchFailure("Failed to fetch products");
                });
    }
    public void delete(String path, ProcessCallback callback) {
        db.collection("products").document(path).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.onSuccess("Deleted successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure("Delete failed");
            }
        });
    }

    public void getProductById(String productId, OnProductFetchListener listener) {
        // Get product by ID from Firestore
        DocumentReference docRef = db.collection("products").document(productId);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                // Retrieve product data
                Product product = documentSnapshot.toObject(Product.class);

                // Retrieve image URL from the product data
                Uri imageUrl = product.getImageUrl();

                // Download the image from Firebase Storage
                FirebaseStorage.getInstance().getReferenceFromUrl(String.valueOf(imageUrl)).getDownloadUrl()
                        .addOnSuccessListener(uri -> {
                            // Set the downloaded image URL to the product
                            product.setImageUrl(Uri.parse(uri.toString()));

                            // Return the product to the listener
                            listener.onFetchSuccess(product);
                        })
                        .addOnFailureListener(e -> {
                            // Handle image download failure
                            listener.onFetchFailure("Failed to download product image");
                        });
            } else {
                listener.onFetchFailure("Product not found");
            }
        }).addOnFailureListener(e -> {
            listener.onFetchFailure("Failed to fetch product");
        });
    }
}


