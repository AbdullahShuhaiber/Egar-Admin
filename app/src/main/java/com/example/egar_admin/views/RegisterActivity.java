package com.example.egar_admin.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        screenOperations();
    }

    private void screenOperations (){
        setOnClick();


    }

    private boolean dataCheck (){
        String phone = binding.etPhone.getText().toString();
        String storeName = binding.etStoreName.getText().toString();
        String storeOwnerName = binding.etStoreOwnerName.getText().toString();
        String commercialRegistration = binding.etCommercialRegistration.getText().toString();
        String address = binding.editAddress.getText().toString();

        if (storeName.isEmpty()) {
            binding.etStoreName.setError("storeName field is Required");
            return false;
        } else if (phone.isEmpty()) {
            binding.etPhone.setError("PhoneNumber field is Required");
            return false;
        } else if (storeOwnerName.isEmpty()) {
            binding.etStoreOwnerName.setError("storeOwnerName field is Required");
            return false;
        }else if(!binding.checked.isChecked()) {
            Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onStop() {
        super.onStop();
    }


    private void setOnClick(){
        //binding.singin.setOnClickListener(this::onClick);
        binding.bnRegister.setOnClickListener(this::onClick);
        binding.back.setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
/*            case R.id.singin:
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                break;*/
            case R.id.bn_register:
                if (dataCheck()){
                    Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                    String phone =binding.etPhone.getText().toString().trim();
                    String storeName = binding.etStoreName.getText().toString().trim();
                    String storeOwnerName = binding.etStoreOwnerName.getText().toString().trim();
                    String address = binding.editAddress.getText().toString().trim();
                    //String storeName = binding.etStoreName.getText().toString().trim();
                    intent1.putExtra("phone",phone);
                    intent1.putExtra("storeName",storeName);
                    intent1.putExtra("storeOwnerName",storeOwnerName);
                    intent1.putExtra("address",address);
                    startActivity(intent1);
                }else {
                    Toast.makeText(this, "The Input Fields Required", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                Intent intent2 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }
}