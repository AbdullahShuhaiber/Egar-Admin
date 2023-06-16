package com.example.egar_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.SpinnerAdapter;
import com.example.egar_admin.adapters.productShowProvider.ProductSpinnerAdapter;
import com.example.egar_admin.controllers.OfferController;
import com.example.egar_admin.controllers.ProductController;

import com.example.egar_admin.databinding.ActivityOfferBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.interfaces.ProductCallback;
import com.example.egar_admin.ui.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityOfferBinding binding;
    OfferController offerController = new OfferController();

    List<Product> products =new ArrayList<>();

    SpinnerAdapter adapter;
    Offer offer ;

    //private ProductSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOfferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeView();

    }

    private void initializeView() {
     setOnClickListeners();
     initializeRecyclerAdapter();
     getProduct();
    }

    private void setOnClickListeners() {
        binding.btnAddProductView.setOnClickListener(this);
    }

    private void initializeRecyclerAdapter() {
        adapter =new SpinnerAdapter(products,getBaseContext()) ;
        binding.spinnerProduct.setAdapter(adapter);
    }

    private void performSave() {
        if (checkData()) {
            addProductView();
        } else {
            Snackbar.make(binding.getRoot(), "Please enter Data , The Input Filed is Required", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.bronze)).show();
        }
    }

    private boolean checkData() {
        String startDate = binding.etOfferStartDate.getText().toString();
        String endDate = binding.etOfferEndDate.getText().toString();
        String price = binding.etDiscountPercentage.getText().toString();
        //String imageUrl = binding.editProductImage.getText().toString();
        //String quantityInCart = binding.editQuantity.getText().toString();
        if (startDate.isEmpty()) {
            binding.etOfferStartDate.setError("startDate field is Required");
            return false;
        } else if (endDate.isEmpty()) {
            binding.etOfferEndDate.setError("endDate field is Required");
            return false;
        } else if (price.isEmpty()) {
            binding.etDiscountPercentage.setError("description field is Required");
            return false;
        }/*else if (true*//*imageUrl.isEmpty()*//*) {
           // binding.editProductImage.setError("image field is Required");
            return false;*/ /*else if (quantityInCart.isEmpty()) {
            binding.editQuantity.setError("Quantity field is Required");
            return false;
        }*/
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_addProduct_View){
            performSave();
        }
    }

    private void getProduct(){
/*        ProductController.getInstance().getAllProducts(new ProductCallback() {
            @Override
            public void onSuccess(List<Product> productList) {
                products.addAll(productList);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onProductFetchSuccess(Product product) {

            }

            @Override
            public void onFailure(String message) {

            }
        });*/
    }

    private void addProductView(){
        String startDate = binding.etOfferStartDate.getText().toString();
        String endDate = binding.etOfferEndDate.getText().toString();
        String price = binding.etDiscountPercentage.getText().toString();
       // offer =new Offer("1",Double.parseDouble(price),1,startDate ,endDate);
        offerController.addOffer(offer, new ProcessCallback() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}