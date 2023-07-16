package com.example.egar_admin.controllers;


import android.media.Rating;

import com.example.egar_admin.interfaces.OnRatingOperationListener;
import com.example.egar_admin.interfaces.OnRatingsFetchListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class RatingController {
    private static final String COLLECTION_NAME = "ratings";

    private FirebaseFirestore db;
    private CollectionReference ratingsCollection;

    public RatingController() {
        db = FirebaseFirestore.getInstance();
        ratingsCollection = db.collection(COLLECTION_NAME);
    }

    public void addRating(Rating rating, OnRatingOperationListener listener) {
        ratingsCollection.add(rating)
                .addOnSuccessListener(documentReference -> {
                    String ratingId = documentReference.getId();
                    listener.onRatingOperationSuccess(ratingId);
                })
                .addOnFailureListener(e -> {
                    listener.onRatingOperationFailure(e.getMessage());
                });
    }

    public void deleteRating(String ratingId, OnRatingOperationListener listener) {
        ratingsCollection.document(ratingId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    listener.onRatingOperationSuccess(ratingId);
                })
                .addOnFailureListener(e -> {
                    listener.onRatingOperationFailure(e.getMessage());
                });
    }

    public void updateRating(String ratingId, Rating updatedRating, OnRatingOperationListener listener) {
        ratingsCollection.document(ratingId)
                .set(updatedRating)
                .addOnSuccessListener(aVoid -> {
                    listener.onRatingOperationSuccess(ratingId);
                })
                .addOnFailureListener(e -> {
                    listener.onRatingOperationFailure(e.getMessage());
                });
    }

    public void getAllRatings(OnRatingsFetchListener listener) {
        ratingsCollection.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<com.example.egar_admin.Model.Rating> ratings = queryDocumentSnapshots.toObjects(com.example.egar_admin.Model.Rating.class);
                    listener.onRatingsFetched(ratings);
                })
                .addOnFailureListener(e -> {
                    listener.onRatingsFetchFailure(e.getMessage());
                });
    }
    public void getRatingsByServiceProviderId(String serviceProviderId, OnRatingsFetchListener listener) {
        ratingsCollection.whereEqualTo("serviceProviderID", serviceProviderId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<com.example.egar_admin.Model.Rating> ratings = queryDocumentSnapshots.toObjects(com.example.egar_admin.Model.Rating.class);
                    listener.onRatingsFetched(ratings);
                })
                .addOnFailureListener(e -> {
                    listener.onRatingsFetchFailure(e.getMessage());
                });
    }

    public void getRatingsByProductId(String productId, OnRatingsFetchListener listener) {
        ratingsCollection.whereEqualTo("productId", productId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<com.example.egar_admin.Model.Rating> ratings = queryDocumentSnapshots.toObjects(com.example.egar_admin.Model.Rating.class);
                    listener.onRatingsFetched(ratings);
                })
                .addOnFailureListener(e -> {
                    listener.onRatingsFetchFailure(e.getMessage());
                });
    }

}