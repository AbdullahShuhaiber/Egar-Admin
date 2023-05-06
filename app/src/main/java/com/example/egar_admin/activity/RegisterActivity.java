package com.example.egar_admin.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.MyFragmentAdapter;

import com.example.egar_admin.databinding.ActivityRegisterBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding binding;
    private MyFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intizilSccren();


    }

    private void intizilSccren() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        setOnClick();
    }

    private boolean isValidPalestinianPhoneNumber() {
        String phoneNumber = binding.etPhone.getText().toString().trim();
        if (phoneNumber.isEmpty()){
            Snackbar.make(binding.getRoot(), "Please enter a  phone number", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();
            return false;
        } else if (phoneNumber.matches("^\\+970(59|56)\\d{7}$")) {
            return true;
        }else {
            Snackbar.make(binding.getRoot(), "Please enter a valid Palestinian phone number starting with 059 or 056.", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();
            return false;
        }

    }

    public boolean isValidEmail() {
        String email = binding.etEmail.getText().toString().trim();
        boolean isValid = false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            isValid = false;
        } else {
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
        }
        if (!isValid) {
            Snackbar.make(binding.getRoot(), "Invalid email address", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();

        }
        return isValid;
    }

    private boolean dataCheck (){
        String name = binding.etName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPass.getText().toString().trim();

        if (name.isEmpty()) {
            binding.etName.setError("UserName field is Required");
            return false;
        } else if (email.isEmpty()  ) {
            binding.etEmail.setError("Email field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPhone.setError("Password field is Required");
            return false;
        }else if(!binding.checked.isChecked()) {
            Snackbar.make(binding.getRoot(),"You must agree to the terms and conditions",Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this, R.color.bronze)).show();
            return false;
        }

        return true;
    }
    private void register() {
        FirebaseAuthController.getInstance().createAccount(binding.etName.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etPass.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private  void setOnClick(){
        binding.bnRegister.setOnClickListener(this::onClick);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bn_register:
                if (dataCheck() && isValidPalestinianPhoneNumber()){
                    register();
                    Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                    String email =binding.etEmail.getText().toString().trim();
                    String pass = binding.etPass.getText().toString().trim();
                    intent1.putExtra("email",email);
                    intent1.putExtra("password",pass);
                    startActivity(intent1);
                }
                break;
        }
    }


}