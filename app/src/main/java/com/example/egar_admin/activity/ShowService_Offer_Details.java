package com.example.egar_admin.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityShowServiceOfferDetailsBinding;
import com.squareup.picasso.Picasso;

public class ShowService_Offer_Details extends AppCompatActivity {

    ActivityShowServiceOfferDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowServiceOfferDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getOffer();

    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowService_Offer_Details.this, R.color.black));
    }

    @Override
    protected void onStart() {
        super.onStart();
       // operationsSccren();
    }


    private Offer offer(){
        Offer offer = (Offer) getIntent().getSerializableExtra("offer");
        return offer;
    }

    private void getOffer(){
        binding.tvTextProductName.setText(offer().getProduct().getName());
        binding.tvTextDescription.setText(offer().getProduct().getDescription());
        binding.tvPrice.setText(String.valueOf(offer().getProduct().getPrice()));
        Picasso.get().load(offer().getProduct().getImageUrl()).into(binding.productImg);

    }
}