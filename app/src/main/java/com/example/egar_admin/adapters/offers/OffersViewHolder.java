package com.example.egar_admin.adapters.offers;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.databinding.ItemOffersBinding;
import com.squareup.picasso.Picasso;


public class OffersViewHolder extends RecyclerView.ViewHolder {
    ItemOffersBinding binding;
    public OffersViewHolder(ItemOffersBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public void savaData(Offer offer){
        binding.textNameProduct.setText(offer.getProduct().getName());
        binding.textPrice.setText(String.valueOf(offer.getPrice()));
        binding.textDiscountPercentage.setText("10-");
        binding.textPriceDiscount.setText(String.valueOf(offer.getPrice()));
        Picasso.get().load(offer.getProduct().getImageUrl()).into(binding.imageProduct);
        Picasso.get().load(offer.getProduct().getProvider().getImage()).into(binding.imageProvider);



    }
}
