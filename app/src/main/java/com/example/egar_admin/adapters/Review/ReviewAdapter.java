package com.example.egar_admin.adapters.Review;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Order;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.Model.Rating;
import com.example.egar_admin.Model.Review;
import com.example.egar_admin.R;
import com.example.egar_admin.controllers.OrderController;
import com.example.egar_admin.controllers.ProductController;
import com.example.egar_admin.databinding.ItemRatingBinding;
import com.example.egar_admin.interfaces.OnOrderStatusFetchListener;
import com.example.egar_admin.interfaces.ProductCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Rating> ratings;

    public ReviewAdapter(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRatingBinding binding = ItemRatingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.savaData(ratings.get(position));
        Rating rating = ratings.get(position);

        OrderController.getInstance().getOrderStatusById(FirebaseAuth.getInstance().getUid(), new OnOrderStatusFetchListener() {
            @Override
            public void onGetOrderStatusSuccess(String orderStatus) {

            }

            @Override
            public void onGetOrderStatusFailure(String s) {

            }

            @Override
            public void onGetOrdersSuccess(List<Order> completedOrders) {
                holder.binding.textReviewText.setText(rating.getReviewText());
                holder.binding.textNameUser.setText(completedOrders.get(position).getUser().getName());
                Picasso.get().load(completedOrders.get(position).getUser().getProfileImageUri()).into(holder.binding.imageUserReview);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ratings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       ItemRatingBinding binding;

        public ViewHolder( ItemRatingBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;
        }
        public void savaData(Rating rating){

            //binding.textReviewText.setText(rating.getReviewText());


        }
    }
}

