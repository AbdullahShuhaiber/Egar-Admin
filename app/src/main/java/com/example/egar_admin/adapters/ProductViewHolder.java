package com.example.egar_admin.adapters;

import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ItemProductShowBinding;
import com.example.egar_admin.databinding.ItemProductsBinding;
import com.example.egar_admin.interfaces.ItemCallback;

public class ProductViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

    ItemProductsBinding binding;
    private ItemCallback callback;

    public ProductViewHolder(ItemProductsBinding binding, ItemCallback callback) {
        super(binding.getRoot());
        this.binding=binding;
        this.callback=callback;
        initializeView();
    }

    private void initializeView() {
        setOnClickListeners();
    }

    private void setOnClickListeners() {
       // binding.imgDelete.setOnClickListener(this::onClick);
        //binding.noteItemView.setOnClickListener(this::onClick);
    }

    public void savaData(Product product){
        binding.tvProductName.setText(product.getName());
        binding.tvPrinc.setText(String.valueOf(product.getPrice()));
        binding.imgProduct.setImageURI(Uri.parse(product.getImageUrl()));
        binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onDelete(product);
                }
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
