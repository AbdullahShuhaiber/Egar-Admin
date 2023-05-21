package com.example.egar_admin.ui.addProduct;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.controllers.ProductController;
import com.example.egar_admin.databinding.FragmentAddProductBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.ui.homeAdmin.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

public class AddProductFragment extends Fragment implements View.OnClickListener{

    private FragmentAddProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initializeView();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initializeView(){
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.btnAddProduct.setOnClickListener(this::onClick);
    }






    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_addProduct){
            performSave();
        }

    }

    private void performSave() {
        if (checkData()) {
            addProduct();
        }else {
            Snackbar.make(binding.getRoot(), "Please enter Data , The Input Filed is Required", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(getActivity(), R.color.bronze)).show();
        }
    }
    private boolean checkData (){
        String nameProduct = binding.etNameProduct.getText().toString();
        String description = binding.editProductDescription.getText().toString();
        String price = binding.editPrice.getText().toString();
        String imageUrl = binding.editProductImage.getText().toString();
        String quantityInCart = binding.editQuantity.getText().toString();
        if (nameProduct.isEmpty()) {
            binding.etNameProduct.setError("nameProduct field is Required");
            return false;
        } else if (price.isEmpty()) {
            binding.editPrice.setError("price field is Required");
            return false;
        }else if (description.isEmpty()) {
            binding.editProductDescription.setError("description field is Required");
            return false;
        }else if (imageUrl.isEmpty()) {
            binding.editProductImage.setError("image field is Required");
            return false;
        }else if (quantityInCart.isEmpty()) {
            binding.editQuantity.setError("Quantity field is Required");
            return false;
        }
        return true;
    }
    private void addProduct(){

        Log.d("TAG", "addProduct: ");

        String nameProduct = binding.etNameProduct.getText().toString();
        String description = binding.editProductDescription.getText().toString();
        double price = Double.parseDouble(binding.editPrice.getText().toString());
        String imageUrl = binding.editProductImage.getText().toString();
        int quantityInCart = Integer.parseInt(binding.editQuantity.getText().toString());

       // Product newProduct = new Product("123", "Product name", "Product description", 10.0, "https://example.com/image.jpg");
        Product product=new Product(nameProduct,description,price,imageUrl,quantityInCart);
        ProductController.getInstance().addProduct(product,"", new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(getActivity(), "addProduct"+nameProduct, Toast.LENGTH_SHORT).show();
                Log.d("addProduct", "onSuccess: ");
            }

            @Override
            public void onFailure(String message) {
                Log.d("addProduct", "onFailure: ");

            }
        });


    }
}