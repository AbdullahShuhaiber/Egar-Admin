package com.example.egar_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private List<Product> productList;

    private Context context;

    public SpinnerAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productList != null ? productList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_product_show_provider,parent,false);

        TextView textName = rootView.findViewById(R.id.text_nameproduct_show_provider);
        ImageView imageView = rootView.findViewById(R.id.image_product_show_provider);

        textName.setText(productList.get(position).getName());
        Picasso.get().load(productList.get(position).getImageUrl()).into(imageView);
        return rootView;
    }
}
