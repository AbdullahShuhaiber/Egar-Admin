package com.example.egar_admin.adapters;

import android.net.Uri;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.databinding.ItemProductShowBinding;
import com.example.egar_admin.databinding.ItemProductsBinding;
import com.example.egar_admin.interfaces.ItemCallback;
import com.squareup.picasso.Picasso;

public class ProductHomeViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

    ItemProductShowBinding binding;

    public ProductHomeViewHolder(ItemProductShowBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
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
        binding.tvProductShow.setText(product.getName());
       // binding.imgProductShow.setImageURI(Uri.parse(product.getImageUrl()));
        Picasso.get().load(product.getImageUrl()).into(binding.imgProductShow);
/*
        binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onDelete(product);
                }
            }
        });
*/

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
