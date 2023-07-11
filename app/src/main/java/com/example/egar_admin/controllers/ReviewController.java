package com.example.egar_admin.controllers;
import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.egar_admin.Model.Review;
import com.example.egar_admin.interfaces.ListCallBack;
import com.example.egar_admin.interfaces.OnReviewFetchListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReviewController {

    private static ReviewController instance;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ReviewController() {
        // Private constructor to prevent direct instantiation
    }

    public static ReviewController getInstance() {
        if (instance == null) {
            synchronized (ReviewController.class) {
                if (instance == null) {
                    instance = new ReviewController();
                }
            }
        }
        return instance;
    }

    public void addReview(Review review) {
        // Add review to Firestore
        // Get a reference to the reviews collection in Firestore
        CollectionReference reviewsCollection = FirebaseFirestore.getInstance().collection("reviews");

        // Convert review object to a HashMap
        HashMap<String, Object> reviewData = new HashMap<>();
        reviewData.put("reviewId", review.getReviewId());
        reviewData.put("userId", review.getUserId());
        reviewData.put("serviceId", review.getServiceId());
        reviewData.put("reviewText", review.getReviewText());
        reviewData.put("rating", review.getRating());
        reviewData.put("date", review.getDate());

        // Add the review to Firestore
        reviewsCollection.add(reviewData)
                .addOnSuccessListener(documentReference -> {
                    // On success, log a message to the console
                    Log.d(TAG, "Review added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    // On failure, log an error message to the console
                    Log.e(TAG, "Error adding review", e);
                });
    }

    public void updateReview(Review review) {
        // Update review in Firestore
        DocumentReference reviewRef = db.collection("reviews").document(review.getReviewId());

        reviewRef.update("userId", review.getUserId(),
                        "serviceId", review.getServiceId(),
                        "reviewText", review.getReviewText(),
                        "rating", review.getRating(),
                        "date", review.getDate())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Review updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating review", e);
                    }
                });
    }

    public void deleteReview(Review review) {
        // Delete review from Firestore
        db.collection("reviews").document(review.getReviewId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Review deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting review", e);
                    }
                });
    }
    public void getAllReviews(ListCallBack listCallBack) {
        // Get all reviews from Firestore
        db.collection("reviews")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Get a list of reviews
                    List<Review> reviewList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Review review = document.toObject(Review.class);
                        reviewList.add(review);
                    }
                    listCallBack.onSuccess(reviewList);
                })
                .addOnFailureListener(e -> {
                    // Handle any errors
                    listCallBack.onFul("Failed to fetch reviews");
                });
    }

    public void getReviewsByServiceId(String serviceId, OnReviewFetchListener listener) {
        CollectionReference reviewsCollection = FirebaseFirestore.getInstance().collection("reviews");

        Query query = reviewsCollection.whereEqualTo("serviceId", serviceId);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Review> reviews = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Review review = document.toObject(Review.class);
                    review.setReviewId(document.getId());
                    reviews.add(review);
                }
                listener.onGetReviewsSuccess(reviews);
            } else {
                listener.onGetReviewsFailure(task.getException().getMessage());
            }
        });
    }


    public void getReviewById(String reviewId, OnReviewFetchListener listener) {
        // Get review by ID from Firestore
        DocumentReference docRef = db.collection("reviews").document(reviewId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Review review = documentSnapshot.toObject(Review.class);
                    listener.onFetchSuccess(review);
                } else {
                    listener.onFetchFailure("Review not found");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFetchFailure("Failed to fetch review");
            }
        });
    }

}
