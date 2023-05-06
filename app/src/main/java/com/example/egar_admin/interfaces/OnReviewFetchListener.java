package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Review;

import java.util.List;

public interface OnReviewFetchListener {
    void onFetchSuccess(Review review);
    void onFetchFailure(String errorMessage);
}

