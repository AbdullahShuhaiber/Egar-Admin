package com.example.egar_admin.controllers;

import androidx.annotation.NonNull;

import com.example.egar_admin.Model.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class ServicesController {

    private static ServicesController instance;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ServicesController() {
        // Private constructor to prevent direct instantiation
    }

    public static ServicesController getInstance() {
        if (instance == null) {
            synchronized (ServicesController.class) {
                if (instance == null) {
                    instance = new ServicesController();
                }
            }
        }
        return instance;
    }

    public void addService(com.example.egar_admin.Model.Service service, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener) {
        // Add service to FireStore
        // Get a reference to the services collection in Firestore
        CollectionReference servicesCollection = db.collection("services");

        // Convert service object to a HashMap
        HashMap<String, Object> serviceData = new HashMap<>();
        serviceData.put("name", service.getName());
        serviceData.put("description", service.getDescription());
        serviceData.put("price", service.getPrice());
        serviceData.put("image", service.getImage());
        serviceData.put("isFavorite", service.isFavorite());
        serviceData.put("isInCart", service.isInCart());

        // Add the service to FireStore
        servicesCollection.add(serviceData)
                .addOnSuccessListener(successListener)
                .addOnFailureListener(failureListener);
    }

    public void updateService(com.example.egar_admin.Model.Service service, OnSuccessListener<Void> successListener, OnFailureListener failureListener) {
        // Update service in FireStore
        DocumentReference serviceRef = db.collection("services").document(service.getId());

        serviceRef.update("name", service.getName(),
                        "description", service.getDescription(),
                        "price", service.getPrice(),
                        "image", service.getImage(),
                        "isFavorite", service.isFavorite(),
                        "isInCart", service.isInCart())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void deleteService(Service service, OnSuccessListener<Void> successListener, OnFailureListener failureListener) {
        // Delete service from FireStore
        db.collection("services").document(service.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void getAllServices(OnSuccessListener<QuerySnapshot> successListener, OnFailureListener failureListener) {
        // Get all services from FireStore
        db.collection("services")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void getServiceById(String serviceId, OnSuccessListener<DocumentSnapshot> successListener, OnFailureListener failureListener) {
        // Get a service by id from FireStore
        db.collection("services").document(serviceId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}

