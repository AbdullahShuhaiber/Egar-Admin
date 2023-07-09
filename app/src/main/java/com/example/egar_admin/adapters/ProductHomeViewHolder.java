package com.example.egar_admin.adapters;

import android.net.Uri;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Product;

import com.example.egar_admin.databinding.ItemProductShowBinding;
import com.example.egar_admin.interfaces.ItemCallbackLongProduct;
import com.example.egar_admin.interfaces.ItemCallbackProduct;
import com.squareup.picasso.Picasso;

public class ProductHomeViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

    ItemProductShowBinding binding;

    ItemCallbackProduct callbackProduct;
    ItemCallbackLongProduct callbackLongProduct;

    public ProductHomeViewHolder(ItemProductShowBinding binding, ItemCallbackProduct callbackProduct,ItemCallbackLongProduct callbackLongProduct) {
        super(binding.getRoot());
        this.binding=binding;
        this.callbackProduct=callbackProduct;
        this.callbackLongProduct=callbackLongProduct;
    }

    public void savaData(Product product){
        binding.tvProductShow.setText(product.getName());
        binding.tvProductPrice.setText(String.valueOf(product.getPrice()));
        binding.tvProductDescription.setText(product.getDescription());
        Picasso.get().load(product.getImageUrl()).into(binding.imgProductShow);



        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackProduct.onItemClick(product);
            }
        });

        binding.imageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackLongProduct.onItemLongClick(product);
            }
        });


    }




/*    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.img_delete) {
            if (callback != null) {
                callback.onDelete(getAdapterPosition());
            }
        }

    }*/
}
