package com.example.egar_admin.adapters.offers;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.databinding.ItemOffersBinding;
import com.example.egar_admin.interfaces.ItemCallbackOffer;
import com.squareup.picasso.Picasso;


public class OffersViewHolder extends RecyclerView.ViewHolder {
    ItemOffersBinding binding;
    ItemCallbackOffer callbackOffer;
    public OffersViewHolder(ItemOffersBinding binding, ItemCallbackOffer callbackOffer) {
        super(binding.getRoot());
        this.binding=binding;
        this.callbackOffer=callbackOffer;
    }

    public void savaData(Offer offer){
        binding.textNameProduct.setText(offer.getProduct().getName());
        binding.textPrice.setText(String.valueOf(offer.getProduct().getPrice()));
        //نسبة الخصم = ((السعر القديم - السعر الجديد) / السعر القديم) × 100
        double rate = ((offer.getProduct().getPrice() - offer.getPrice())/offer.getProduct().getPrice())*100;

        //binding.textDiscountPercentage.setText(String.valueOf(rate));
        binding.textDiscountPercentage.setText(String.valueOf(Math.round(rate)));

        binding.textPriceDiscount.setText(String.valueOf(offer.getPrice()));
        Picasso.get().load(offer.getProduct().getImageUrl()).into(binding.imageProduct);
        Picasso.get().load(offer.getProduct().getProvider().getImage()).into(binding.imageProvider);


        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackOffer.onItemClick(offer);

            }
        });


    }
}
