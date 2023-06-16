package com.example.egar_admin.adapters.productShowProvider;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.databinding.ItemProductShowProviderBinding;
import com.squareup.picasso.Picasso;

public class ProductSpinnerViewHolder extends RecyclerView.ViewHolder {
    ItemProductShowProviderBinding binding;

    public ProductSpinnerViewHolder(ItemProductShowProviderBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    public void savaData(Product product){
        binding.textNameproductShowProvider.setText(product.getName());
        Picasso.get().load(product.getImageUrl()).into(binding.imageProductShowProvider);



    }
}
