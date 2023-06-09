package com.example.egar_admin.controllers;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.Model.Provider;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.interfaces.ProductCallback;
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


    public void addProduct(String nameProduct, String description, double price,boolean isFavorite, Uri pickedImageUri, int quantityInCart,String category, Provider provider, ProcessCallback callback) {
        CollectionReference productsCollection = FirebaseFirestore.getInstance().collection("products");

        // Convert product object to a HashMap
        HashMap<String, Object> productData = new HashMap<>();
        productData.put("name", nameProduct);
        productData.put("description", description);
        productData.put("price", price);
        productData.put("isFavorite",isFavorite );
        productData.put("quantityInCart", quantityInCart);
        productData.put("category",category );
        productData.put("provider",provider);

        FirebaseStorage storage = FirebaseStorage.getInstance();

        String imageName = "product_" + System.currentTimeMillis();

        StorageReference imagesRef = storage.getReference().child("product_images").child(imageName);

        UploadTask uploadTask = imagesRef.putFile(pickedImageUri);

        uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }
            return imagesRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();

                productData.put("imageUrl", downloadUri.toString());

                productsCollection.add(productData)
                        .addOnSuccessListener(documentReference -> {
                            String documentId = documentReference.getId();
                            productData.put("id", documentId);
                            callback.onSuccess("Product added with ID: " + documentId);
                        })
                        .addOnFailureListener(e -> {
                            callback.onFailure(e.getMessage());
                        });
            } else {
                callback.onFailure("Error uploading image");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
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

    public void getAllProducts(String providerId, OnProductFetchListener listener) {
        db.collection("products")
                .whereEqualTo("provider.id", providerId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<Product> productList = new ArrayList<>();
                    int productCount = queryDocumentSnapshots.size();
                    AtomicInteger processedCount = new AtomicInteger(0);
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Product product = document.toObject(Product.class);
                        String imageUrl = String.valueOf(product.getImageUrl());
                        FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    product.setImageUrl(uri.toString());

                                    productList.add(product);
                                    int count = processedCount.incrementAndGet();
                                    if (count == productCount) {
                                        listener.onFetchListSuccess(productList, providerId);
                                    }
                                })
                                .addOnFailureListener(e -> {
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

    public void getProductById(String productId, ProductCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference productRef = db.collection("products").document(productId);

        productRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String id = documentSnapshot.getId();
                String name = documentSnapshot.getString("name");
                String description = documentSnapshot.getString("description");
                double price = documentSnapshot.getDouble("price");
                boolean isFavorite = documentSnapshot.getBoolean("isFavorite");
                int quantityInCart = documentSnapshot.getLong("quantityInCart").intValue();
                String category = documentSnapshot.getString("category");
                String providerId = documentSnapshot.getString("providerId");
                String imageUrl = documentSnapshot.getString("imageUrl");

                DocumentReference providerRef = db.collection("serviceproviders").document(providerId);
                providerRef.get().addOnSuccessListener(providerDocumentSnapshot -> {
                    if (providerDocumentSnapshot.exists()) {
                        String providerName = providerDocumentSnapshot.getString("name");
                        String providerEmail = providerDocumentSnapshot.getString("email");
                        String providerPhoneNumber = providerDocumentSnapshot.getString("phoneNumber");

                        Provider provider = new Provider(providerId, providerName, providerEmail, providerPhoneNumber);

                        Product product = new Product(id, name, description, price, isFavorite, quantityInCart, category, provider, imageUrl);
                        callback.onProductFetchSuccess(product);
                    } else {
                        callback.onFailure("Provider document does not exist");
                    }
                }).addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
            } else {
                callback.onFailure("Product document does not exist");
            }
        }).addOnFailureListener(e -> {
            callback.onFailure(e.getMessage());
        });
    }
}


