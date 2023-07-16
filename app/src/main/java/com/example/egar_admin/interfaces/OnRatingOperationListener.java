package com.example.egar_admin.interfaces;

public interface OnRatingOperationListener {
    void onRatingOperationSuccess(String ratingId);

    void onRatingOperationFailure(String message);
}
