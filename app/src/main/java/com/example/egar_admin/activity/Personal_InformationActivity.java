package com.example.egar_admin.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.FirebaseManger.FirebaseFetchingDataController;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityPersonalInformationBinding;
import com.example.egar_admin.interfaces.DataCallBackUser;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.interfaces.UserDataCallBack;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Personal_InformationActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPersonalInformationBinding binding;

    private ActivityResultLauncher<String> permissionRL;
    private ActivityResultLauncher<Void> cameraRL;



    private Uri pickedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void initializeView(){
        setOnClick();
        setProfile();
    }

    private void  setOnClick(){
        binding.btnBack.setOnClickListener(this::onClick);
        binding.btnModify.setOnClickListener(this::onClick);
        binding.imagevEdit.setOnClickListener(this::onClick);

    }

    private void setProfile(){
        FirebaseFetchingDataController.getInstance().getCurrentUserData(new DataCallBackUser() {
            @Override
            public void onSuccess(String name, String address, String number, String providerImage) {
                binding.etUserName.setText(name);
                binding.etEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                binding.etPhoneNumber.setText(number);
                Picasso.get().load(providerImage).into(binding.imageProvider);
            }

            @Override
            public void onFailure(String message) {

            }
        });


    }

    private void update(){
//    public User(String name, String email, String phoneNumber, String profileImageUri) {
        String name = binding.etUserName.getText().toString();
        String email = binding.etEmail.getText().toString();
        String phoneNumber = binding.etPhoneNumber.getText().toString();



            FirebaseAuthController.getInstance().updateProviderProfile(FirebaseAuth.getInstance().getUid(), name, email, phoneNumber/*, "", "", ""*/, pickedImageUri, new ProcessCallback() {
                @Override
                public void onSuccess(String message) {

                }

                @Override
                public void onFailure(String message) {

                }
            });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back){
            onBackPressed();

        }
        if (v.getId() == R.id.btn_Modify){
            update();
        }if (v.getId() == R.id.imagev_edit){
            selectImage();
        }

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
            binding.imageProvider.setImageURI(pickedImageUri);
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
    }
}