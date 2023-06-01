package com.example.egar_admin.ui.addProduct;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.egar_admin.Model.Product;
import com.example.egar_admin.Model.Provider;
import com.example.egar_admin.R;
import com.example.egar_admin.controllers.ProductController;
import com.example.egar_admin.databinding.FragmentAddProductBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.interfaces.ServiceProviderCallBack;
import com.example.egar_admin.ui.MainActivity;
import com.example.egar_admin.ui.homeAdmin.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class AddProductFragment extends Fragment implements View.OnClickListener{

    private FragmentAddProductBinding binding;
    private Uri pickedImageUri;
    private ActivityResultLauncher<Void> cameraRL;
    private ActivityResultLauncher<String> permissionRL;

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
        setupActivityResults();
    }

    private void setOnClickListeners() {
        binding.btnAddProduct.setOnClickListener(this::onClick);
        binding.imageAddProduct.setOnClickListener(this::onClick);
    }





    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_addProduct){
            performSave();
        }if (v.getId() == R.id.image_add_product){
           // pickImage();
            selectImage();
        }
    }

    private void performSave() {
        if (checkData()) {
            //Log.d("TAG", "performSave: "+pickedImageUri);
           // Toast.makeText(getActivity(), ""+pickedImageUri, Toast.LENGTH_SHORT).show();
            addProduct();
        }else {
            Snackbar.make(binding.getRoot(), "Please enter Data , The Input Filed is Required", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(getActivity(), R.color.bronze)).show();
        }
    }
    private boolean checkData (){
        String nameProduct = binding.etNameProduct.getText().toString();
        String description = binding.editProductDescription.getText().toString();
        String price = binding.editPrice.getText().toString();
        //String imageUrl = binding.editProductImage.getText().toString();
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
        }/*else if (true*//*imageUrl.isEmpty()*//*) {
           // binding.editProductImage.setError("image field is Required");
            return false;*/
        else if (quantityInCart.isEmpty()) {
            binding.editQuantity.setError("Quantity field is Required");
            return false;
        }
        return true;
    }
    public void getServiceProviderData(String serviceProviderId, ServiceProviderCallBack callback) {
        CollectionReference serviceProvidersCollection = FirebaseFirestore.getInstance().collection("serviceProviders");

        DocumentReference serviceProviderDoc = serviceProvidersCollection.document(serviceProviderId);

        serviceProviderDoc.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Provider serviceProvider = document.toObject(Provider.class);

                    callback.onSuccess(serviceProvider);
                } else {
                    callback.onFailure("Service provider not found");
                }
            } else {
                callback.onFailure("Error retrieving service provider data");
            }
        });
    }

    private void addProduct(){

        String nameProduct = binding.etNameProduct.getText().toString();
        String description = binding.editProductDescription.getText().toString();
        double price = Double.parseDouble(binding.editPrice.getText().toString());
        int quantityInCart = Integer.parseInt(binding.editQuantity.getText().toString());
        getServiceProviderData(FirebaseAuth.getInstance().getCurrentUser().getUid(), new ServiceProviderCallBack() {
            @Override
            public void onSuccess(Provider provider) {
                Product product=new Product(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid(),nameProduct,description,price,pickedImageUri,quantityInCart,provider.getProviderType());
                ProductController.getInstance().addProduct(product,pickedImageUri, new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(getActivity(), "Product Added Successful"+nameProduct, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.d("addProduct", "onFailure: ");

                    }
                });
            }

            @Override
            public void onFailure(String message) {

            }
        });




    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100&& data!= null && data.getData() != null){
            pickedImageUri = data.getData();
            binding.imageAddProduct.setImageURI(pickedImageUri);
        }
    }

    private void setupActivityResults(){
        permissionRL = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result){
                    cameraRL.launch(null);

                }
            }
        });



/*        cameraRL = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                if (result != null){
                    pickedImageBitmap = result;
                    binding.imageAddProduct.setImageBitmap(pickedImageBitmap);

                }
            }
        });*/

    }
    private void pickImage(){
        permissionRL.launch(Manifest.permission.CAMERA);
    }
}