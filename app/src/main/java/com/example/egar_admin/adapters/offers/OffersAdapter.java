package com.example.egar_admin.adapters.offers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.databinding.ItemOffersBinding;
import com.example.egar_admin.interfaces.ItemCallbackOffer;

import java.util.List;


public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {

    List<Offer> offerList;

    ItemCallbackOffer callbackOffer;


    public OffersAdapter(List<Offer> offerList) {
        this.offerList = offerList;
    }


    public void setCallbackOffer(ItemCallbackOffer callbackOffer) {
        this.callbackOffer = callbackOffer;
    }
    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOffersBinding binding=ItemOffersBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new OffersViewHolder(binding,callbackOffer);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
       holder.savaData(offerList.get(position));
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
