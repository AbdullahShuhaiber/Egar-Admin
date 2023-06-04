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

import com.example.egar_admin.FirebaseManger.FirebaseFetchingDataController;
import com.example.egar_admin.FirebaseManger.FirebaseFireStoreController;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.Model.Provider;
import com.example.egar_admin.R;
import com.example.egar_admin.controllers.ProductController;
import com.example.egar_admin.databinding.FragmentAddProductBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.interfaces.ServiceProviderCallBack;
import com.example.egar_admin.ui.MainActivity;
import com.example.egar_admin.ui.homeAdmin.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class AddProductFragment extends Fragment implements View.OnClickListener {

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

    private void initializeView() {
        setOnClickListeners();
        setupActivityResults();

    }

    private void setOnClickListeners() {
        binding.btnAddProduct.setOnClickListener(this);
        binding.imageAddProduct.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_addProduct) {
            performSave();
        }
        if (v.getId() == R.id.image_add_product) {
            // pickImage();
            selectImage();
        }
    }

    private void performSave() {
        if (checkData()) {

            addProduct();
        } else {
            Snackbar.make(binding.getRoot(), "Please enter Data , The Input Filed is Required", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(getActivity(), R.color.bronze)).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private boolean checkData() {
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
        } else if (description.isEmpty()) {
            binding.editProductDescription.setError("description field is Required");
            return false;
        }/*else if (true*//*imageUrl.isEmpty()*//*) {
           // binding.editProductImage.setError("image field is Required");
            return false;*/ else if (quantityInCart.isEmpty()) {
            binding.editQuantity.setError("Quantity field is Required");
            return false;
        }
        return true;
    }

    public void printUserData(ProcessCallback callback) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("serviceproviders").document(userId);

        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    String email = documentSnapshot.getString("email");
                    String providerType = documentSnapshot.getString("providerType");
                    String phoneNumber = documentSnapshot.getString("phoneNumber");
                    String password = documentSnapshot.getString("password");


                } else {
                    callback.onFailure("User document does not exist");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    public void getServiceProviderData(ServiceProviderCallBack callback) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("serviceproviders").document(userId);

        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    String email = documentSnapshot.getString("email");
                    String providerType = documentSnapshot.getString("providerType");
                    String phoneNumber = documentSnapshot.getString("phoneNumber");
                    String password = documentSnapshot.getString("password");
                    String address = documentSnapshot.getString("address");
                    String city = documentSnapshot.getString("city");
                    String bio = documentSnapshot.getString("bio");
                    String id = FirebaseAuth.getInstance().getUid();
                    if (id == null) {
                        id = "no id ";
                    }else {
                        Provider provider = new Provider(id,name, email, providerType, phoneNumber, address, city, bio);
                        callback.onSuccess(provider);
                    }

                } else {
                    callback.onFailure("User document does not exist");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }


    private void addProduct() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(getActivity(), "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }


        getServiceProviderData(new ServiceProviderCallBack() {
            @Override
            public void onSuccess(Provider provider) {
                String nameProduct = binding.etNameProduct.getText().toString().trim();
                String description = binding.editProductDescription.getText().toString().trim();
                String priceText = binding.editPrice.getText().toString().trim();
                String quantityText = binding.editQuantity.getText().toString().trim();

                if (nameProduct.isEmpty() || description.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double price;
                int quantityInCart;
                try {
                    price = Double.parseDouble(priceText);
                    quantityInCart = Integer.parseInt(quantityText);
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid price or quantity value", Toast.LENGTH_SHORT).show();
                    return;
                }
                ProductController.getInstance().addProduct(nameProduct, description, price, false, pickedImageUri, quantityInCart, provider.getProviderType(), provider, new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Snackbar.make(binding.getRoot(), message + nameProduct, Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(), message + "failure 1", Snackbar.LENGTH_LONG).show();


                    }
                });
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(getActivity(), message + "لا يوجد", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null) {
            pickedImageUri = data.getData();
            binding.imageAddProduct.setImageURI(pickedImageUri);
        }
    }

    private void setupActivityResults() {
        permissionRL = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
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

    private void pickImage() {
        permissionRL.launch(Manifest.permission.CAMERA);
    }
}