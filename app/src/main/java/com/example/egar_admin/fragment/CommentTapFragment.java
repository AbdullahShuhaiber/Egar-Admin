package com.example.egar_admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.Model.Rating;
import com.example.egar_admin.Model.Review;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.Review.ReviewAdapter;
import com.example.egar_admin.adapters.offers.OffersAdapter;
import com.example.egar_admin.controllers.OfferController;
import com.example.egar_admin.controllers.RatingController;
import com.example.egar_admin.controllers.ReviewController;
import com.example.egar_admin.databinding.FragmentCommentTapBinding;
import com.example.egar_admin.interfaces.OnRatingsFetchListener;
import com.example.egar_admin.interfaces.OnReviewFetchListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class CommentTapFragment extends Fragment {
    FragmentCommentTapBinding binding;
    private List<com.example.egar_admin.Model.Rating> ratingss = new ArrayList<>() ;
    private ReviewAdapter adapter;

    public CommentTapFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentCommentTapBinding.inflate(inflater);
        initializeView();
        return binding.getRoot();
    }

    private void initializeView() {
        initializeRecyclerAdapter();
        getReviews();
    }

    private void initializeRecyclerAdapter() {
        adapter = new ReviewAdapter(ratingss);
        binding.rvReviews.setAdapter(adapter);
        binding.rvReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getReviews() {

        RatingController ratingController = new RatingController();
        ratingController.getRatingsByServiceProviderId(FirebaseAuth.getInstance().getUid(), new OnRatingsFetchListener() {
            @Override
            public void onRatingsFetched(List<Rating> ratings) {
                Toast.makeText(getActivity(), ""+ratings.size(), Toast.LENGTH_SHORT).show();
                ratingss.clear();
                ratingss.addAll(ratings);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorMessage) {

            }

            @Override
            public void onRatingsFetchFailure(String message) {

            }
        });
    }

/*
        ReviewController.getInstance().getReviewsByServiceId(FirebaseAuth.getInstance().getUid(), new OnReviewFetchListener() {
            @Override
            public void onFetchSuccess(Review review) {

            }

            @Override
            public void onFetchFailure(String errorMessage) {

            }

            @Override
            public void onGetReviewsSuccess(List<Review> reviews) {
                reviews.clear();
                reviews.addAll(reviews);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onGetReviewsFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });
*//*

    }
*/
}