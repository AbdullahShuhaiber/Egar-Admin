package com.example.egar_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityChangePasswordBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.ui.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controlActivity();
    }

    private void controlActivity(){
        setOnClick();
    }
    private void changePassword(){
        FirebaseAuthController.getInstance().changePassword(
                binding.etCurrentPassword.getText().toString().trim(),
                binding.etNewPassword.getText().toString().trim(),
                binding.etNewPasswordConfirmation.getText().toString().trim(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean checkDataINTextFiled(){
        if (binding.etCurrentPassword.getText().toString().trim().isEmpty()
                || binding.etNewPassword.getText().toString().trim().isEmpty()
                || binding.etNewPasswordConfirmation.getText().toString().trim().isEmpty()){
            Snackbar.make(binding.getRoot(),"Please Input Data in Text Filed",Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void setOnClick(){
        binding.btnSave.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            if (checkDataINTextFiled()) {
                changePassword();
            }
        }
    }
}